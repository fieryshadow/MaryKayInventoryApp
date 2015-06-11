package edu.byui.shane.marykayinventoryapp;

import java.util.List;

/**
 * Created by shane on 6/8/15.
 */
public class InventoryManager {
    private List<ProductEntry> inStock, outOfStock, onOrder;

    public List<ProductInfo> getListing() {
        return null; // anyone
    }

    public void checkIn(int barcode) {
        // Greg
    }

    public void checkOut(int barcode) {
        // Greg
    }

    public void orderItem(int barcode, int amount) {
        // Ryan
    }

    public void updateProduct(int barcode, String newName, float newCost) {
        // Ryan
    }

    public void addProduct(int barcode, String name, float cost) {
        // Shane
    }

    public void removeProduct(int barcode) {
        // Shane
    }

    public ProductInfo getProductInfo(int barcode) {
        return null; // anyone
    }
}
