package edu.byui.shane.marykayinventoryapp;

/**
 * A class that knows what the product keys look like
 */
public class ProductCode {
    /**
     * Retrieves the unique product key for any product entry.
     * @param productNumber How many of the product are you adding?
     * @param section Is it a sample product or actual retail
     * @return Returns a unique identifier taking into account the product section
     */
    public static String makeProductKey(String productNumber, int section) { // a signature change is eminent
        return productNumber + "_" + section;
    }

    /**
     * Retrieves the unique product key for any product entry.
     * @param productEntry A product entry to process
     * @return Returns a unique identifier taking into account the product section
     */
    public static String makeProductKey(ProductEntry productEntry) {
        ProductInfo productInfo = productEntry.getInfo();
        return makeProductKey(productInfo.getId(), productInfo.getSection());
    }

    /**
     * Parses a product code and gets the id part of it
     * @param productCode A compiled product code
     * @return Returns the product id
     */
    public static String getId(String productCode) {
        return productCode.split("_")[0];
    }

    /**
     * Parses a product code and gets the section part of it
     * @param productCode A compiled product code
     * @return Returns the product section
     */
    public static String getSection(String productCode) {
        return productCode.split("_")[1];
    }
}
