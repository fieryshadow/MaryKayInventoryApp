package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import java.util.Scanner;


public class UpdateInventoryActivity extends ActionBarActivity {
    public final static String TAG_UPDATE_INVENTORY_ACTIVITY = "UpdateInventoryActivity";
    public final static String EXTRA_MESSAGE = "Product Number: ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_inventory);
        EditText t;
        t = (EditText)findViewById(R.id.ProductNumber);
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
        Log.i(TAG_UPDATE_INVENTORY_ACTIVITY, "Button Clicked");
        //create intent
        Intent intent = new Intent(this, UpdateProductListActivity.class);
        // get info from the edit text box
        EditText Product = (EditText) findViewById(R.id.ProductNumber);
        if (Product.getText().toString().equals("")){
            Log.e(TAG_UPDATE_INVENTORY_ACTIVITY, "Empty String being passed");
        }

        // create message to send
        String message = Product.getText().toString();
        // test print of the full message to be sent
        Log.i(TAG_UPDATE_INVENTORY_ACTIVITY, EXTRA_MESSAGE + message);
        // add info from edit text box to the intent
        intent.putExtra(EXTRA_MESSAGE, message);
        // start the activity
        startActivity(intent);
    }
}
