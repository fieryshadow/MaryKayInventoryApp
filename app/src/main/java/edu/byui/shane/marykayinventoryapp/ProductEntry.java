package edu.byui.shane.marykayinventoryapp;

/**
 * Keeps track of how muck of a product you have in your inventory
 */
public class ProductEntry {
    private Product product;
    private int numberOfProduct, defaultValue;

    public float calculateTotalCost() {
        return 0; // Shane
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getNumberOfProduct() {
        return numberOfProduct;
    }

    public void setNumberOfProduct(int numberOfProduct) {
        this.numberOfProduct = numberOfProduct;
    }

    public int getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(int defaultValue) {
        this.defaultValue = defaultValue;
    }
}
