package edu.byui.shane.marykayinventoryapp;

import android.test.InstrumentationTestCase;

/**
 * Created by Greg on 6/10/2015.
 */
public class ProductTest extends InstrumentationTestCase {
    public void testName(){
        // put in asserts for product names.
        Product p = new Product();
        p.setName("Eye Liner");
        assertEquals("Eye Liner", p.getName());
    }

    public void testID() {

    }

    public void testCost() {

    }

}
