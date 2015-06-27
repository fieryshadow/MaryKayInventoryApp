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
    private Hashtable<String, ProductEntry> inventory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i("MainActivity", "Startup of App successful");
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


}
