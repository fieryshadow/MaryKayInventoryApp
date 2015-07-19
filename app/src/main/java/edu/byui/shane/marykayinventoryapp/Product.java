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
    private String productNumber, category, name, color;
    private int section;
    private float cost;
    private Bitmap image;

    public Product(String productNumber, int section) {
        this.productNumber = productNumber;
        this.section = section;
    }

    public Product(String productNumber, String group, String name, int section, String color, float cost) {
        this.productNumber = productNumber;
        this.category = group;
        this.name = name;
        this.section = section;
        this.color = color;
        this.cost = cost;

        // load a default icon
        this.image = BitmapFactory.decodeResource(MainActivity.getMainResources(), R.mipmap.default_product_icon);
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
        Log.i(MyApp.LOGGING_TAG, "Category was set successfully in Product.setCategory");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name.equals("")) {
            Log.e(MyApp.LOGGING_TAG, "Error: product must have a name! in Product.setName");
        }
        this.name = name;
    }

    public int getSection() {
        return section;
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
        if(cost >= 0) {
            Log.i(MyApp.LOGGING_TAG, "The cost is within valid range. in Product.setCost");
        }
        this.cost = cost;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap newImage) {
        image = newImage;
    }

    /**
     * Retrieves a file and uses that file to set the image.
     * @param filename
     */
    public void setImageByFile(String filename) {
        Log.i(MyApp.LOGGING_TAG, "Loading product image in Product.setImageByFile");
        String path = Environment.getDataDirectory().getAbsolutePath();
        String filepath = path + "/MaryKayIconUpdate/" + filename;
        if (new File(filepath).exists()) {
            Log.i(MyApp.LOGGING_TAG, "Decoding image at '" + path + "' in Product.setImageByFile");
            Bitmap image = BitmapFactory.decodeFile(filepath);
            Log.i(MyApp.LOGGING_TAG, "Scaling image in Product.setImageByFile");
            this.image = Bitmap.createScaledBitmap(image, 80, 80, true);
            Log.i(MyApp.LOGGING_TAG, "Image has been updated in Product.setImageByFile");
        } else {
            Log.w(MyApp.LOGGING_TAG, "The filename specified doesn't exist! in Product.setImageByFile");
        }
    }

    /**
     * retrieves a url and converts it to an image
     * @param url
     */
    public void setImageByURL(String url) {
        Log.i(MyApp.LOGGING_TAG, "Downloading product image from specified URL in Product.setImageByURL");
        try {
            URL u = new URL(url);
            Log.v(MyApp.LOGGING_TAG, "Decoding image in Product.setImageByURL");
            Bitmap image = BitmapFactory.decodeStream(u.openStream());
            Log.v(MyApp.LOGGING_TAG, "Resizing image in Product.setImageByURL");
            this.image = Bitmap.createScaledBitmap(image, 80, 80, true);
            Log.i(MyApp.LOGGING_TAG, "Image has been set in Product.setImageByURL");
        } catch (MalformedURLException ex) {
            Log.w(MyApp.LOGGING_TAG, "Couldn't find specified URL in Product.setImageByURL");
        } catch (IOException e) {
            Log.w(MyApp.LOGGING_TAG, "Couldn't decode image from specified URL in Product.setImageByURL");
        }
    }
}
