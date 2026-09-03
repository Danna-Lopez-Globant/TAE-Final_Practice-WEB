package org.com.globant.tests;

import org.com.globant.base.BaseTest;
import org.com.globant.data.CheckoutData;
import org.com.globant.data.TestData;
import org.com.globant.pages.CartPage;
import org.com.globant.pages.CheckoutCompletePage;
import org.com.globant.pages.CheckoutInfoPage;
import org.com.globant.pages.CheckoutOverviewPage;
import org.com.globant.pages.InventoryPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PurchaseProductTest extends BaseTest {

    private InventoryPage inventoryPage;
    private CheckoutData checkoutData;

    @BeforeMethod(alwaysRun = true)
    public void loginPrecondition() {
        inventoryPage = loginAsStandardUser();
        checkoutData = TestData.getCheckoutData();
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after login.");
    }

    @Test(description = "Purchase a random product through the complete buy flow")
    public void purchaseRandomProduct() {
        String selectedProduct = inventoryPage.addRandomProductToCart();
        Assert.assertEquals(inventoryPage.getCartBadgeCount(), 1,
                "Cart badge should show 1 after adding a product. Selected: " + selectedProduct);

        CartPage cartPage = inventoryPage.openCart();
        Assert.assertTrue(cartPage.isLoaded(), "Cart page should be displayed.");
        Assert.assertEquals(cartPage.getItemCount(), 1, "Cart should contain the selected product.");

        CheckoutInfoPage checkoutInfoPage = cartPage.clickCheckout();
        Assert.assertTrue(checkoutInfoPage.isLoaded(), "Checkout information page should be displayed.");

        CheckoutOverviewPage overviewPage = checkoutInfoPage
                .fillPersonalData(checkoutData)
                .continueToOverview();
        Assert.assertTrue(overviewPage.isLoaded(), "Checkout overview page should be displayed.");

        CheckoutCompletePage completePage = overviewPage.finishCheckout();
        Assert.assertTrue(completePage.isLoaded(), "Checkout complete page should be displayed.");
        Assert.assertTrue(completePage.isPurchaseSuccessful(TestData.getCheckoutSuccessMessage()),
                "Expected a thank-you message. Actual: " + completePage.getThankYouMessage());
    }
}