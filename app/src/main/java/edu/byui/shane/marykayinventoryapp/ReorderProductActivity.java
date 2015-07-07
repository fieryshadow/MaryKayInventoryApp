package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class ReorderProductActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_product);
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
        //Products for testing purposes only.
        Product eyeshadow = new Product("123456", "Makeup", "EyeShadow", "Section1", "Blue/Black", (float) 10.99);
        ProductEntry entry1 = new ProductEntry(eyeshadow, 3, 0, 6);
        Product lipstick = new Product("3456", "Makeup", "LipStick", "Section1", "Red", (float) 8.99);
        ProductEntry entry2 = new ProductEntry(lipstick, 2, 0, 8);
    }

    /**
     * This function doesn't store any of the information that was entered on the page and it will return the user to the home page.
     * @param view
     */
    public void cancel(View view) {
        finish();
    }
}
