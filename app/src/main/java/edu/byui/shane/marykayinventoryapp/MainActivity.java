package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Hashtable;


public class MainActivity extends ActionBarActivity {
    public static String TAG_FOR_APP = "MaryKayStuffAndThings";
    private Hashtable<String, ProductEntry> inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG_FOR_APP, "Startup of App successful");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TAG_FOR_APP, "Starting to read database...");
        InventoryManager.getInstance().readFromDatabase();
        Log.i(TAG_FOR_APP, "Finished reading database.");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
     * This function will take the user from the home page to the Update Product List Activity when the user clicks on the button.
     * @param view
     */
    public void switchToUpdateProductList(View view) {
        Intent switcheroo = new Intent(this, UpdateProductListActivity.class);
        startActivity(switcheroo);
    }

    /**
     * This function will take the user from the home page to the Update Inventory Activity when the user clicks on the button.
     * @param view
     */
    public void switchToUpdateInventory(View view) {
        Intent switcheroo = new Intent(this, UpdateInventoryActivity.class);
        startActivity(switcheroo);
    }

    /**
     * This function will take the user from the home page to the Inventory List Activity when the user clicks on the button.
     * @param view
     */
    public void switchToInventoryList(View view) {
        Intent switcheroo = new Intent(this, InventoryListActivity.class);
        startActivity(switcheroo);
    }

    /**
     * This function will take the user from the home page to the Reorder Product Activity when the user clicks on the button.
     * @param view
     */
    public void switchToReorderProduct(View view) {
        Intent switcheroo = new Intent(this, ReorderProductActivity.class);
        startActivity(switcheroo);
    }
}
