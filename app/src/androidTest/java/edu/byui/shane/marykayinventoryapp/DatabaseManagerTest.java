package edu.byui.shane.marykayinventoryapp;

import android.util.Log;

import java.util.Hashtable;

/**
 * Tests that the ProductDataSource works properly
 */
public class DatabaseManagerTest extends BaseTest {
    public void testDatabaseReadWrite() {
        // store a new product entry into the database
        ProductDataSource pds = ProductDataSource.getInstance();
        Product product = new Product("dbmt1", "Foundation", "Smokey", 1, "Grey", 11.11f);
        ProductEntry productEntry = new ProductEntry(product, 1, 2, 3);
        pds.storeProduct(productEntry);

        // check read product entry's integrity
        ProductEntry readProductEntry = pds.getProduct(ProductCode.makeProductKey("dbmt1", 1));
        assertNotNull(readProductEntry);
        assertEquals(1, readProductEntry.getNumberInStock());
        assertEquals(2, readProductEntry.getNumberOnOrder());
        assertEquals(3, readProductEntry.getHighestNumberInInventory());

        // check read product's integrity
        Product readProduct = readProductEntry.getProduct();
        assertEquals("dbmt1", readProduct.getProductNumber());
        assertEquals("Foundation", readProduct.getCategory());
        assertEquals("Smokey", readProduct.getName());
        assertEquals(1, readProduct.getSection());
        assertEquals("Grey", readProduct.getColor());
        assertEquals(11.11f, readProduct.getCost());
    }

    public void testReadAllProducts() {
        // store some products
        ProductDataSource pds = ProductDataSource.getInstance();
        // can't do the following unless we somehow is
        //assertEquals(0, pds.readAllProducts().size());
        pds.storeProduct(new ProductEntry(new Product("dbmt2-0", 1), 1, 2, 3));
        pds.storeProduct(new ProductEntry(new Product("dbmt2-1", 1), 1, 2, 3));
        pds.storeProduct(new ProductEntry(new Product("dbmt2-2", 1), 1, 2, 3));

        // make sure the products are properly returned
        Hashtable<String, ProductEntry> readProducts = pds.readAllProducts();
        assertEquals(true, readProducts.containsKey("dbmt2-0"));
        assertEquals(true, readProducts.containsKey("dbmt2-1"));
        assertEquals(true, readProducts.containsKey("dbmt2-2"));
    }
}
