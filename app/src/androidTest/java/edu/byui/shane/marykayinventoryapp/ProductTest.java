package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the Product class is functional
 */

public class ProductTest extends InstrumentationTestCase {
    public void testGetID() { // Greg
        //create new product
        Product p = new Product("1234", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        // check to make sure the id is correct.
        assertEquals("1234", p.getProductNumber());
    }

    public void testGroup() { //Ryan
        // test to make sure that a product is added to the correct group.
        Product p = new Product("123", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        //Set the newly created product's group to lipstick
        p.setCategory("lipstick");
        //Ensure the group was successfully saved to the item.
        assertEquals("lipstick", p.getCategory());
    }


    public void testName(){
        //create a new product with id 12345
        Product p = new Product("123", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        //set the name of the product
        p.setName("Eye Liner");
        //check that the new products name is really correctly stored.
        assertEquals("Eye Liner", p.getName());
    }

    public void testID() {
        //create new product with id 12345
        Product p = new Product("12345", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        // check to make sure the id is correct.
        assertEquals("12345", p.getProductNumber());
    }

    public void testColor(){ // Ryan
        //Create a new product
        Product p = new Product("123", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        //Set the color to blue.
        p.setColor("blue");
        //Ensure that the color was set correctly.
        assertEquals("blue", p.getColor());
    }

    public void testCost() { // Greg
        // Create new product
        Product p = new Product("123", "Eye Shadow", "liquid", 1, "Orange", 12.3f);
        // need to check that the initial cost == 0
        assertEquals(0.00f, p.getCost());
        // set the cost
        p.setCost(12.50f);
        // check to make sure the set worked and matches what is in the Product for cost.
        assertEquals(12.50f, p.getCost());
    }
}
