package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Environment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.content.Context;

import java.util.Hashtable;


/**
 * MainActivity is just the main page or start page for the entire app.
 */
public class MainActivity extends ActionBarActivity {
    private static Resources mainResources;
    private Hashtable<String, ProductEntry> inventory;

    /** Make sure onCreate is called first to initialize! */
    public static Resources getMainResources() {
        return mainResources;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainResources = getResources();
        Log.i(MyApp.LOGGING_TAG, "Loaded home page in MainActivity.onCreate");
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
     * This function will take the user from the home page to the Update Inventory Activity when the user clicks on the button.
     * @param view The current view the user is looking at
     */
    public void switchToUpdateInventory(View view) {
        Intent switcheroo = new Intent(this, UpdateInventoryActivity.class);
        startActivity(switcheroo);
    }

    /**
     * This function will take the user from the home page to the Inventory List Activity when the user clicks on the button.
     * @param view The current view the user is looking at
     */
    public void switchToInventoryList(View view) {
        Log.v(MyApp.LOGGING_TAG, "Switching to inventory list view in MainActivity.switchToInventoryList");
        Intent switcheroo = new Intent(this, InventoryListActivity.class);
        startActivity(switcheroo);
    }

    /**
     * This function will take the user from the home page to the Reorder Product Activity when the user clicks on the button.
     * @param view The current view the user is looking at
     */
    public void switchToReorderProduct(View view) {
        Intent switcheroo = new Intent(this, ReorderProductActivity.class);
        startActivity(switcheroo);
    }
}
