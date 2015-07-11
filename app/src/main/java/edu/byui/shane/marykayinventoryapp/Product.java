package edu.byui.shane.marykayinventoryapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.File;

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

        // load a default icon
        this.image = BitmapFactory.decodeResource(MainActivity.getMainResources(), R.mipmap.default_product_icon);
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
            Log.i(MainActivity.TAG_FOR_APP, "The cost is within valid range. in Product.setCost");
        }
        this.cost = cost;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap newImage) {
        image = newImage;
    }

    public void setImageByFile(String filename) {
        String path = Environment.getDataDirectory().getAbsolutePath();
        String filepath = path + "/MaryKayIconUpdate/" + filename;
        Log.i(MainActivity.TAG_FOR_APP, "Decoding image at '" + path + "' in Product.setImageByFile");
        Bitmap image = BitmapFactory.decodeFile(filepath);
        Log.i(MainActivity.TAG_FOR_APP, "Scaling image in Product.setImageByFile");
        this.image = Bitmap.createScaledBitmap(image, 50, 50, true);
        Log.i(MainActivity.TAG_FOR_APP, "Image has been updated in Product.setImageByFile");
    }
}
