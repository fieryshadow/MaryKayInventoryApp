package edu.byui.shane.marykayinventoryapp;

import android.app.Application;
import android.util.Log;

/**
 * A place where code can be executed only once.
 */
public class MyApp extends Application {
    public static String LOGGING_TAG = "MaryKayStuffAndThings";

    @Override
    public void onCreate() {
        super.onCreate();

        ProductDataSource.createSingleton(getApplicationContext());
        InventoryManager.createSingleton(getApplicationContext());
        Log.v(MyApp.LOGGING_TAG, "Starting to read database in MainActivity.onCreate ...");
        InventoryManager.getInstance().readFromDatabase();
        Log.i(MyApp.LOGGING_TAG, "Finished reading database in MainActivity.onCreate");
    }
}
