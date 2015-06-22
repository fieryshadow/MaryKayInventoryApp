package edu.byui.shane.marykayinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.Hashtable;

/**
 * Cleanly interfaces with the underlying database that stores everything
 */
public class ProductDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_PRODUCT_CODE,
        MySQLiteHelper.COLUMN_CATEGORY, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_SECTION,
        MySQLiteHelper.COLUMN_COLOR, MySQLiteHelper.COLUMN_COST, MySQLiteHelper.COLUMN_NUMBER_IN_STOCK,
        MySQLiteHelper.COLUMN_NUMBER_ON_ORDER, MySQLiteHelper.COLUMN_HIGHEST_NUMBER_IN_STOCK };

    public ProductDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    private ProductEntry cursor2ProductEntry(Cursor cursor) {
        Product product = new Product(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getFloat(6));
        return new ProductEntry(product, cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));
    }

    private ContentValues packValues(ProductEntry item) {
        ProductInfo info = item.getInfo();
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COLUMN_PRODUCT_CODE, info.getId());
        values.put(MySQLiteHelper.COLUMN_CATEGORY, info.getGroup());
        values.put(MySQLiteHelper.COLUMN_NAME, info.getName());
        values.put(MySQLiteHelper.COLUMN_SECTION, info.getSection());
        values.put(MySQLiteHelper.COLUMN_COLOR, info.getColor());
        values.put(MySQLiteHelper.COLUMN_COST, info.getCost());
        values.put(MySQLiteHelper.COLUMN_NUMBER_IN_STOCK, info.getNumberInStock());
        values.put(MySQLiteHelper.COLUMN_NUMBER_ON_ORDER, info.getNumberOnOrder());
        values.put(MySQLiteHelper.COLUMN_HIGHEST_NUMBER_IN_STOCK, info.getHighestNumberInInventory());
        return values;
    }

    public void storeProduct(ProductEntry item) {
        open();
        ContentValues values = packValues(item);
        database.delete(MySQLiteHelper.TABLE_PRODUCTS,
                MySQLiteHelper.COLUMN_PRODUCT_CODE + " = " + item.getInfo().getId(), null);
        database.insert(MySQLiteHelper.TABLE_PRODUCTS, null, values);
        close();
    }

    public ProductEntry getProduct(String barcode) {
        open();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS, allColumns,
                MySQLiteHelper.COLUMN_PRODUCT_CODE + " = " + barcode, null, null, null, null);
        cursor.moveToFirst();
        ProductEntry productEntry = cursor2ProductEntry(cursor);
        cursor.close();
        close();
        return productEntry;
    }

    public Hashtable<String, ProductEntry> readAllProducts() {
        Hashtable<String, ProductEntry> products = new Hashtable<>();
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        ProductEntry productEntry;
        while (!cursor.isAfterLast()) {
            productEntry = cursor2ProductEntry(cursor);
            products.put(productEntry.getInfo().getId(), productEntry);
            cursor.moveToNext();
        }

        cursor.close();
        close();
        return products;
    }

    public void storeAllProducts(Hashtable<String, ProductEntry> data) {
        // is this needed?
    }
}


class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id"; // database row, not product id!
    public static final String COLUMN_PRODUCT_CODE = "productCode";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_SECTION = "section";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_NUMBER_IN_STOCK = "inStockNum";
    public static final String COLUMN_NUMBER_ON_ORDER = "onOrderNum";
    public static final String COLUMN_HIGHEST_NUMBER_IN_STOCK = "greatestNum";

    // increment table version whenever database columns change, but also,
    // make the onUpgrade method convert the old database to the new one.
    public static final int TABLE_VERSION = 1;
    public static final String DATABASE_NAME = "products.db";

    private static final String DATABASE_CREATE = "create table " + TABLE_PRODUCTS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_PRODUCT_CODE + " text not null, " +
            COLUMN_CATEGORY + " text not null, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_COLOR + " text not null, " +
            COLUMN_SECTION + " text not null, " +
            COLUMN_COST + " real not null, " +
            COLUMN_NUMBER_IN_STOCK + " integer not null, " +
            COLUMN_NUMBER_ON_ORDER + " integer not null, " +
            COLUMN_HIGHEST_NUMBER_IN_STOCK + " integer not null);";

    public MySQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, TABLE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", destroying all old data");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
        onCreate(db);
    }
}
