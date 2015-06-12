package edu.byui.shane.marykayinventoryapp;

import android.database.sqlite.SQLiteDatabase;

import java.util.Hashtable;

/**
 * Cleanly interfaces with the underlying database that stores everything
 */
public class DatabaseManager {
    // labels: id, group, name, cost, color, numberInStock, numberOnOrder, highestNumberInInventory
    private SQLiteDatabase table;

    public DatabaseManager(String databaseLocation) {
        // setup the database
    }

    public void updateWithItem(int barcode, ProductEntry item) {

    }

    public Hashtable<Integer, ProductEntry> readAll() {
        return null;
    }

    public void writeAll(Hashtable<Integer, ProductEntry> data) {

    }
}
