package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the InventoryManager is working correctly
 */
public class InventoryManagerTest extends InstrumentationTestCase {
    public void testGetListing() { // Anyone

    }

    public void testCheckIn() { // Greg
        // check to see if the product was correctly added to the list of products
        // add a new item to the list
        // check to see if the number of items in the list is one larger than before.
    }

    public void testCheckOut() { // Greg
        // check to see if the product was correctly removed from the list of products.
        // add a couple of items to the list
        // remove 1 item from the list
        // check to see if the number of items in the list is 1 less than before.
    }

    public void testOrderItem() { // Ryan

    }

    public void testUpdateProduct() { // Ryan

    }

    public void testAddProduct() { // Shane
        InventoryManager inventoryManager = new InventoryManager();
        assertEquals(inventoryManager.getProductInfo(1234), null);
        inventoryManager.addProduct(1234, "Eye Shadow", "bestStuff", "Orange", 12.34f);
        Product product = new Product(1234, "Eye Shadow", "bestStuff", "Orange", 12.34f);
        ProductInfo info = inventoryManager.getProductInfo(1234);
        assertEquals(product.getId(), info.getId());
        assertEquals(product.getGroup(), info.getGroup());
        assertEquals(product.getName(), info.getName());
        assertEquals(product.getColor(), info.getColor());
        assertEquals(product.getCost(), info.getCost());
    }

    public void testRemoveProduct() { // Shane
        InventoryManager inventoryManager = new InventoryManager();
        assertEquals(inventoryManager.getProductInfo(1234), null);
        inventoryManager.addProduct(1234, "Eye Shadow", "bestStuff", "Orange", 12.34f);
        inventoryManager.removeProduct(1234);
        assertEquals(inventoryManager.getProductInfo(1234), null);
    }

    public void testGetProductInfo() { // Anyone

    }
}
