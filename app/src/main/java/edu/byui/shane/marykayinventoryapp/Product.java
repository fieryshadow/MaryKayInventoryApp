package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;
import android.util.Log;

/**
 * Holds info pertaining to a MaryKay product
 */
public class Product {
    private String id, group, name, section, color;
    private float cost;
    private Bitmap image;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String group, String name, String section, String color, float cost) {
        this.id = id;
        this.group = group;
        this.name = name;
        this.section = section;
        this.color = color;
        this.cost = cost;
    }

    public String getId() {
        return id;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
        //Team Activity Information log - Ryan
        Log.i(MainActivity.TAG_FOR_APP, "Group was set sucessfully.");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        //Team Activity Error log - Ryan
        if(name.equals("")) {
            Log.e(MainActivity.TAG_FOR_APP, "Error: product must have a name!");
        }
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        //Team Activity Information log - Ryan
        if(cost >= 0) {
            Log.i(MainActivity.TAG_FOR_APP, "The cost is within valid range.");
        }
        this.cost = cost;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap newImage) {
        image = newImage;
    }
}
