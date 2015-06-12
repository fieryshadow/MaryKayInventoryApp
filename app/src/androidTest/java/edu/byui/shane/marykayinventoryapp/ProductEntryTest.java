package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the ProductEntry class is functional
 */
public class ProductEntryTest extends InstrumentationTestCase {
    public void testGetProduct() { // Shane
        Product product = new Product(1234);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.getProduct(), product);
    }

    public void testIsOutOfStock() { // Shane
        Product product = new Product(1234);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.isOutOfStock(), true);
        productEntry = new ProductEntry(product, 1, 0, 0);
        assertEquals(productEntry.isOutOfStock(), false);
    }

    public void testGetNumberInStock() { // Shane
        Product product = new Product(1234);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.getNumberInStock(), 0);
        productEntry = new ProductEntry(product, 1, 0, 0);
        assertEquals(productEntry.getNumberInStock(), 1);
    }

    public void testGetNumberOnOrder() { // Greg

    }

    public void testGetHighestNumberInInventory() { // Greg

    }

    public void testCalculateInventoryValue() { // Greg

    }

    public void testCalculateTotalWorth() { // Ryan

    }

    public void testCalculateCostOf() { // Ryan

    }

    public void testReceivedItem() { // Shane

    }

    public void testSoldItem() { // Shane

    }

    public void testOrderedItem() { // Ryan

    }
}
