package edu.byui.shane.marykayinventoryapp;

import android.util.Log;

/**
 * Holds info pertaining to a MaryKay product
 */
public class Product {
    private String id, group, name, section, color;
    private float cost;

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
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
        //Team Activity Information log - Ryan
        if(this.getCost() >= 0) {
            Log.i("Valid Input", "The cost is within valid range.");
        }
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
