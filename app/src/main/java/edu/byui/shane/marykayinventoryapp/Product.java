package edu.byui.shane.marykayinventoryapp;

/**
 * Holds info pertaining to a MaryKay product
 */
public class Product {
    private String id, group, name, color;
    private float cost;

    public Product(String id) {
        this.id = id;
    }

    public Product(String id, String group, String name, String color, float cost) {
        this.id = id;
        this.group = group;
        this.name = name;
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
        this.cost = cost;
    }
}
