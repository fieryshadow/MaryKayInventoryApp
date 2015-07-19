package edu.byui.shane.marykayinventoryapp;

import android.app.Application;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;


import java.util.logging.LogRecord;

/**
 * A place where code can be executed only once.
 */
public class MyApp extends Application {
    public static String LOGGING_TAG = "MaryKayStuffAndThings";


    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(MyApp.LOGGING_TAG, "Startup of App successful in MyApp.onCreate");

        ProductDataSource.createSingleton(getApplicationContext());
        Log.v(MyApp.LOGGING_TAG, "Starting to read database in MyApp.onCreate");
        InventoryManager.getInstance().readFromDatabase();
        Log.i(MyApp.LOGGING_TAG, "Finished reading database in MyApp.onCreate");
        InventoryManager.createSingleton(getApplicationContext());
    }

}
