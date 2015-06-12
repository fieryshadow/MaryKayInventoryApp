package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the Product class is functional
 */

public class ProductTest extends InstrumentationTestCase {
    public void testName(){
        //create a new product
        Product p = new Product();
        //set the name of the product
        p.setName("Eye Liner");
        //check that the new products name is really correctly stored.
        assertEquals("Eye Liner", p.getName());
    }

    public void testID() {
        //create new product
        Product p = new Product();
        // set the id
        p.setId(12345);
        // chekc to make sure the id is correct.
        assertEquals(12345, p.getId());
    }

    public void testCost() {
        // Create new product
        Product p = new Product();
        // set the cost
        p.setCost(12.5f);
        // check to make sure the set worked and matches what is in the Product for cost.
        assertEquals(12.5f, p.getCost());
    }

}
