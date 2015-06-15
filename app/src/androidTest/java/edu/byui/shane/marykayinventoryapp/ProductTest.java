package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the Product class is functional
 */

public class ProductTest extends InstrumentationTestCase {
    public void testGetID() { // Greg
        //create new product
        Product p = new Product(1234);
        // check to make sure the id is correct.
        assertEquals(1234, p.getId());
    }

    public void testGroup() { //Ryan
        // test to make sure that a product is added to the correct group.
        Product p = new Product(99999);
        //Set the newly created product's group to lipstick
        p.setGroup("lipstick");
        //Ensure the group was successfully saved to the item.
        assertEquals("lipstick", p.getGroup());
    }


    public void testName(){
        //create a new product with id 12345
        Product p = new Product(12345);
        //set the name of the product
        p.setName("Eye Liner");
        //check that the new products name is really correctly stored.
        assertEquals("Eye Liner", p.getName());
    }

    public void testID() {
        //create new product with id 12345
        Product p = new Product(12345);
        // check to make sure the id is correct.
        assertEquals(12345, p.getId());
    }

    public void testColor(){ // Ryan
        //Create a new product
        Product p = new Product(99999);
        //Set the color to blue.
        p.setColor("blue");
        //Ensure that the color was set correctly.
        assertEquals(99999, p.getColor());
    }

    public void testCost() { // Greg
        // Create new product
        Product p = new Product(12345);
        // need to check that the initial cost == 0
        assertEquals(0.0f, p.getCost());
        // set the cost
        p.setCost(12.5f);
        // check to make sure the set worked and matches what is in the Product for cost.
        assertEquals(12.5f, p.getCost());
    }
}
