package edu.byui.shane.marykayinventoryapp;

import android.util.Log;
import android.widget.Toast;

import junit.framework.AssertionFailedError;

/**
 * Tests that the InventoryManager is working correctly
 */
public class InventoryManagerTest extends BaseTest {
    /**
     * Check to see if products are correctly added to the inventory
     */
    public void testProcessCheckIn() {
        InventoryManager m = InventoryManager.getInstance();
        // check to make sure the product doesn't exist in the inventory
        assertNull(m.getProductInfo("imt1"));

        // add a new item
        Thread thread = m.processCheckIn("imt1", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new AssertionFailedError("Inventory manager check in thread failed unexpectedly.");
        }
        assertNotNull(m.getProductInfo("imt1_1"));
        assertEquals(3, m.getProductInfo("imt1_1").getNumberInStock());
        // check in a new item
        try {
            m.processCheckIn("imt1", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "").join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check to see if the number of items in the list is one larger than before.
        assertEquals(7, (m.getProductInfo("imt1_1").getNumberInStock()));
    }

    /**
     * Check to see if products are correctly removed from the inventory
     */
    public void testProcessCheckOut() {
        InventoryManager m = InventoryManager.getInstance();
        // not in inventory yet, test
        Thread thread = m.processCheckOut("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new AssertionFailedError("Inventory manager check out thread failed unexpectedly.");
        }
        // check to see that an entry (with none in stock) gets added to the inventory
        assertNotNull(m.getProductInfo("imt2_1"));
        assertEquals(0, m.getProductInfo("imt2_1").getNumberInStock());
        
        // need a few products to start with
        try {
            m.processCheckIn("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "").join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // remove 3 items from inventory
        try {
            m.processCheckOut("imt2", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "").join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // check to see if the number of items in the list is 1 less than before
        assertEquals(1, m.getProductInfo("imt2_1").getNumberInStock());
    }

    public void testProcessOrder() {
        InventoryManager m = InventoryManager.getInstance();
        Thread thread = m.processOrders("imt3", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 3, "");
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new AssertionFailedError("Inventory manager process order thread failed unexpectedly.");
        }
        assertNotNull(m.getProductInfo("imt3_1"));
        assertEquals(3, m.getProductInfo("imt3_1").getNumberOnOrder());
        try {
            m.processOrders("imt3", "Foundation", "Powder Puff", "Orange", 12.00f, 1, 4, "").join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertEquals(7, m.getProductInfo("imt3_1").getNumberOnOrder());
    }

    public void testGetSectionListing() {

    }

    public void testGetListing() {


    }

    public void testGetProductInfo() {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        String productKey = "12345_1";
        Thread thread = inventoryManager.processCheckIn("12345", "foundation", "liquid", "peach", 12.0f, 1, 2, null);
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        ProductInfo info = inventoryManager.getProductInfo(productKey);
        assertNotNull(info);
        assertEquals(info.getColor(), "peach");
        assertEquals(info.getSection(), 1);
        assertEquals(info.getProductNumber(), "12345");

    }

    public void testReadFromDatabase() {
        InventoryManager inventoryManager = InventoryManager.getInstance();
        String productKey = "12345_1";
        Thread thread = inventoryManager.processCheckIn("12345", "foundation", "liquid", "peach", 12.0f, 1, 2, null);
        try {
            thread.join();
        } catch (InterruptedException e) {
            Log.i(MyApp.LOGGING_TAG, "stupid");
            e.printStackTrace();
        }

        assertNotNull(inventoryManager.getListing());
        try {
            inventoryManager.readFromDatabase().join();
        } catch (InterruptedException e) {
            Log.i(MyApp.LOGGING_TAG, "really Stupid");
            e.printStackTrace();
        }
        assertNotNull(inventoryManager.getListing());
    }
}
