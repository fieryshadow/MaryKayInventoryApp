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

        removeProduct = intent.getBooleanExtra(UpdateInventoryActivity.EXTRA_REMOVED, false);

        EditText ProductID = (EditText) findViewById(R.id.ProductNumber);
        EditText ProductSection = (EditText) findViewById(R.id.ProductSection);

        ProductSection.setText(productSection);
        ProductID.setText(productNumber);

        String ProductKey = ProductCode.makeProductKey(productNumber, Integer.parseInt(productSection));

        if(inventoryManager.getProductInfo(ProductKey) != null){
            EditText ProductColor = (EditText) findViewById(R.id.color);
            EditText ProductName = (EditText) findViewById(R.id.ProductName);
            EditText ProductCost = (EditText) findViewById(R.id.ProductCost);
            EditText productCategory = (EditText) findViewById(R.id.ProductCategory);
            productCategory.setText(inventoryManager.getProductInfo(ProductKey).getCategory());
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
     * Submit takes all the parameters from the edit text boxes in the Activity Update Product List page.
     * and decides whether to add or remove the product based on what button was pushed in the previous activity.
     * @param view The thing a user is looking at!
     */
    public void submit(View view) {
        Log.v(MyApp.LOGGING_TAG, "Loading user input from text boxes in UpdateProductListActivity.submit");
        String productNumber = ((TextView) findViewById(R.id.ProductNumber)).getText().toString();
        String category = ((EditText) findViewById(R.id.ProductCategory)).getText().toString();
        String name = ((EditText) findViewById(R.id.ProductName)).getText().toString();
        int section = Integer.parseInt(((EditText) findViewById(R.id.ProductSection)).getText().toString());
        String color = ((EditText) findViewById(R.id.color)).getText().toString();
        int numProduct = Integer.parseInt(((EditText) findViewById(R.id.NumberOfProduct)).getText().toString());
        float cost = Float.parseFloat(((EditText) findViewById(R.id.ProductCost)).getText().toString());
        String imageFile = ((EditText) findViewById(R.id.ProductImageUpdater)).getText().toString();
        Log.w(MyApp.LOGGING_TAG, "Pulled input values: prod#->" + productNumber + ", cat->" + category +
                ", name->" + name + ", sec->" + section + ", col->" + color + ", #prod->" +
                numProduct + ", cost->" + cost + ", iFile->'" + imageFile + "' in UpdateProductListActivity.submit");

        Log.v(MyApp.LOGGING_TAG, "Variable removeProduct is: " + removeProduct + " in UpdateProductListActivity.submit");
        InventoryManager inventoryManager = InventoryManager.getInstance();
        Log.i(MyApp.LOGGING_TAG, "Submitting user input in UpdateProductListActivity.submit");
        if (!removeProduct) {
            Log.v(MyApp.LOGGING_TAG, "Adding product in UpdateProductListActivity.submit");
            inventoryManager.processCheckIn(productNumber, category, name, color, cost, section, numProduct, imageFile);
        } else {
            Log.i(MyApp.LOGGING_TAG, "Removing product in UpdateProductListActivity.submit");
            inventoryManager.processCheckOut(productNumber, category, name, color, cost, section, numProduct, imageFile);
        }

        Log.i(MyApp.LOGGING_TAG, "Sending you back to the home page in UpdateProductListActivity.submit");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
