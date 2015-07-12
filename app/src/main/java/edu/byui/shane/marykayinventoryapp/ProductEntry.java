package edu.byui.shane.marykayinventoryapp;

/**
 * Keeps track of how much of a product you have in your inventory and associated costs
 */
public class ProductEntry {
    private Product product;
    private int numberInStock, numberOnOrder, highestNumberInInventory;

    public ProductEntry(Product product, int numberInStock, int numberOnOrder, int highestNumberInInventory) {
        // should we throw some exceptions for passing negative values?
        this.product = product;
        this.numberInStock = numberInStock;
        this.numberOnOrder = numberOnOrder;
        this.highestNumberInInventory = highestNumberInInventory;
    }

    public float getInventoryValue() {
        return calculateCostOf(numberInStock);
    }

    public float getTotalWorth() {
        return calculateCostOf(numberInStock + numberOnOrder);
    }

    private float calculateCostOf(int amount) {
        return amount * product.getCost();
    }

    public boolean isOutOfStock() { // are we going to use this??
        return numberInStock == 0;
    }

    public ProductInfo getInfo() {
        return new ProductInfo(product.getProductNumber(), product.getCategory(), product.getName(),
                product.getSection(), product.getColor(), product.getCost(),
                numberInStock, numberOnOrder, highestNumberInInventory,
                getInventoryValue(), getTotalWorth(), product.getImage());
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

    public void setNumberInStock(int newNumberInStock) {
        numberInStock = newNumberInStock;
        if (numberInStock > highestNumberInInventory) {
            highestNumberInInventory = numberInStock;
        }
    }

    public void setNumberOnOrder(int numberOnOrder) {
        this.numberOnOrder = numberOnOrder;
    }
}
