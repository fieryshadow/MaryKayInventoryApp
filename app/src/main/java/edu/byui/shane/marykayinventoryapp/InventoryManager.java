package edu.byui.shane.marykayinventoryapp;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Manages multiple lists of Products and their amounts and running costs, as well as
 * what's in the inventory, out of stock, on order, etc.
 */
public class InventoryManager {
    private static Context appContext;
    private Hashtable<String, ProductEntry> inventory; // barcode: ProductEntry

    private static InventoryManager manager; // final...
    private InventoryManager() {
        inventory = new Hashtable<>();
    }
    public static InventoryManager getInstance() {
        Log.i(MainActivity.TAG_FOR_APP, "here now");
        return manager;
    } // singleton for use in app

    public static void createSingleton(Context context) {
        if (manager == null) {
            Log.i(MainActivity.TAG_FOR_APP, "got here");
            appContext = context;
            manager = new InventoryManager();
        }
    }

    public List<ProductInfo> getListing() {
        List<ProductInfo> listing = new ArrayList<>();
        for (ProductEntry entry : inventory.values()) {
            if (entry.getHighestNumberInInventory() != 0) {
                listing.add(entry.getInfo());
            }
        }
        return listing;
    }

    public List<ProductInfo> getWebsiteListing() {
        Log.v(MainActivity.TAG_FOR_APP, "Connecting to the MaryKay server...");
        List<ProductInfo> listing = new ArrayList<>();
        for (ProductEntry entry : inventory.values()) {
            listing.add(entry.getInfo());
        }
        Log.i(MainActivity.TAG_FOR_APP, "Loaded data from MaryKay server.");
        return listing;
    }

    public void checkInItem(String barcode) { // make return boolean of whether it's successful?

    }

    public void orderItem(String barcode, int amount) {

    }

    public void updateProduct(String barcode, String newGroup, String newName, String newColor, float newCost) {

    }

    /**
     * addProduct will take all the info for a product and put it into the inventory list based on
     * a product key.
     * @param productNumber  The id on the product
     * @param category Category of the product
     * @param name Name of the product
     * @param color Color of the product
     * @param cost Total retail price of the product
     * @param section Sales Inventory or Sample Inventory
     * @param numOfProduct Total number of products being added
     */
    public void addProduct(String productNumber, String category, String name, String color, float cost, String section, int numOfProduct) {
        // for every product in the inventory list that matches the product id and section, add number of in stock by number of products being added
        String productKey = createProductKey(productNumber, section);
        if (inventory.containsKey(productKey)) {
            Log.i(MainActivity.TAG_FOR_APP, "adding " + numOfProduct + " to " + inventory.get(productKey).getInfo().getId());
            inventory.get(productKey).setNumberInStock(inventory.get(productKey).getNumberInStock() + numOfProduct);
            ProductDataSource.getInstance().storeProduct(inventory.get(productKey));
            Log.i(MainActivity.TAG_FOR_APP, "total in stock = " + inventory.get(productKey).getNumberInStock()+numOfProduct);
        }
        else {
            Log.i(MainActivity.TAG_FOR_APP, "Adding a new product");
            ProductEntry productEntry = new ProductEntry(new Product(productNumber, category, name, section, color, cost), numOfProduct, 0, numOfProduct);
            inventory.put(createProductKey(productNumber, section), productEntry);
            Log.i(MainActivity.TAG_FOR_APP, "data time");
            ProductDataSource.getInstance().storeProduct(productEntry);
            Log.i(MainActivity.TAG_FOR_APP, "Done adding new Product");
        }

    }

    /**
     * Removes the product from the inventory list based off of the productKey and how much of the product to remove.
     * @param productKey The product identifier
     * @param numProduct the number of product that is being removed.
     */
    public void removeProduct(String productKey, int numProduct) {
        inventory.get(productKey).setNumberInStock(inventory.get(productKey).getNumberInStock()-numProduct);
    }

    /**
     * createProductKey will concat the sectioin onto the product number to give each key a unique
     * number.
     * @param productNumber How many of the product are you adding?
     * @param section Is it a sample product or actual retail
     * @return Returns a unique identifier taking into account the product section
     */
    public static String createProductKey(String productNumber, String section) {
        return productNumber + section;
    }

    /**
     * Each product has a unique key that will return all the information associated with that
     * product
     * @param productKey The product identifier
     * @return Returns product info gathered into one object
     */
    public ProductInfo getProductInfo(String productKey) {
        ProductEntry productEntry = inventory.get(productKey);
        if (productEntry == null) {
            return null;
        }
        return productEntry.getInfo();
    }

    /**
     * create the local inventory list from the database that has the inventory list stored.
     */
    public void readFromDatabase() {
        Log.i(MainActivity.TAG_FOR_APP, "where are you");
        inventory.putAll(ProductDataSource.getInstance().readAllProducts());
        Log.i(MainActivity.TAG_FOR_APP, "done now");

        /*inventory.put(createProductKey("1234", "A"), new ProductEntry(new Product("1234", "foundation", "liquid", "A", "peach", 12.34f), 0, 0, 0));
        inventory.put(createProductKey("5678", "A"), new ProductEntry(new Product("5678", "foundation", "liquid", "A", "dark", 34.34f), 4, 5, 6));
        inventory.put(createProductKey("2345", "B"), new ProductEntry(new Product("2435", "eyeliner", "lash enhancing", "B", "deep black", 56.34f), 7, 8, 9));
        inventory.put(createProductKey("3456", "A"), new ProductEntry(new Product("3456", "lipstick", "liquid", "A", "bright red", 78.34f), 0, 0, 0));
        inventory.put(createProductKey("6789", "A"), new ProductEntry(new Product("6789", "lipstick", "twist up", "A", "natural", 96.57f), 3, 5, 7));
        inventory.put(createProductKey("0987", "A"), new ProductEntry(new Product("0987", "foundation", "liquid", "A", "green", 12.34f), 0, 0, 0));
        inventory.put(createProductKey("9876", "A"), new ProductEntry(new Product("9876", "foundation", "liquid", "A", "light", 34.34f), 4, 5, 6));
        inventory.put(createProductKey("8765", "B"), new ProductEntry(new Product("8765", "eyeliner", "lash enhancing", "B", "pale sparkles", 56.34f), 7, 8, 9));
        inventory.put(createProductKey("7654", "A"), new ProductEntry(new Product("7654", "lipstick", "liquid", "A", "leafy", 78.34f), 0, 0, 0));
        inventory.put(createProductKey("6543", "B"), new ProductEntry(new Product("6543", "blush", "gnarly", "B", "soft", 96.57f), 3, 5, 7));//*/
    }
}
