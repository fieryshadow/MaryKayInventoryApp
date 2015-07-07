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

    public float calculateInventoryValue() {
        return calculateCostOf(numberInStock);
    }

    public float calculateTotalWorth() {
        return calculateCostOf(numberInStock + numberOnOrder);
    }

    public float calculateCostOf(int amount) {
        return amount * product.getCost();
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

    public ProductInfo getInfo() {
        return new ProductInfo(ProductCode.getProductKey(product.getId(), product.getSection()), product.getGroup(), product.getName(),
                product.getSection(), product.getColor(), product.getCost(),
                numberInStock, numberOnOrder, highestNumberInInventory,
                calculateInventoryValue(), calculateTotalWorth());
    }

    public Product getProduct() {return product;}

    public int getHighestNumberInInventory() {return highestNumberInInventory;}

    public int getNumberOnOrder() {return numberOnOrder;}

    public int getNumberInStock() {return numberInStock;}

    public void setNumberInStock(int numberInStock) {this.numberInStock = numberInStock;}

    public void setNumberOnOrder(int numberOnOrder) {this.numberOnOrder = numberOnOrder;}

    public void setHighestNumberInInventory(int highestNumberInInventory) {this.highestNumberInInventory = highestNumberInInventory;}
}
