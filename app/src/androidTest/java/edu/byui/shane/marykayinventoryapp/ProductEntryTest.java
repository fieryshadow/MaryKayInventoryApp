package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the ProductEntry class is functional
 */
public class ProductEntryTest extends InstrumentationTestCase {
    public void testConstructionAndGettersSetters() {
        Product product1 = new Product("pet1-0", 1);
        Product product2 = new Product("pet1-1", 2);

        // getter check 1
        ProductEntry entry = new ProductEntry(product1, 0, 0, 0);
        assertEquals(entry.getProduct(), product1);
        assertEquals(entry.getNumberInStock(), 0);
        assertEquals(entry.getNumberOnOrder(), 0);
        assertEquals(entry.getHighestNumberInInventory(), 0);

        // getter check 2
        entry = new ProductEntry(product2, 1, 2, 3);
        assertEquals(entry.getProduct(), product2);
        assertEquals(entry.getNumberInStock(), 1);
        assertEquals(entry.getNumberOnOrder(), 2);
        assertEquals(entry.getHighestNumberInInventory(), 3);

        // check setters
        entry.setNumberInStock(4);
        entry.setNumberOnOrder(5);
        assertEquals(entry.getNumberInStock(), 4);
        assertEquals(entry.getNumberOnOrder(), 5);
        assertEquals(entry.getHighestNumberInInventory(), 4);
    }

    public void testIsOutOfStock() {
        Product product = new Product("pet2", 1);
        ProductEntry productEntry = new ProductEntry(product, 0, 0, 0);
        assertEquals(productEntry.isOutOfStock(), true);
        productEntry = new ProductEntry(product, 1, 0, 0);
        assertEquals(productEntry.isOutOfStock(), false);
    }

    public void testCalculateInventoryValue() {
        ProductEntry productEntry = new ProductEntry(new Product("pet3", "Foundation", "Top Dog", 2, "Black", 33.33f), 3, 4, 5);
        assertEquals(99.99f, productEntry.getInventoryValue());
    }

    public void testCalculateTotalWorth() {
        ProductEntry productEntry = new ProductEntry(new Product("pet4", "Foundation", "Top Dog", 2, "Black", 33.33f), 3, 4, 5);
        assertEquals(233.31f, productEntry.getTotalWorth());
    }
}
