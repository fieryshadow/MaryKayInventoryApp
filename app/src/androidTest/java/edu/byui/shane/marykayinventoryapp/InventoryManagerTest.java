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
        InventoryManager m = new InventoryManager();
        //check to make sure the Listing is at 0 on a new created object of InventoryManager
        assertEquals(0, m.getListing().size());
        m.checkInItem(12345);

        int size = m.getListing().size();
        m.checkInItem(123456);
        // check to see if the number of items in the list is one larger than before.
        assertEquals(size + 1, m.getListing().size());

    }

    public void testCheckOut() { // Greg
        // check to see if the product was correctly removed from the list of products.
        InventoryManager m = new InventoryManager();
        // check to make sure the listing is empty
        assertEquals(0, m.getListing().size());
        // add a couple of items to the list
        m.checkInItem(12345);
        m.checkInItem(123456);

        //set the size to the size of the list to compare after removing 1.
        int size = m.getListing().size();

        // remove 1 item from the list
        m.checkOutItem(12345);

        // check to see if the number of items in the list is 1 less than before.
        assertEquals(size - 1, m.getListing().size());
    }

    public void testOrderItem() { // Ryan

    }

    public void testUpdateProduct() { // Ryan

    }

    public void testAddProduct() { // Shane

    }

    public void testRemoveProduct() { // Shane

    }

    public void testGetProductInfo() { // Anyone

    }
}
