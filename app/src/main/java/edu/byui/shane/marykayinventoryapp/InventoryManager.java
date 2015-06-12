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
        return null; // Anyone
    }

    public void checkInItem(int barcode) {
        // Greg

    }

    public void checkOutItem(int barcode) {
        // Greg
    }

    public void orderItem(int barcode, int amount) {
        // Ryan
    }

    public void updateProduct(int barcode, String newGroup, String newName, String newColor, float newCost) {
        // Ryan
    }

    public void addProduct(int barcode, String group, String name, String color, float cost) {
        // Shane
    }

    public void removeProduct(int barcode) {
        // Shane
    }

    public ProductInfo getProductInfo(int barcode) {
        return null; // Anyone
    }
}
