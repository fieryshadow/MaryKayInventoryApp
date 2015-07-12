package edu.byui.shane.marykayinventoryapp;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * UpdateProductListActivity will either remove a product, add a product, or simply update information about the product.
 */
public class UpdateProductListActivity extends ActionBarActivity {
    private boolean removeProduct;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_list);
        Intent intent = getIntent();
        String productNumber = intent.getStringExtra(UpdateInventoryActivity.EXTRA_MESSAGE);
        String productSection = intent.getStringExtra("Product Section");
        removeProduct = intent.getBooleanExtra("processCheckOut", false);
        TextView ProductID = (TextView) findViewById(R.id.ProductNumber);
        EditText ProductSection = (EditText) findViewById(R.id.ProductSection);
        ProductSection.setText(productSection);
        ProductID.setTextSize(20);
        ProductID.setText(UpdateInventoryActivity.EXTRA_MESSAGE + productNumber);
        InventoryManager inventoryManager = InventoryManager.getInstance();
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


    /**
     * @param view
     * Submit takes all the parameters from the edit text boxes in the Activity Update Product List page.
     * and decides whether to add or remove the product based on what button was pushed in the previous activity.
     */
    public void submit(View view) {
        Log.i(MainActivity.TAG_FOR_APP, "Load user input from text boxes in UpdateProductListActivity.submit");
        // these need to be final for them to be sent to threads
        final String id = ((TextView) findViewById(R.id.ProductNumber)).getText().toString();
        final String category = ((EditText) findViewById(R.id.ProductCategory)).getText().toString();
        final String name = ((EditText) findViewById(R.id.ProductName)).getText().toString();
        final int sectionId = (int) findViewById(R.id.ProductSection).getAlpha();
        final String color = ((EditText) findViewById(R.id.color)).getText().toString();
        final int numProduct = (int) findViewById(R.id.NumberofProduct).getAlpha();
        final float cost = findViewById(R.id.ProductCost).getAlpha();
        final String imageFile = ((EditText) findViewById(R.id.ProductImageUpdater)).getText().toString();

        Log.i(MainActivity.TAG_FOR_APP, "Parse which section was selected in UpdateProductListActivity.submit");
        final String section;
        if (sectionId == 1) {
            section = InventoryManager.section1;
        } else {
            section = InventoryManager.section2;
        }

        // testing code...
//        category.setText("Foundation");
//        name.setText("Liquid");
//        section.setText("A");
//        color.setText("Peach");
//        numProduct.setText("4");
//        cost.setText("12.34");

        Log.i(MainActivity.TAG_FOR_APP, "Remove Product == " + removeProduct + " in UpdateProductListActivity.submit");
        new Thread(new Runnable() {
            @Override
            public void run() {
                InventoryManager inventoryManager = InventoryManager.getInstance();
                if (!removeProduct) {
                    Log.i(MainActivity.TAG_FOR_APP, "Adding Product in UpdateProductListActivity.submit");
                    inventoryManager.processCheckIn(id, category, name, color, cost, section, numProduct, imageFile);
                    Log.i(MainActivity.TAG_FOR_APP, "Finished adding Product in UpdateProductListActivity.submit");
                } else {
                    inventoryManager.processCheckOut(id, category, name, color, cost, section, numProduct, imageFile);
                }
            }
        }).start();

        Log.i(MainActivity.TAG_FOR_APP, "Sending you back to the home page in UpdateProductListActivity.submit");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
