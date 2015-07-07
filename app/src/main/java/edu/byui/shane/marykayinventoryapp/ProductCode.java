package edu.byui.shane.marykayinventoryapp;

/**
 * A class that knows what the product keys look like
 */
public class ProductCode {
    /**
     * Retrieves the unique product key for any product entry
     * @param productNumber How many of the product are you adding?
     * @param section Is it a sample product or actual retail
     * @return Returns a unique identifier taking into account the product section
     */
    public static String getProductKey(String productNumber, String section) { // a signature change is eminent
        return productNumber + section;
    }
}
