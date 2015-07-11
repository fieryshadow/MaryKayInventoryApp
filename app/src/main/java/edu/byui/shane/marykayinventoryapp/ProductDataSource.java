package edu.byui.shane.marykayinventoryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.util.Hashtable;

/**
 * Cleanly interfaces with the underlying database that stores everything
 */
public class ProductDataSource {
    private SQLiteDatabase database;
    private MySQLiteHelper dbHelper;
    private String[] allColumns = { MySQLiteHelper.COLUMN_ID, MySQLiteHelper.COLUMN_PRODUCT_ID,
        MySQLiteHelper.COLUMN_CATEGORY, MySQLiteHelper.COLUMN_NAME, MySQLiteHelper.COLUMN_SECTION,
        MySQLiteHelper.COLUMN_COLOR, MySQLiteHelper.COLUMN_COST, MySQLiteHelper.COLUMN_NUMBER_IN_STOCK,
        MySQLiteHelper.COLUMN_NUMBER_ON_ORDER, MySQLiteHelper.COLUMN_HIGHEST_NUMBER_IN_STOCK,
        MySQLiteHelper.COLUMN_IMAGE };
    private static ProductDataSource dataSource;
    private static Context appContext;

    private ProductDataSource(Context context) {
        dbHelper = new MySQLiteHelper(context);
    }

    /**
     * A workaround for needing the activity context for parts of the code to work correctly. Must be
     * called at the very beginning of the program startup, or else a NullPointerException will occur!
     * @param context Needs to be the MainActivity! Not for calling anywhere else!
     */
    public static void createSingleton(Context context) {
        if (dataSource == null) {
            appContext = context;
            dataSource = new ProductDataSource(appContext);
        }
    }

    /** This Is Singleton!! */
    public static ProductDataSource getInstance() {
        return dataSource;
    }

    private void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    private void close() {
        dbHelper.close();
    }

    /** Converts a database cursor into a product entry */
    private ProductEntry cursor2ProductEntry(Cursor cursor) {
        byte[] imageBytes = cursor.getBlob(10);
        Bitmap bitMapImage = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.length);
        Product product = new Product(cursor.getString(1), cursor.getString(2), cursor.getString(3),
                cursor.getString(4), cursor.getString(5), cursor.getFloat(6));
        product.setImage(bitMapImage);
        return new ProductEntry(product, cursor.getInt(7), cursor.getInt(8), cursor.getInt(9));
    }

    /** Takes a product entry and packs it into a database readable format */
    @Nullable
    private ContentValues packValues(ProductEntry item) {
        if (item == null) {
            Log.wtf(MainActivity.TAG_FOR_APP, "How are you storing nothing to the database!?", new Throwable("You Suck!"));
            return null;
        }
        ProductInfo info = item.getInfo();
        ContentValues values = new ContentValues();

        values.put(MySQLiteHelper.COLUMN_PRODUCT_ID, info.getId());
        values.put(MySQLiteHelper.COLUMN_CATEGORY, info.getGroup());
        values.put(MySQLiteHelper.COLUMN_NAME, info.getName());
        values.put(MySQLiteHelper.COLUMN_SECTION, info.getSection());
        values.put(MySQLiteHelper.COLUMN_COLOR, info.getColor());
        values.put(MySQLiteHelper.COLUMN_COST, info.getCost());
        values.put(MySQLiteHelper.COLUMN_NUMBER_IN_STOCK, info.getNumberInStock());
        values.put(MySQLiteHelper.COLUMN_NUMBER_ON_ORDER, info.getNumberOnOrder());
        values.put(MySQLiteHelper.COLUMN_HIGHEST_NUMBER_IN_STOCK, info.getHighestNumberInInventory());

        Bitmap image = info.getImage();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        values.put(MySQLiteHelper.COLUMN_IMAGE, stream.toByteArray());

        return values;
    }

    /**
     * Takes a product entry and stores it into the database
     * @param item The product entry you need to store
     */
    public void storeProduct(ProductEntry item) {
        open();
        ContentValues values = packValues(item);
        Log.v(MainActivity.TAG_FOR_APP, "Deleting database entry... in ProductDataSource.storeProduct");
        database.delete(MySQLiteHelper.TABLE_PRODUCTS,
                MySQLiteHelper.COLUMN_PRODUCT_ID + " = \"" + item.getInfo().getId() + "\"", null);
        Log.v(MainActivity.TAG_FOR_APP, "Adding database entry... in ProductDataSource.storeProduct");
        database.insert(MySQLiteHelper.TABLE_PRODUCTS, null, values);
        Log.v(MainActivity.TAG_FOR_APP, "Closing database... in ProductDataSource.storeProduct");
        close();
    }

    /**
     * Retrieves the product entry associated with the product ID from the database
     * @param productCode A compiled product code
     * @return Returns the product entry
     */
    public ProductEntry getProduct(String productCode) {
        open();
        String id = ProductCode.getId(productCode);
        String section = ProductCode.getSection(productCode);
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS, allColumns,
                MySQLiteHelper.COLUMN_PRODUCT_ID + " = " + id + " and " +
                        MySQLiteHelper.COLUMN_SECTION + " = " + section, null, null, null, null);
        cursor.moveToFirst();
        ProductEntry productEntry = cursor2ProductEntry(cursor);
        cursor.close();
        close();
        return productEntry;
    }

    /**
     * Reads all product entries from the database and returns them
     * @return Returns a table of product entries
     */
    public Hashtable<String, ProductEntry> readAllProducts() {
        open();
        Hashtable<String, ProductEntry> products = new Hashtable<>();
        Log.v(MainActivity.TAG_FOR_APP, "Prepare to die, we're making a cursor! in ProductDataSource.readAllProducts");
        Cursor cursor = database.query(MySQLiteHelper.TABLE_PRODUCTS,
                allColumns, null, null, null, null, null);

        Log.v(MainActivity.TAG_FOR_APP, "Beginning database read... in ProductDataSource.readAllProducts");
        cursor.moveToFirst();
        ProductEntry productEntry;
        while (!cursor.isAfterLast()) {
            productEntry = cursor2ProductEntry(cursor);
            products.put(ProductCode.makeProductKey(productEntry), productEntry);
            cursor.moveToNext();
        }

        cursor.close();
        Log.i(MainActivity.TAG_FOR_APP, "Finished reading the database. in ProductDataSource.readAllProducts");
        close();
        return products;
    }
}


class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String TABLE_PRODUCTS = "products";
    public static final String COLUMN_ID = "_id"; // database row, not product id!
    public static final String COLUMN_PRODUCT_ID = "productCode";
    public static final String COLUMN_CATEGORY = "category";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_COLOR = "color";
    public static final String COLUMN_SECTION = "section";
    public static final String COLUMN_COST = "cost";
    public static final String COLUMN_NUMBER_IN_STOCK = "inStockNum";
    public static final String COLUMN_NUMBER_ON_ORDER = "onOrderNum";
    public static final String COLUMN_HIGHEST_NUMBER_IN_STOCK = "greatestNum";
    public static final String COLUMN_IMAGE = "image";

    // increment table version whenever database columns change, but also,
    // make the onUpgrade method convert the old database to the new one.
    public static final int TABLE_VERSION = 2;
    public static final String DATABASE_NAME = "products.db";

    private static final String DATABASE_CREATE = "create table " + TABLE_PRODUCTS + "(" +
            COLUMN_ID + " integer primary key autoincrement, " +
            COLUMN_PRODUCT_ID + " text not null, " +
            COLUMN_CATEGORY + " text not null, " +
            COLUMN_NAME + " text not null, " +
            COLUMN_COLOR + " text not null, " +
            COLUMN_SECTION + " text not null, " +
            COLUMN_COST + " real not null, " +
            COLUMN_NUMBER_IN_STOCK + " integer not null, " +
            COLUMN_NUMBER_ON_ORDER + " integer not null, " +
            COLUMN_HIGHEST_NUMBER_IN_STOCK + " integer not null, " +
            COLUMN_IMAGE + " blob);";

    /** Makes the proper database */
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
