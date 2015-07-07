package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Scanner;

/**
 * The Update Inventory Activity allows the user to edit the items in the inventory.
 */
public class UpdateInventoryActivity extends ActionBarActivity {
    public final static String EXTRA_MESSAGE = "Product Number: ";
    private boolean removeProduct;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
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

    private int barcode;

    public int getBarcode() {
        return barcode;
    }

    public void setBarcode(int barcode) {
        this.barcode = barcode;
    }



    public void updateProduct(View view) {
        Log.i(MainActivity.TAG_FOR_APP, "Button Clicked");
        //create intent

        Button button = (Button) findViewById(R.id.ScanIn);
        if (button.isActivated()){
            removeProduct = false;
        }
        Intent intent = new Intent(this, UpdateProductListActivity.class);
        // get info from the edit text box
        EditText Product = (EditText) findViewById(R.id.ProductNumber);
        EditText Section = (EditText) findViewById(R.id.ProductSection);

        if (Product.getText().toString().equals("")){
            Log.e(MainActivity.TAG_FOR_APP, "Empty String being passed");
        }


        // create message to send
        String productNumber = Product.getText().toString();
        String productSection = Section.getText().toString();
        // test print of the full message to be sent
        Log.i(MainActivity.TAG_FOR_APP, EXTRA_MESSAGE + productNumber);
        // add info from edit text box to the intent
        intent.putExtra("Product Section", productSection);
        intent.putExtra(EXTRA_MESSAGE, productNumber);
        intent.putExtra("removeProduct", removeProduct);
        // start the activity
        startActivity(intent);
    }

    /**
     * moves to the next activity and passes the Product number and section with it.
     * @param view
     */
    public void removeProduct(View view) {
        Button button = (Button) findViewById(R.id.ScanOut);
        if (button.isActivated()){
            removeProduct = true;
        }
        Intent intent = new Intent(this, UpdateProductListActivity.class);
        EditText product = (EditText) findViewById(R.id.ProductNumber);
        if (product.getText().toString().equals("")) {
            Log.e(MainActivity.TAG_FOR_APP, "Empty String being passed");
        }
        String message = product.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, message);
        intent.putExtra("removeProduct", removeProduct);

        startActivity(intent);
    }
}
