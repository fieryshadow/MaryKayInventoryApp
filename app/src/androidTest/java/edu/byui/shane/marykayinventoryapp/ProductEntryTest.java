package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the ProductEntry class is functional
 */
public class ProductEntryTest extends InstrumentationTestCase {
    public void testConstructionAndGetters() { // Shane
        Product product1 = new Product("1234", 1);
        Product product2 = new Product("5678", 1);
        // should we throw some exceptions for passing negative values?

        ProductEntry entry = new ProductEntry(product1, 0, 0, 0);
        assertEquals(entry.getProduct(), product1);
        assertEquals(entry.getNumberInStock(), 0);
        assertEquals(entry.getNumberOnOrder(), 0);
        assertEquals(entry.getHighestNumberInInventory(), 0);

        entry = new ProductEntry(product2, 1, 2, 3);
        assertEquals(entry.getProduct(), product2);
        assertEquals(entry.getNumberInStock(), 1);
        assertEquals(entry.getNumberOnOrder(), 2);
        assertEquals(entry.getHighestNumberInInventory(), 3);
    }

    public void testIsOutOfStock() { // Shane
        Product product = new Product("1234", 1);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.isOutOfStock(), true);
        productEntry = new ProductEntry(product, 1, 0, 0);
        assertEquals(productEntry.isOutOfStock(), false);
    }

    public void testCalculateInventoryValue() { // Greg

    }

    public void testCalculateTotalWorth() { // Greg

    }

    /* // Broken by changed API. Please update API usages to continue.
    public void testReceivedItem() { // Shane
        Product product = new Product("1234");
        ProductEntry productEntry = new ProductEntry(product, 0, 1, 2);

        // move on order items to in stock
        productEntry.receivedItem();
        assertEquals(productEntry.getNumberInStock(), 1);
        assertEquals(productEntry.getNumberOnOrder(), 0);
        assertEquals(productEntry.getHighestNumberInInventory(), 2);

        // or just add item to the in stock
        productEntry.receivedItem();
        assertEquals(productEntry.getNumberInStock(), 2);
        assertEquals(productEntry.getNumberOnOrder(), 0);
        assertEquals(productEntry.getHighestNumberInInventory(), 2);

        // make sure highest value is updated properly
        productEntry.receivedItem();
        assertEquals(productEntry.getNumberInStock(), 3);
        assertEquals(productEntry.getNumberOnOrder(), 0);
        assertEquals(productEntry.getHighestNumberInInventory(), 3);
    }

    public void testSoldItem() { // Shane
        Product product = new Product("1234");
        ProductEntry productEntry = new ProductEntry(product, 1, 0, 1);

        // check that it updates things properly
        productEntry.soldItem();
        assertEquals(productEntry.getNumberInStock(), 0);
        assertEquals(productEntry.getNumberOnOrder(), 0);
        assertEquals(productEntry.getHighestNumberInInventory(), 1);

        // somehow there was more in the inventory than we thought, so update the highest value
        productEntry.soldItem();
        assertEquals(productEntry.getNumberInStock(), 0);
        assertEquals(productEntry.getNumberOnOrder(), 0);
        assertEquals(productEntry.getHighestNumberInInventory(), 2);
    }
    //*/
}
