package edu.byui.shane.marykayinventoryapp;

/**
 * Created by shane on 6/8/15.
 */
public class Product {
    private String name;
    private int id;
    private float cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public float getCost() {
        return cost;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }
}
