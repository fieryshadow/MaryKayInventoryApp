package edu.byui.shane.marykayinventoryapp;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    private String name;
    private int amount;
    private float totalCost;

    public ProductInfo(String name, int amount, float totalCost) {
        this.name = name;
        this.amount = amount;
        this.totalCost = totalCost;
    }

    public float getTotalCost() {
        return totalCost;
    }

    public int getAmount() {
        return amount;
    }

    public String getName() {
        return name;
    }
}
