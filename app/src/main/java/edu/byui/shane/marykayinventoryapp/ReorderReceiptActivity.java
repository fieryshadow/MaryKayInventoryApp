package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.List;


public class ReorderReceiptActivity extends ActionBarActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reorder_receipt);
        Bundle bundle = getIntent().getExtras();
        List<ProductInfo> receiptList = bundle.getParcelableArrayList("productInfoList");
        Log.i(MyApp.LOGGING_TAG, "Loaded bundle from Intent");

        ListView listView = (ListView) findViewById(R.id.listView);
        ListAdapter adapter = new ArrayAdapter(this, R.layout.reorder_reciept_item, receiptList);
        listView.setAdapter(adapter);
        Log.i(MyApp.LOGGING_TAG, "ListView adapter has been set.");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_reorder_receipt, menu);
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
     * This function will take the user from the receipt page back to the home page when the user clicks on the button.
     * @param view The current view the user is looking at
     */
    public void switchToUpdateInventory(View view) {
        Intent switcheroo = new Intent(this, MainActivity.class);
        startActivity(switcheroo);
    }
}
