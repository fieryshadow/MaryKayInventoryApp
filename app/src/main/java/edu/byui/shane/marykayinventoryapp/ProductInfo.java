package edu.byui.shane.marykayinventoryapp;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    private int id;
    private String group, name, color;
    private float cost;
    private int numberInStock, numberOnOrder, highestNumberInInventory;
    private float inventoryValue, totalWorth;

    public ProductInfo(int id, String group, String name, String color, float cost,
                       int numberInStock, int numberOnOrder, int highestNumberInInventory,
                       float inventoryValue, float totalWorth) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.color = color;
        this.cost = cost;
        this.numberInStock = numberInStock;
        this.numberOnOrder = numberOnOrder;
        this.highestNumberInInventory = highestNumberInInventory;
        this.inventoryValue = inventoryValue;
        this.totalWorth = totalWorth;
    }

    public int getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public float getCost() {
        return cost;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public int getNumberOnOrder() {
        return numberOnOrder;
    }

    public int getHighestNumberInInventory() {
        return highestNumberInInventory;
    }

    public float getInventoryValue() {
        return inventoryValue;
    }

    public float getTotalWorth() {
        return totalWorth;
    }
}
