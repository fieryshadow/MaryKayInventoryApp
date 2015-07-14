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
    private static InventoryManager manager; // final facade
    private Hashtable<String, ProductEntry> inventory; // ProductCode -> ProductEntry
    
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
     * Retrieves the product entries corresponding to the given section and groups them by category/name
     * @param section Section identifier
     * @return Returns a list of product groups
     */
    public List<ProductGroup> getSectionListing(int section) {
        Hashtable<String, ProductGroup> listing = new Hashtable<>();
        Log.v(MyApp.LOGGING_TAG, "Finding products under section " + section + " in InventoryManager.getSectionListing");
        for (ProductEntry entry : inventory.values()) {
            Log.v(MyApp.LOGGING_TAG, "Current product (" + ProductCode.makeProductKey(entry) + ") section is " + entry.getProduct().getSection() + " in InventoryManager.getSectionListing");
            if (entry.getProduct().getSection() == section) {
                Log.v(MyApp.LOGGING_TAG, "Adding product to list in InventoryManager.getSectionListing");
                ProductInfo info = entry.getInfo();
                String key = info.getCategory()+ "_" + info.getName();
                if (listing.containsKey(key)) {
                    listing.get(key).addChild(info);
                } else {
                    ProductGroup group = new ProductGroup(info.getCategory(), info.getName(), info.getImage());
                    group.addChild(info);
                    listing.put(key, group);
                }
            }
        }
        Log.v(MyApp.LOGGING_TAG, "Returning products under section " + section + " in InventoryManager.getSectionListing");
        List<ProductGroup> list = new ArrayList<>();
        list.addAll(listing.values());
        return list;
    }

    /**
     * Retrieves all the product info you could ever want
     * @return Returns a list of product info
     */
    public List<ProductInfo> getListing() {
        List<ProductInfo> list = new ArrayList<>();
        for (ProductEntry entry : inventory.values()) {
            list.add(entry.getInfo());
        }
        return list;
    }

    private void updateProduct(String productNumber, String category, String name, String color,
                               float cost, int section, int changeInProduct, int changeInOrder, String imageFile) {
        Product product;
        ProductEntry productEntry;
        String productKey = ProductCode.makeProductKey(productNumber, section);
        if (inventory.containsKey(productKey)) { // update existing product in inventory
            productEntry = inventory.get(productKey);
            Log.v(MyApp.LOGGING_TAG, "Setting " + changeInProduct + " to " + productKey + " in InventoryManager.updateProduct");
            product = productEntry.getProduct();
            product.setCategory(category);
            product.setName(name);
            product.setColor(color);
            product.setCost(cost);

            productEntry.setNumberInStock(productEntry.getNumberInStock() + changeInProduct);
            productEntry.setNumberOnOrder(productEntry.getNumberOnOrder() + changeInOrder);
            Log.v(MyApp.LOGGING_TAG, "Total in stock is: " + productEntry.getNumberInStock() + " in InventoryManager.updateProduct");
        } else { // add new product to inventory
            Log.i(MyApp.LOGGING_TAG, "Adding a new product in InventoryManager.updateProduct");
            product = new Product(productNumber, category, name, section, color, cost);
            Log.v(MyApp.LOGGING_TAG, "Checking product delta in InventoryManager.updateProduct");
            if (changeInProduct < 0) {
                Log.w(MyApp.LOGGING_TAG, "You can't remove products that don't exist in the inventory! Adding product to the list in InventoryManager.updateProduct");
                changeInProduct = 0;
            } else if (changeInOrder < 0) {
                Log.w(MyApp.LOGGING_TAG, "You don't have any items ordered for nonexistent products. Adding product to the list in InventoryManager.updateProduct");
                changeInOrder = 0;
            }
            Log.i(MyApp.LOGGING_TAG, "Adding product to inventory in InventoryManager.updateProduct");
            productEntry = new ProductEntry(product, changeInProduct, changeInOrder, changeInProduct);
            String key = ProductCode.makeProductKey(productNumber, section);
            inventory.put(key, productEntry);
        }

        Log.i(MyApp.LOGGING_TAG, "Checking for product image in InventoryManager.updateProduct");
        if (imageFile != null && !imageFile.equals("")) {
            Log.v(MyApp.LOGGING_TAG, "Updating product image in InventoryManager.updateProduct");
            if (imageFile.contains("http")) {
                product.setImageByURL(imageFile);
            } else {
                product.setImageByFile(imageFile);
            }
            Log.i(MyApp.LOGGING_TAG, "Updated product image in InventoryManager.updateProduct");
        }

        Log.v(MyApp.LOGGING_TAG, "Database time! in InventoryManager.updateProduct");
        ProductDataSource.getInstance().storeProduct(productEntry);
        Log.i(MyApp.LOGGING_TAG, "Stored info to database in InventoryManager.updateProduct");
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
    public void processCheckIn(final String productNumber, final String category, final String name, final String color,
                               final float cost, final int section, final int numOfProduct, final String imageFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int changeInOrder = 0;
                String productKey = ProductCode.makeProductKey(productNumber, section);
                Log.i(MyApp.LOGGING_TAG, "Adding " + numOfProduct + " products to " + productKey + " in InventoryManager.processCheckIn");
                if (inventory.containsKey(productKey)) {
                    int orders = inventory.get(productKey).getNumberOnOrder();
                    changeInOrder = Math.min(orders, numOfProduct);
                }
                updateProduct(productNumber, category, name, color, cost, section, numOfProduct, changeInOrder, imageFile);
            }
        }).start();
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
    public void processCheckOut(final String productNumber, final String category, final String name, final String color,
                                final float cost, final int section, final int numOfProduct, final String imageFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateProduct(productNumber, category, name, color, cost, section, -numOfProduct, 0, imageFile);
            }
        }).start();
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
    public void processOrders(final String productNumber, final String category, final String name, final String color,
                              final float cost, final int section, final int numOfProduct, final String imageFile) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                updateProduct(productNumber, category, name, color, cost, section, 0, numOfProduct, imageFile);
            }
        }).start();
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
     * Create the local inventory list from the database that has the stored data
     */
    public void readFromDatabase() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Log.v(MyApp.LOGGING_TAG, "Starting read from database in InventoryManager.readFromDatabase.thread");
                inventory.putAll(ProductDataSource.getInstance().readAllProducts());
                Log.i(MyApp.LOGGING_TAG, "Finished reading database in InventoryManager.readFromDatabase.thread");

                if (inventory.values().size() == 0) { // prepopulate inventory for testing
                    Log.i(MyApp.LOGGING_TAG, "Database is empty, creating new product list in InventoryManager.readFromDatabase.thread");
                    ProductEntry productEntry;
                    productEntry = new ProductEntry(new Product("12345678", "Lipstick", "True Dimensions", 1, "Sassy Fuchsia (Satin)", 18.00f), 3, 0, 5);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-sassy-fuchsia-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("12348765", "Lipstick", "True Dimensions", 1, "Spice n' Nice (Satin)", 18.00f), 5, 7, 5);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-spice-and-nice-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("97445676", "Lipstick", "True Dimensions", 1, "Chocolate (Satin)", 18.00f), 123, 21, 234);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-chocolatte-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("95346212", "Lipstick", "True Dimensions", 1, "Tangerine Pop (Satin)", 18.00f), 56, 0, 1213);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-tangerine-pop-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("97543456", "Lipstick", "True Dimensions", 1, "First Blush (Satin)", 18.00f), 1, 17, 2);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-first-blush-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("12344321", "Lip Gloss", "NouriShine", 1, "Shock Tart (Shimmer)", 15.00f), 13, 0, 54);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20Nourishine%20Plus%20Lip%20Gloss/mary-kay-nourishine-plus-lip-gloss-shock-tart-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("12345678", "Lipstick", "True Dimensions", 2, "Sassy Fuchsia (Satin)", 0.02f), 7, 3, 11);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20True%20Dimensions%20Lipstick/mary-kay-true-dimensions-lipstick-sassy-fuchsia-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    productEntry = new ProductEntry(new Product("12344321", "Lip Gloss", "NouriShine", 2, "Shock Tart (Shimmer)", 0.34f), 0, 0, 13);
                    productEntry.getProduct().setImageByURL("http://www.marykay.com/en-US/products/PublishingImages/DAM/Product%20Images/Final%20Product%20Image%20Library/Makeup/Mary%20Kay%20Nourishine%20Plus%20Lip%20Gloss/mary-kay-nourishine-plus-lip-gloss-shock-tart-h.png");
                    inventory.put(ProductCode.makeProductKey(productEntry), productEntry);

                    Log.i(MyApp.LOGGING_TAG, "Writing products to database in InventoryManager.readFromDatabase.thread");
                    ProductDataSource pds = ProductDataSource.getInstance();
                    for (ProductEntry entry : inventory.values()) {
                        pds.storeProduct(entry);
                    }
                    Log.i(MyApp.LOGGING_TAG, "Finished creating product list in InventoryManager.readFromDatabase.thread");
                }
            }
        }).start();
    }
}
