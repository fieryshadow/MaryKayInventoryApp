package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Tests that the Product class is functional
 */
public class ProductTest extends InstrumentationTestCase {
    public void testName(){
        Product p = new Product();
        p.setName("Eye Liner");
        assertEquals("Eye Liner", p.getName());
    }

    public void testID() {

    }

    public void testCost() {

    }

}
