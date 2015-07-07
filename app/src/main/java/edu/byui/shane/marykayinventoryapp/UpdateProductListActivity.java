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
    public static final String TAG_UPDATE = "TAG_UPDATE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_product_list);
        Intent intent = getIntent();
        String productNumber = intent.getStringExtra(UpdateInventoryActivity.EXTRA_MESSAGE);
        String productSection = intent.getStringExtra("Product Section");
        removeProduct = intent.getBooleanExtra("removeProduct", false);
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

        Log.i(TAG_UPDATE, "start loop through number of Products");

        Log.i(TAG_UPDATE, "remove Product = " + removeProduct);
        if (!removeProduct) {
            Log.i(TAG_UPDATE, "adding Product");
            inventoryManager.processCheckIn(ID.getText().toString(), category.getText().toString(), name.getText().toString(),
                    color.getText().toString(), cost.getAlpha(), section.getText().toString(), (int) numProduct.getAlpha());

            Log.i(TAG_UPDATE, "finished adding Product");
        }
        else {
            inventoryManager.removeProduct(ID.toString() + section.toString(), (int) numProduct.getAlpha());
        }


        Log.i(TAG_UPDATE, "exited loop through number of products");
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
