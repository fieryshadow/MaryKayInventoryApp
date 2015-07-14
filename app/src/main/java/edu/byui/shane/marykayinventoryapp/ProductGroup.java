package edu.byui.shane.marykayinventoryapp;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * A container for groups of product info
 */
public class ProductGroup {
    private String category, name;
    private Bitmap image;
    private List<ProductInfo> children;

    public ProductGroup(String category, String name, Bitmap image) {
        children = new ArrayList<>();
        this.category = category;
        this.name = name;
        this.image = image;
    }

    public void addChild(ProductInfo child) {
        children.add(child);
    }

    public int getSize() {
        return children.size();
    }

    public List<ProductInfo> getChildren() {
        return children;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public Bitmap getImage() {
        return image;
    }
}
