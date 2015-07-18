package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;


public class ReorderProductActivity extends ActionBarActivity {
    private List<ProductInfo> productList;
    private float total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_product);

        final TextView totalCost = (TextView) findViewById(R.id.orderTotal);
        ListView listView = (ListView) findViewById(R.id.orderList);

        productList = InventoryManager.getInstance().getListing();

        OrderListAdapter adapter = new OrderListAdapter(this, R.layout.order_list_item, productList);

        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ProductInfo info = productList.get(position);

                if (info.getNumberToOrder() == 0) {
                    info.setNumberToOrderToDefault();
                    // do the order update and number manipulation stuff here, this is an example
                    TextView amountView = (TextView) view.findViewById(R.id.amountView);
                    // the following doesn't actually save any values, so we need to put some more data into product info...
                    amountView.setText("You will be getting " + info.getNumberToOrder() + " of these items from MaryKay if you choose to continue...");
                    amountView.setTextColor(Color.parseColor("#ffff0000"));// red
                    totalCost.setText("Total Cost: " + updateTotalCost(info));
                } else {
                    // do something to allow user to enter a number to set numberToOrder in the info object.
                }
            }
        });
        Log.i(MyApp.LOGGING_TAG, "productList Size = " +productList.size());

    }
    public float updateTotalCost(ProductInfo productInfo){
        total += productInfo.getCost()*productInfo.getNumberToOrder()/2;
        return total;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reorder_product, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void submit(View view) {
        finish();
        //instead of calling finish(), loop through the productList and call processOrder on any object that toOrder isn't 0.
    }

    /**
     * This function doesn't store any of the information that was entered on the page and it will return the user to the home page.
     * @param view The current user view
     */
    public void cancel(View view) {
        finish();
    }

}
