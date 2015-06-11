package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Created by Greg on 6/10/2015.
 */
public class ProductEntryTest extends InstrumentationTestCase {
    public void testProduct() {
        ProductEntry productEntry = new ProductEntry();
        assert productEntry.getProduct() == null;
        Product product = new Product();
        productEntry.setProduct(product);
        assert productEntry.getProduct() == product;
    }

    public void testNumberOfProduct() {
        ProductEntry productEntry = new ProductEntry();
        assert productEntry.getNumberOfProduct() == 0;
        productEntry.setNumberOfProduct(5);
        assert productEntry.getNumberOfProduct() == 5;
    }

    public void testDefaultValue() {
        // Shane
    }

    public void testCalculateTotalCost() {
        // Shane
    }
}
