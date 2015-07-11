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
    public static String section1 = "A";
    public static String section2 = "B";
    private static Context appContext;
    private static InventoryManager manager; // final...
    private Hashtable<String, ProductEntry> inventory; // barcode: ProductEntry
    
    /** This is a singleton */
    private InventoryManager() {
        inventory = new Hashtable<>();
    }

    /** Retrieve the one and only, instance */
    public static InventoryManager getInstance() {
        return manager;
    }

    /**
     * A workaround for needing the activity context for parts of the code to work correctly. Must be
     * called at the very beginning of the program startup, or else a NullPointerException will occur!
     * @param context Needs to be the MainActivity! Not for calling anywhere else!
     */
    public static void createSingleton(Context context) {
        if (manager == null) {
            appContext = context;
            manager = new InventoryManager();
        }
    }

    /**
     * Retrieves the product entries corresponding to the given section
     * @param section Section identifier
     * @return Returns a list of product entries
     */
    public List<ProductInfo> getSectionListing(String section) {
        List<ProductInfo> listing = new ArrayList<>();
        for (ProductEntry entry : inventory.values()) {
            if (entry.getProduct().getSection().equals(section)) {
                listing.add(entry.getInfo());
            }
        }
        return listing;
    }

    /**
     * Stretch goal
     * @return Returns the whole MaryKay product list
     */
    public List<ProductInfo> getWebsiteListing() {
        Log.v(MainActivity.TAG_FOR_APP, "Connecting to the MaryKay server... in InventoryManager.getWebsiteListing");
        // tbd...
        Log.i(MainActivity.TAG_FOR_APP, "Loaded data from MaryKay server.");
        return null;
    }

    private void updateProduct(String productNumber, String category, String name, String color,
                               float cost, String section, int changeInProduct, int changeInOrder, String imageFile) {
        Product product;
        ProductEntry productEntry;
        String productKey = ProductCode.makeProductKey(productNumber, section);
        if (inventory.containsKey(productKey)) { // update existing product in inventory
            productEntry = inventory.get(productKey);
            Log.i(MainActivity.TAG_FOR_APP, "setting " + changeInProduct + " to " + productEntry.getInfo().getId() + " in InventoryManager.updateProduct");
            product = productEntry.getProduct();
            product.setGroup(category);
            product.setName(name);
            product.setColor(color);
            product.setCost(cost);

            productEntry.setNumberInStock(productEntry.getNumberInStock() + changeInProduct);
            productEntry.setNumberOnOrder(productEntry.getNumberOnOrder() + changeInOrder);
            Log.i(MainActivity.TAG_FOR_APP, "Total in stock = " + productEntry.getNumberInStock() + " in InventoryManager.updateProduct");
        } else { // add new product to inventory
            Log.i(MainActivity.TAG_FOR_APP, "Adding a new product in InventoryManager.updateProduct");
            product = new Product(productNumber, category, name, section, color, cost);
            Log.i(MainActivity.TAG_FOR_APP, "Checking product delta in InventoryManager.updateProduct");
            if (changeInProduct < 0) {
                Log.w(MainActivity.TAG_FOR_APP, "You can't remove products that don't exist in the inventory! Adding product to the list... in InventoryManager.updateProduct");
                changeInProduct = 0;
            } else if (changeInOrder < 0) {
                Log.w(MainActivity.TAG_FOR_APP, "You don't have any items ordered for nonexistent products. Adding product to the list... in InventoryManager.updateProduct");
                changeInOrder = 0;
            }
            Log.i(MainActivity.TAG_FOR_APP, "Adding product to inventory in InventoryManager.updateProduct");
            productEntry = new ProductEntry(product, changeInProduct, changeInOrder, changeInProduct);
            String key = ProductCode.makeProductKey(productNumber, section);
            inventory.put(key, productEntry);
        }

        Log.i(MainActivity.TAG_FOR_APP, "Checking for product image in InventoryManager.updateProduct");
        if (imageFile != null && !imageFile.equals("")) {
            Log.i(MainActivity.TAG_FOR_APP, "Updating product image in InventoryManager.updateProduct");
            product.setImageByFile(imageFile);
        }

        Log.v(MainActivity.TAG_FOR_APP, "Database time! in InventoryManager.updateProduct");
        ProductDataSource.getInstance().storeProduct(productEntry);
        Log.i(MainActivity.TAG_FOR_APP, "Stored info in database in InventoryManager.updateProduct");
    }

    /**
     * Takes all the info for a product and puts it into the inventory list based on a product key
     * @param productNumber The id on the product
     * @param category Category of the product
     * @param name Name of the product
     * @param color Color of the product
     * @param cost Total retail price of the product
     * @param section Sales Inventory or Sample Inventory
     * @param numOfProduct Total number of products being added
     * @param imageFile The filename for a new icon to store in the database
     */
    public void processCheckIn(String productNumber, String category, String name, String color,
                               float cost, String section, int numOfProduct, String imageFile) {
        int changeInOrder = 0;
        String productKey = ProductCode.makeProductKey(productNumber, section);
        Log.i(MainActivity.TAG_FOR_APP, "Adding " + numOfProduct + " to " + productKey + " in InventoryManager.processCheckIn");
        if (inventory.containsKey(productKey)) {
            int orders = inventory.get(productKey).getNumberOnOrder();
            changeInOrder = Math.min(orders, numOfProduct);
        }
        updateProduct(productNumber, category, name, color, cost, section, numOfProduct, changeInOrder, imageFile);
    }

    /**
     * Removes the product from the inventory list based off of the productKey and how much of the product to remove.
     * @param productNumber The id on the product
     * @param category Category of the product
     * @param name Name of the product
     * @param color Color of the product
     * @param cost Total retail price of the product
     * @param section Sales Inventory or Sample Inventory
     * @param numOfProduct Total number of products being removed
     * @param imageFile The filename for a new icon to store in the database
     */
    public void processCheckOut(String productNumber, String category, String name, String color,
                                float cost, String section, int numOfProduct, String imageFile) {
        updateProduct(productNumber, category, name, color, cost, section, -numOfProduct, 0, imageFile);
    }

    /**
     * Processes order request, dispatching to other code to handle the details (website, etc)
     * @param productNumber The id on the product
     * @param category Category of the product
     * @param name Name of the product
     * @param color Color of the product
     * @param cost Total retail price of the product
     * @param section Sales Inventory or Sample Inventory
     * @param numOfProduct Total number of products being ordered
     * @param imageFile The filename for a new icon to store in the database
     */
    public void processOrders(String productNumber, String category, String name, String color,
                              float cost, String section, int numOfProduct, String imageFile) {
        updateProduct(productNumber, category, name, color, cost, section, 0, numOfProduct, imageFile);
    }

    /**
     * Each product has a unique key that will return all the information associated with that product
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
     * Create the local inventory list from the database that has the inventory list stored.
     */
    public void readFromDatabase() {
        Log.v(MainActivity.TAG_FOR_APP, "Starting to read from database in InventoryManager.readFromDatabase ...");
        inventory.putAll(ProductDataSource.getInstance().readAllProducts());
        Log.i(MainActivity.TAG_FOR_APP, "Finished reading database in InventoryManager.readFromDatabase");

        /* can test with hard coded products
        inventory.put(ProductCode.makeProductKey("1234", "A"), new ProductEntry(new Product("1234", "foundation", "liquid", "A", "peach", 12.34f), 0, 0, 0));
        inventory.put(ProductCode.makeProductKey("5678", "A"), new ProductEntry(new Product("5678", "foundation", "liquid", "A", "dark", 34.34f), 4, 5, 6));
        inventory.put(ProductCode.makeProductKey("2345", "B"), new ProductEntry(new Product("2435", "eyeliner", "lash enhancing", "B", "deep black", 56.34f), 7, 8, 9));
        inventory.put(ProductCode.makeProductKey("3456", "A"), new ProductEntry(new Product("3456", "lipstick", "liquid", "A", "bright red", 78.34f), 0, 0, 0));
        inventory.put(ProductCode.makeProductKey("6789", "A"), new ProductEntry(new Product("6789", "lipstick", "twist up", "A", "natural", 96.57f), 3, 5, 7));
        inventory.put(ProductCode.makeProductKey("0987", "A"), new ProductEntry(new Product("0987", "foundation", "liquid", "A", "green", 12.34f), 0, 0, 0));
        inventory.put(ProductCode.makeProductKey("9876", "A"), new ProductEntry(new Product("9876", "foundation", "liquid", "A", "light", 34.34f), 4, 5, 6));
        inventory.put(ProductCode.makeProductKey("8765", "B"), new ProductEntry(new Product("8765", "eyeliner", "lash enhancing", "B", "pale sparkles", 56.34f), 7, 8, 9));
        inventory.put(ProductCode.makeProductKey("7654", "A"), new ProductEntry(new Product("7654", "lipstick", "liquid", "A", "leafy", 78.34f), 0, 0, 0));
        inventory.put(ProductCode.makeProductKey("6543", "B"), new ProductEntry(new Product("6543", "blush", "gnarly", "B", "soft", 96.57f), 3, 5, 7));
        //*/
    }
}
