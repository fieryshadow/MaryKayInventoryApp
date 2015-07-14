package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import org.w3c.dom.Text;

/**
 * The Update Inventory Activity allows the user to edit the items in the inventory.
 */
public class UpdateInventoryActivity extends ActionBarActivity implements AdapterView.OnItemSelectedListener {
    public final static String EXTRA_MESSAGE = "Product Number: ";
    private String productSection;
    public final static String EXTRA_REMOVED = "processCheckOut";
    private boolean removeProduct;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        spinner = (Spinner) findViewById(R.id.ProductSection);

        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.Sections, android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_update_inventory, menu);
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

    /**
     * Moves to the update product list activity, passing along necessary info
     * @param view The view the user is looking at
     */
    public void updateProduct(View view) {
        Log.i(MyApp.LOGGING_TAG, "Button Clicked");
        //create intent

        Button button = (Button) findViewById(R.id.ScanIn);
        if (button.isActivated()){
            removeProduct = false;
        }
        Intent intent = new Intent(this, UpdateProductListActivity.class);
        // get info from the edit text box
        EditText Product = (EditText) findViewById(R.id.ProductNumber);
        Spinner Section = (Spinner) findViewById(R.id.ProductSection);
        if (Product.getText().toString().equals("")){
            Log.e(MyApp.LOGGING_TAG, "Empty String being passed in UpdateInventoryActivity.updateProduct");
        }


        // create message to send
        String productNumber = Product.getText().toString();
        // test print of the full message to be sent
        Log.i(MyApp.LOGGING_TAG, EXTRA_MESSAGE + productNumber);
        // add info from edit text box to the intent
        intent.putExtra("Product Section", productSection);
        intent.putExtra(EXTRA_MESSAGE, productNumber);
        intent.putExtra("processCheckOut", removeProduct);
        // start the activity
        startActivity(intent);
    }

    /**
     * Moves to the next activity and passes the Product number and section with it.
     * @param view The view the user is looking at
     */
    public void removeProduct(View view) {
        Button button = (Button) findViewById(R.id.ScanOut);
        if (button.isActivated()){
            removeProduct = true;
        }
        Intent intent = new Intent(this, UpdateProductListActivity.class);
        EditText product = (EditText) findViewById(R.id.ProductNumber);
        if (product.getText().toString().equals("")) {
            Log.e(MyApp.LOGGING_TAG, "Empty String being passed in UpdateInventoryActivity.removeProduct");
        }
        String message = product.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra(EXTRA_REMOVED, removeProduct);

        startActivity(intent);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        TextView myText =  (TextView) view;
        if(myText.getText().toString().equals("Section 1")){
            productSection = "1";
        } else {
            productSection = "2";
        }

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
