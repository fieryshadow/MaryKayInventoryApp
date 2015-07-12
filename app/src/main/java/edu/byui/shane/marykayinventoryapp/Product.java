package edu.byui.shane.marykayinventoryapp;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

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
        Log.i(MainActivity.TAG_FOR_APP, "Loading product image in Product.setImageByFile");
        String path = Environment.getDataDirectory().getAbsolutePath();
        String filepath = path + "/MaryKayIconUpdate/" + filename;
        if (new File(filepath).exists()) {
            Log.i(MainActivity.TAG_FOR_APP, "Decoding image at '" + path + "' in Product.setImageByFile");
            Bitmap image = BitmapFactory.decodeFile(filepath);
            Log.i(MainActivity.TAG_FOR_APP, "Scaling image in Product.setImageByFile");
            this.image = Bitmap.createScaledBitmap(image, 50, 50, true);
            Log.i(MainActivity.TAG_FOR_APP, "Image has been updated in Product.setImageByFile");
        } else {
            Log.w(MainActivity.TAG_FOR_APP, "The filename specified doesn't exist! in Product.setImageByFile");
        }
    }

    public void setImageByURL(String url) {
        Log.i(MainActivity.TAG_FOR_APP, "Downloading product image from specified URL in Product.setImageByURL");
        try {
            URL u = new URL(url);
            Log.w(MainActivity.TAG_FOR_APP, "Decoding image in Product.setImageByURL");
            Bitmap image = BitmapFactory.decodeStream(u.openStream());
            Log.w(MainActivity.TAG_FOR_APP, "Resizing image in Product.setImageByURL");
            this.image = Bitmap.createScaledBitmap(image, 50, 50, true);
            Log.w(MainActivity.TAG_FOR_APP, "Image has been set in Product.setImageByURL");
        } catch (MalformedURLException ex) {
            Log.w(MainActivity.TAG_FOR_APP, "Couldn't download image from specified URL in Product.setImageByURL");
        } catch (IOException e) {
            Log.w(MainActivity.TAG_FOR_APP, "Couldn't decode image from specified URL in Product.setImageByURL");
        }
    }
}
