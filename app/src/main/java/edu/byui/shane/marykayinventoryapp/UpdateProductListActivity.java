package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class UpdateProductListActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_list);
        Intent intent = getIntent();
        String message = intent.getStringExtra(UpdateInventoryActivity.EXTRA_MESSAGE);

        TextView ProductID = (TextView) findViewById(R.id.ProductNumber);
        ProductID.setTextSize(20);
        ProductID.setText(UpdateInventoryActivity.EXTRA_MESSAGE + message);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_product_list, menu);
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


        EditText category = (EditText) findViewById(R.id.ProductCategory);
        EditText name = (EditText) findViewById(R.id.ProductName);
        EditText section = (EditText) findViewById(R.id.ProductSection);
        EditText color = (EditText) findViewById(R.id.color);
        EditText numProduct = (EditText) findViewById(R.id.NumberofProduct);
        EditText cost = (EditText) findViewById(R.id.ProductCost);
        TextView ID = (TextView) findViewById(R.id.ProductNumber);
        InventoryManager inventoryManager = InventoryManager.getInstance();
        for (int i = 0; i <= numProduct.getAlpha(); ++i){
            inventoryManager.addProduct(ID.toString(), category.toString(), name.toString(), color.toString(), cost.getAlpha(), section.toString(), (int) numProduct.getAlpha());
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void getBarcode() {

    }
}
