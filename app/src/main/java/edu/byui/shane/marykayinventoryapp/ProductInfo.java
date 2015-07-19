package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * A container for important values needing to be sent to the View (MVC)
 */
public class ProductInfo implements Parcelable{
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

    public void setNumberToOrderToDefault() {this.numberToOrder = highestNumberInInventory - numberInStock;}

    public void resetNumberToOrder() {this.numberToOrder = 0;}

    @Override
    public String toString() {
        //return "$" + getInventoryValue() + " of " + getColor() + " " + getName() + " " + getCategory();
        return getName() + " " + getCategory() + " - " + getColor() + "\n        "
                + getNumberToOrder() + " / $" + getCost()/2f + "                        $" + (getNumberToOrder() * getCost())/2f;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
//        String productNumber, String category, String name, int section, String color, float cost,
//        int numberInStock, int numberOnOrder, int highestNumberInInventory,
//        float inventoryValue, float totalWorth, Bitmap image, int numberToOrder
        dest.writeString(productNumber);
        dest.writeString(category);
        dest.writeString(name);
        dest.writeInt(section);
        dest.writeString(color);
        dest.writeFloat(cost);
        dest.writeInt(numberInStock);
        dest.writeInt(numberOnOrder);
        dest.writeInt(highestNumberInInventory);
        dest.writeFloat(inventoryValue);
        dest.writeFloat(totalWorth);
//        Parcel imageParcel = Parcel.obtain();
//        image.writeToParcel(imageParcel, 0);
//        dest.writeBundle(imageParcel.readBundle());
        dest.writeInt(numberToOrder);
    }

    public static final Parcelable.Creator<ProductInfo> CREATOR = new Parcelable.Creator<ProductInfo>() {
        @Override
        public ProductInfo createFromParcel(Parcel source) {
            String productNumber = source.readString();
            String category = source.readString();
            String name = source.readString();
            Integer section = source.readInt();
            String color = source.readString();
            Float cost = source.readFloat();
            Integer numberInStock = source.readInt();
            Integer numberOnOrder = source.readInt();
            Integer highestNumberInInventory = source.readInt();
            Float inventoryValue = source.readFloat();
            Float totalWorth = source.readFloat();
//            Bundle imageBundle = source.readBundle();
//            Parcel imageParcel = Parcel.obtain();
//            imageBundle.writeToParcel(imageParcel, 0);
//            Bitmap image = Bitmap.CREATOR.createFromParcel(imageParcel);
            Integer numberToOrder = source.readInt();

            ProductInfo info = new ProductInfo(productNumber, category, name, section, color, cost, numberInStock,
                    numberOnOrder, highestNumberInInventory, inventoryValue, totalWorth, null);//image);
            info.setNumberToOrder(numberToOrder);
            return info;
        }

        @Override
        public ProductInfo[] newArray(int size) {
            return new ProductInfo[size];
        }
    };
}
