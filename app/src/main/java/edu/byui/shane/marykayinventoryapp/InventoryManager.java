package edu.byui.shane.marykayinventoryapp;

import android.util.Log;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * Manages multiple lists of Products and their amounts and running costs, as well as
 * what's in the inventory, out of stock, on order, etc.
 */
public class InventoryManager {
    private static final String TAG_INVENTORY_MANAGER = "InventoryManager";
    private Hashtable<String, ProductEntry> inventory; // barcode: ProductEntry

    private static final InventoryManager manager = new InventoryManager();
    public static InventoryManager getInstance() { return manager; } // singleton for use in app

    public InventoryManager() {
        inventory = new Hashtable<>();

    /*    // testing values
        inventory.put("1234", new ProductEntry(new Product("1234", "foundation", "liquid", "A", "peach", 12.34f), 0, 0, 0));
        inventory.put("5678", new ProductEntry(new Product("5678", "foundation", "liquid", "A", "dark", 34.34f), 4, 5, 6));
        inventory.put("2345", new ProductEntry(new Product("2435", "eyeliner", "lash enhancing", "B", "deep black", 56.34f), 7, 8, 9));
        inventory.put("3456", new ProductEntry(new Product("3456", "lipstick", "liquid", "A", "bright red", 78.34f), 0, 0, 0));
        inventory.put("6789", new ProductEntry(new Product("6789", "lipstick", "twist up", "A", "natural", 96.57f), 3, 5, 7));
        inventory.put("0987", new ProductEntry(new Product("0987", "foundation", "liquid", "A", "green", 12.34f), 0, 0, 0));
        inventory.put("9876", new ProductEntry(new Product("9876", "foundation", "liquid", "A", "light", 34.34f), 4, 5, 6));
        inventory.put("8765", new ProductEntry(new Product("8765", "eyeliner", "lash enhancing", "B", "pale sparkles", 56.34f), 7, 8, 9));
        inventory.put("7654", new ProductEntry(new Product("7654", "lipstick", "liquid", "A", "leafy", 78.34f), 0, 0, 0));
        inventory.put("6543", new ProductEntry(new Product("6543", "blush", "gnarly", "B", "soft", 96.57f), 3, 5, 7));*/
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

    public void checkOutItem(String barcode) {

    }

    public void orderItem(String barcode, int amount) {

    }

    public void updateProduct(String barcode, String newGroup, String newName, String newColor, float newCost) {

    }

    public void addProduct(String barcode, String category, String name, String color, float cost, String section, int numOfProduct) {
        // for every product in the inventory list that matches the product id and section, add number of in stock by number of products being added
        for (ProductEntry entry : inventory.values()){
            // if the barcode and section match then just adjust number in Stock
            if(entry.getProduct().getId().equals(barcode) && entry.getProduct().getSection().equals(section)){
                Log.i(TAG_INVENTORY_MANAGER, "adding " + numOfProduct + " to " + entry.getInfo().getId());
                entry.setNumberInStock(entry.getNumberInStock()+numOfProduct);
            }
            // if it doesn't match both section and Product number then add a new product Entry to the inventory list
            else {
                Log.i(TAG_INVENTORY_MANAGER, "Adding a new product");
                inventory.put(barcode, new ProductEntry(new Product(barcode, category, name, section, color, cost), numOfProduct, 0, numOfProduct));
                Log.i(TAG_INVENTORY_MANAGER, "Done adding new Product");
            }
            Log.i(TAG_INVENTORY_MANAGER, "loop");
        }
        Log.v(MainActivity.TAG_FOR_APP, "added " + numOfProduct + " products to the inventory");
        Log.i(TAG_INVENTORY_MANAGER, "added " + numOfProduct + " products to the inventory");

    }

    public void removeProduct(String barcode, String category, String name, String color, float cost, String section, int numOfProduct) {

    }

    public ProductInfo getProductInfo(String barcode) {
        ProductEntry productEntry = inventory.get(barcode);
        if (productEntry == null) {
            return null;
        }
        return productEntry.getInfo();
    }

    public void readFromDatabase() {
        inventory.put("1234", new ProductEntry(new Product("1234", "foundation", "liquid", "A", "peach", 12.34f), 0, 0, 0));
        inventory.put("5678", new ProductEntry(new Product("5678", "foundation", "liquid", "A", "dark", 34.34f), 4, 5, 6));
        inventory.put("2345", new ProductEntry(new Product("2435", "eyeliner", "lash enhancing", "B", "deep black", 56.34f), 7, 8, 9));
        inventory.put("3456", new ProductEntry(new Product("3456", "lipstick", "liquid", "A", "bright red", 78.34f), 0, 0, 0));
        inventory.put("6789", new ProductEntry(new Product("6789", "lipstick", "twist up", "A", "natural", 96.57f), 3, 5, 7));
        inventory.put("0987", new ProductEntry(new Product("0987", "foundation", "liquid", "A", "green", 12.34f), 0, 0, 0));
        inventory.put("9876", new ProductEntry(new Product("9876", "foundation", "liquid", "A", "light", 34.34f), 4, 5, 6));
        inventory.put("8765", new ProductEntry(new Product("8765", "eyeliner", "lash enhancing", "B", "pale sparkles", 56.34f), 7, 8, 9));
        inventory.put("7654", new ProductEntry(new Product("7654", "lipstick", "liquid", "A", "leafy", 78.34f), 0, 0, 0));
        inventory.put("6543", new ProductEntry(new Product("6543", "blush", "gnarly", "B", "soft", 96.57f), 3, 5, 7));
    }
}
