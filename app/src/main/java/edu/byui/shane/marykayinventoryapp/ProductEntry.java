package edu.byui.shane.marykayinventoryapp;

/**
 * Keeps track of how much of a product you have in your inventory and associated costs
 */
public class ProductEntry {
    private Product product;
    private int numberInStock, numberOnOrder, highestNumberInInventory;

    public ProductEntry(Product product, int numberInStock, int numberOnOrder, int highestNumberInInventory) {
        this.product = product;
        this.numberInStock = numberInStock;
        this.numberOnOrder = numberOnOrder;
        this.highestNumberInInventory = highestNumberInInventory;
    }

    public float calculateInventoryValue() {
        return calculateCostOf(numberInStock);
    }

    public float calculateTotalWorth() {
        return calculateCostOf(numberInStock + numberOnOrder);
    }

    public float calculateCostOf(int amount) {
        return 0;
    }

    public boolean isOutOfStock() {
        return numberInStock == 0;
    }

    public void receivedItem() {
        if (numberOnOrder > 0) {
            numberOnOrder--;
        }
        numberInStock++;
        if (numberInStock > highestNumberInInventory) {
            highestNumberInInventory = numberInStock;
        }
    }

    public void soldItem() {

    }

    public void orderedItem() {

    }

    public Product getProduct() {
        return product;
    }

    public int getHighestNumberInInventory() {
        return highestNumberInInventory;
    }

    public int getNumberOnOrder() {
        return numberOnOrder;
    }

    public int getNumberInStock() {
        return numberInStock;
    }
}
