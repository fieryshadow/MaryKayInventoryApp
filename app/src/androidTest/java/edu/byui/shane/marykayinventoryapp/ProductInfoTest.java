package edu.byui.shane.marykayinventoryapp;

/**
 * Tests that the ProductInfo class is functional
 */
public class ProductInfoTest extends BaseTest {
    public void testReorderInfoTracking() {
        ProductInfo info = new ProductInfo("pit1", "Blush", "Pepper", 1, "Hot", 12.21f, 1, 0, 2, 12.21f, 12.21f, null);
        assertEquals(0, info.getNumberToOrder());
        info.setNumberToOrderToDefault();
        assertEquals(1, info.getNumberToOrder());
        info.setNumberToOrder(4);
        assertEquals(4, info.getNumberToOrder());
        info.setNumberToOrderToDefault();
        assertEquals(1, info.getNumberToOrder());
        info.resetNumberToOrder();
        assertEquals(0, info.getNumberToOrder());
    }
}
