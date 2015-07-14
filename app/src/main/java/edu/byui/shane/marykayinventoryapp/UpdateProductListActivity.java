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

        InventoryManager inventoryManager = InventoryManager.getInstance();

        Intent intent = getIntent();

        String productNumber = intent.getStringExtra(UpdateInventoryActivity.EXTRA_MESSAGE);
        String productSection = intent.getStringExtra("Product Section");

        removeProduct = intent.getBooleanExtra("processCheckOut", false);

        EditText ProductID = (EditText) findViewById(R.id.ProductNumber);
        EditText ProductSection = (EditText) findViewById(R.id.ProductSection);

        ProductSection.setText(productSection);
        ProductID.setText(productNumber);

        String ProductKey = ProductCode.makeProductKey(productNumber, productSection);

        if(inventoryManager.getProductInfo(ProductKey) != null){
            EditText ProductColor = (EditText) findViewById(R.id.color);
            EditText ProductName = (EditText) findViewById(R.id.ProductName);
            EditText ProductCost = (EditText) findViewById(R.id.ProductCost);
            EditText productCategory = (EditText) findViewById(R.id.ProductCategory);
            productCategory.setText(inventoryManager.getProductInfo(ProductKey).getGroup());
            ProductColor.setText(inventoryManager.getProductInfo(ProductKey).getColor());
            ProductName.setText(inventoryManager.getProductInfo(ProductKey).getName());
            ProductCost.setText(Float.toString(inventoryManager.getProductInfo(ProductKey).getCost()));
        }
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
        Log.i(MyApp.LOGGING_TAG, "Submitting user input in UpdateProductListActivity.submit");
        Log.v(MyApp.LOGGING_TAG, "Load user input from text boxes in UpdateProductListActivity.submit");
        // these variables need to be final for them to be sent to threads properly
        final String productNumber = ((TextView) findViewById(R.id.ProductNumber)).getText().toString();
        final String category = ((EditText) findViewById(R.id.ProductCategory)).getText().toString();
        final String name = ((EditText) findViewById(R.id.ProductName)).getText().toString();
        final int section = Integer.parseInt(((EditText) findViewById(R.id.ProductSection)).getText().toString());
        final String color = ((EditText) findViewById(R.id.color)).getText().toString();
        final int numProduct = Integer.parseInt(((EditText) findViewById(R.id.NumberOfProduct)).getText().toString());
        final float cost = Float.parseFloat(((EditText) findViewById(R.id.ProductCost)).getText().toString());
        final String imageFile = ((EditText) findViewById(R.id.ProductImageUpdater)).getText().toString();
        Log.v(MyApp.LOGGING_TAG, "Values: prod#->" + productNumber + ", cat->" + category +
                        ", name->" + name + ", sec->" + section + ", col->" + color + ", #prod->" +
                        numProduct + ", cost->" + cost + ", iFile->" + imageFile + "... in UpdateProductListActivity.submit");

        Log.v(MyApp.LOGGING_TAG, "Remove Product == " + removeProduct + " in UpdateProductListActivity.submit");
        new Thread(new Runnable() {
            @Override
            public void run() {
                InventoryManager inventoryManager = InventoryManager.getInstance();
                if (!removeProduct) {
                    Log.v(MyApp.LOGGING_TAG, "Adding Product in UpdateProductListActivity.submit.thread");
                    inventoryManager.processCheckIn(productNumber, category, name, color, cost, section, numProduct, imageFile);
                    Log.i(MyApp.LOGGING_TAG, "Finished adding Product in UpdateProductListActivity.submit.thread");
                } else {
                    inventoryManager.processCheckOut(productNumber, category, name, color, cost, section, numProduct, imageFile);
                }
            }
        }).start();

        Log.i(MyApp.LOGGING_TAG, "Sending you back to the home page in UpdateProductListActivity.submit");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
