package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo {
    private String productNumber, category, name, color;
    private int section, numberInStock, numberOnOrder, highestNumberInInventory;
    private float cost, inventoryValue, totalWorth;
    private Bitmap image;
    private int numberToOrder;

    public ProductInfo(String productNumber, String category, String name, int section, String color, float cost,
                       int numberInStock, int numberOnOrder, int highestNumberInInventory,
                       float inventoryValue, float totalWorth, Bitmap image) {
        this.productNumber = productNumber;
        this.category = category;
        this.name = name;
        this.section = section;
        this.color = color;
        this.cost = cost;
        this.numberInStock = numberInStock;
        this.numberOnOrder = numberOnOrder;
        this.highestNumberInInventory = highestNumberInInventory;
        this.inventoryValue = inventoryValue;
        this.totalWorth = totalWorth;
        this.image = image;
        this.numberToOrder = 0;//We might also subtract off the numberOnOrder to not order twice.
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getSection() {
        return section;
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

    public Bitmap getImage() {
        return image;
    }

    public int getNumberToOrder() {return numberToOrder;}

    public void setNumberToOrder(int numToOrder) {this.numberToOrder = numToOrder;}

    public void setNumberToOrderToDefault() {
        this.numberToOrder = highestNumberInInventory - numberInStock;
        if(this.numberToOrder == 0){
            this.numberToOrder = 1;
            Log.i(MyApp.LOGGING_TAG, "no item added setting number to order to " + this.numberToOrder);
        }
    }

    public void resetNumberToOrder() {this.numberToOrder = 0;}

    @Override
    public String toString() {
        return "$" + getInventoryValue() + " of " + getColor() + " " + getName() + " " + getCategory();
    }
}
