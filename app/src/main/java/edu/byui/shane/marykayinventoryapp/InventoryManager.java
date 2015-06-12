package edu.byui.shane.marykayinventoryapp;

import java.util.Hashtable;
import java.util.List;

/**
 * Manages multiple lists of Products and their amounts and running costs, as well as
 * what's in the inventory, out of stock, on order, etc.
 */
public class InventoryManager {
    private Hashtable<Integer, ProductEntry> inventory; // barcode -> ProductEntry

    public List<ProductInfo> getListing() {
        return null;
    }

    public void checkInItem(int barcode) {


    }

    public void checkOutItem(int barcode) {

    }

    public void orderItem(int barcode, int amount) {

    }

    public void updateProduct(int barcode, String newGroup, String newName, String newColor, float newCost) {

    }

    public void addProduct(int barcode, String group, String name, String color, float cost) {

    }

    public void removeProduct(int barcode) {

    }

    public ProductInfo getProductInfo(int barcode) {
        ProductEntry productEntry = inventory.get(barcode);
        if (productEntry == null) {
            return null;
        }
        return productEntry.getInfo();
    }
}
