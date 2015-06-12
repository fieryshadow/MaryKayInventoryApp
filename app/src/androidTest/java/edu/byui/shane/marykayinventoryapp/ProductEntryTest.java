package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the ProductEntry class is functional
 */
public class ProductEntryTest extends InstrumentationTestCase {
    public void testProduct() {
        Product product = new Product(1234);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.getProduct(), product);
    }

    public void testNumberInStock() {
        Product product = new Product(1234);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.getNumberInStock(), 0);
        productEntry.receivedItem();
        assertEquals(productEntry.getNumberInStock(), 1);
    }

    public void testHighestNumberInInventory() {
        // Greg
    }

    public void testCalculateInventoryValue() {
        // Ryan
    }
}
