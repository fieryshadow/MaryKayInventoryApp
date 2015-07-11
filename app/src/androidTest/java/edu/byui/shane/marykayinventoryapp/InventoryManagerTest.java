package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the InventoryManager is working correctly
 */
public class InventoryManagerTest extends InstrumentationTestCase {
    public void testGetListing() {

    }

    /* The API has been updated since this was written. This needs to be tested indirectly.
    public void testProcessCheckIn() {
        // check to see if the product was correctly added to the list of products
        // add a new item to the list
        InventoryManager m = InventoryManager.getInstance();
        //check to make sure the Listing is at 0 on a new created object of InventoryManager
        assertEquals(0, m.getProductInfo("12345").getNumberInStock());

        // Check in a new item
        m.checkInItem("12345");
        // set size to number of in stock items
        int size = m.getProductInfo("12345").getNumberInStock();
        // check in a new item
        m.checkInItem("12345");
        // check to see if the number of items in the list is one larger than before.
        assertEquals(size + 1, m.getProductInfo("12345").getNumberInStock());
    }
    */

    /* This needs to be updated to the correct API!
    public void testProcessCheckOut() {
        // check to see if the product was correctly removed from the list of products.
        InventoryManager m = InventoryManager.getInstance();
        // check to make sure the listing is empty
        assertEquals(0, m.getProductInfo("12345").getNumberInStock());
        // add a couple of items to the list
        m.checkInItem("12345");
        m.checkInItem("12345");

        //set the size to the size of the list to compare after removing 1.
        int size = m.getProductInfo("12345").getNumberInStock();

        // remove 1 item from the list
        m.processCheckOut("12345", 1);

        // check to see if the number of items in the list is 1 less than before.
        assertEquals(size - 1, m.getSectionListing(InventoryManager.section1).size());
    }
    */

    public void testProcessOrder() {

    }

    public void testAddProduct() { // probably needs moving to testProcessCheckIn...
        InventoryManager inventoryManager = InventoryManager.getInstance();
        assertEquals(inventoryManager.getProductInfo("1234"), null);
        inventoryManager.processCheckIn("1234123", "Eye Shadow", "liquid", "Orange", 12.34f, "A", 3, null);
        Product product = new Product("1234123", "Eye Shadow", "bestStuff", "Orange", "A", 12.34f);
        ProductInfo info = inventoryManager.getProductInfo("1234");
        assertEquals(product.getId(), info.getId());
        assertEquals(product.getGroup(), info.getGroup());
        assertEquals(product.getName(), info.getName());
        assertEquals(product.getColor(), info.getColor());
        assertEquals(product.getCost(), info.getCost());
    }

    public void testRemoveProduct() { // probably needs moving to testProcessCheckOut
        InventoryManager inventoryManager = InventoryManager.getInstance();
        assertEquals(inventoryManager.getProductInfo("1234"), null);
        inventoryManager.processCheckIn("1234", "Eye Shadow", "bestStuff", "Orange", 12.34f, "A", 2, null);
        inventoryManager.processCheckOut("1234", "Eye Shadow", "bestStuff", "Orange", 12.34f, "A", 1, null);
        assertEquals(inventoryManager.getProductInfo("1234"), null);
    }

    public void testGetProductInfo() {

    }
}
