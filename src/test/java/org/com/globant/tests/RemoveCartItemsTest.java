package org.com.globant.tests;

import org.com.globant.base.BaseTest;
import org.com.globant.data.TestData;
import org.com.globant.pages.CartPage;
import org.com.globant.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class RemoveCartItemsTest extends BaseTest {

    private InventoryPage inventoryPage;
    private List<String> addedProducts;
    private int itemsToAdd;

    @BeforeMethod(alwaysRun = true)
    public void loginAndAddProducts() {
        itemsToAdd = TestData.getCartItemsToAdd();
        inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after login.");

        addedProducts = inventoryPage.addFirstNProductsToCart(itemsToAdd);
        Assert.assertEquals(addedProducts.size(), itemsToAdd,
                itemsToAdd + " different products should be added.");
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), itemsToAdd,
                "Cart badge should show " + itemsToAdd + " items before opening the cart.");
    }

    @Test(description = "Add products from data layer, remove them from the cart, and verify it is empty")
    public void removeAllCartItems() {
        CartPage cartPage = inventoryPage.openCart();
        Assert.assertTrue(cartPage.isLoaded(), "Cart page should be displayed.");
        Assert.assertEquals(cartPage.getItemCount(), itemsToAdd,
                "Cart should list the added products: " + addedProducts);

        cartPage.removeAllItems();

        Assert.assertTrue(cartPage.isCartEmpty(), "Shopping cart should be empty after removing all items.");
        Assert.assertFalse(cartPage.isCartBadgeVisible(),
                "Cart badge should disappear when the cart is empty.");
    }
}