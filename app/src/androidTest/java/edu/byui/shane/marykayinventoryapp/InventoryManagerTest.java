package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the InventoryManager is working correctly
 */
public class InventoryManagerTest extends InstrumentationTestCase {
    /**
     * Check to see if products are correctly added to the inventory
     */
    public void testProcessCheckIn() {
        InventoryManager m = InventoryManager.getInstance();
        // check to make sure the product doesn't exist in the inventory
        assertEquals(null, m.getProductInfo("imt1"));

        // add a new item
        m.processCheckIn("imt1", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        assertEquals(3, m.getProductInfo("12345").getNumberInStock());
        // check in a new item
        m.processCheckIn("imt1", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "");
        // check to see if the number of items in the list is one larger than before.
        assertEquals(7, m.getProductInfo("imt1").getNumberInStock());
    }

    /**
     * Check to see if products are correctly removed from the inventory
     */
    public void testProcessCheckOut() {
        InventoryManager m = InventoryManager.getInstance();
        // not in inventory yet, test
        m.processCheckOut("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        // check to see that an entry (with none in stock) gets added to the inventory
        assertEquals(0, m.getProductInfo("imt2").getNumberInStock());
        
        // need a few products to start with
        m.processCheckIn("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "");
        // remove 3 items from inventory
        m.processCheckOut("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        // check to see if the number of items in the list is 1 less than before
        assertEquals(1, m.getProductInfo("imt2").getNumberInStock());
    }

    public void testProcessOrder() {
        InventoryManager m = InventoryManager.getInstance();
        m.processOrders("imt3", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        assertEquals(3, m.getProductInfo("imt3").getNumberOnOrder());
        m.processOrders("imt3", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "");
        assertEquals(7, m.getProductInfo("imt3").getNumberOnOrder());
    }

    public void testGetSectionListing() {

    }

    public void testGetListing() {

    }

    public void testGetProductInfo() {

    }

    public void testReadFromDatabase() {

    }
}
