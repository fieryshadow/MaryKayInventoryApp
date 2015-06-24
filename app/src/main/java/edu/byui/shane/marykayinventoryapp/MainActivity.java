package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.Hashtable;


public class MainActivity extends ActionBarActivity {
    private Hashtable<String, ProductEntry> inventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Comments are fun!!
        // No they're not.
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

    // It's the end of the world as we know it!!! And I feel fine! psych  (Shane/Greg)

    public void switchToUpdateProductList(View view) {
        Intent switcheroo = new Intent(this, UpdateProductListActivity.class);
        startActivity(switcheroo);
    }

    public void switchToUpdateInventory(View view) {
        Intent switcheroo = new Intent(this, UpdateInventoryActivity.class);
        startActivity(switcheroo);
    }

    public void switchToInventoryList(View view) {
        Intent switcheroo = new Intent(this, InventoryListActivity.class);
        startActivity(switcheroo);
    }

    public void switchToReorderProduct(View view) {
        Intent switcheroo = new Intent(this, ReorderProductActivity.class);
        startActivity(switcheroo);
    }

    public void populateInventory(){
        inventory = new Hashtable<>();

        // testing values
        //           Product#       Product Entry   Product  Product#   Category              Name                Section       Description/Color               Price    InStock OutOfStock HighestInInventory
        inventory.put("072750", new ProductEntry(new Product("072750", "Skin Care",        "TimeWise Anti-Aging", "1", "Miracle Set: Normal/Dry",               95.00f), 1,      0,          1));
        inventory.put("060794", new ProductEntry(new Product("060794", "Foundations",      "Other",               "1", "Makeup Finishing Spray by Skindinavia", 18.00f), 4,      1,          5));
        inventory.put("023467", new ProductEntry(new Product("023467", "Concealer/Powder", "Concealer",           "2", "Ivory 1",                               12.00f), 1,      0,          2));
    }
}
