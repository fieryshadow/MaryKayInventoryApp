package edu.byui.shane.marykayinventoryapp;

import android.database.sqlite.SQLiteDatabase;

import java.util.Hashtable;

/**
 * Cleanly interfaces with the underlying database that stores everything
 */
public class DatabaseManager {
    // labels: id, group, name, cost, color, numberInStock, numberOnOrder, highestNumberInInventory
    private SQLiteDatabase table;

    public void updateWith(int barcode, ProductEntry data) {

    }

    public Hashtable<Integer, ProductEntry> readAll() {
        return null;
    }
}
