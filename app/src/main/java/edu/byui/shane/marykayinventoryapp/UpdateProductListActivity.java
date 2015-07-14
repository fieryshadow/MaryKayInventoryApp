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

        String ProductKey = ProductCode.getProductKey(productNumber, productSection);

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

        EditText category = (EditText) findViewById(R.id.ProductCategory);
        EditText name = (EditText) findViewById(R.id.ProductName);
        EditText section = (EditText) findViewById(R.id.ProductSection);
        EditText color = (EditText) findViewById(R.id.color);
        EditText numProduct = (EditText) findViewById(R.id.NumberofProduct);
        EditText cost = (EditText) findViewById(R.id.ProductCost);
        TextView ID = (TextView) findViewById(R.id.ProductNumber);
        InventoryManager inventoryManager = InventoryManager.getInstance();

        // testing code...
        category.setText("Foundation");
        name.setText("Liquid");
        section.setText("A");
        color.setText("Peach");
        numProduct.setText("4");
        cost.setText("12.34");

        Log.i(MainActivity.TAG_FOR_APP, "start loop through number of Products");

        Log.i(MainActivity.TAG_FOR_APP, "remove Product = " + removeProduct);
        if (!removeProduct) {
            Log.i(MainActivity.TAG_FOR_APP, "adding Product");
            inventoryManager.processCheckIn(ID.getText().toString(), category.getText().toString(), name.getText().toString(),
                    color.getText().toString(), cost.getAlpha(), section.getText().toString(), (int) numProduct.getAlpha());

            Log.i(MainActivity.TAG_FOR_APP, "finished adding Product");
        }
        else {
            inventoryManager.processCheckOut(ID.toString() + section.toString(), (int) numProduct.getAlpha());
        }


        Log.i(MainActivity.TAG_FOR_APP, "exited loop through number of products");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
