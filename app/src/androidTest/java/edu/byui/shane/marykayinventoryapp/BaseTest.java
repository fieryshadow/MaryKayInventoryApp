package edu.byui.shane.marykayinventoryapp;

import android.content.Context;
import android.test.InstrumentationTestCase;

/**
 * A way to get the database initialized while testing the app
 */
abstract public class BaseTest extends InstrumentationTestCase {
    public static Context appContext;

    public void setUp() {
        ProductDataSource.createSingleton(appContext);
    }
}
