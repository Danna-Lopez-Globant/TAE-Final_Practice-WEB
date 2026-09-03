package org.com.globant.tests;

import org.com.globant.base.BaseTest;
import org.com.globant.pages.InventoryPage;
import org.com.globant.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LogoutTest extends BaseTest {

    private InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void loginPrecondition() {
        inventoryPage = loginAsStandardUser();
        Assert.assertTrue(inventoryPage.isLoaded(), "Inventory page should be visible after login.");
    }

    @Test(description = "Log out and verify redirection to the login page")
    public void logoutRedirectsToLoginPage() {
        LoginPage redirectedLoginPage = inventoryPage.logout();

        Assert.assertTrue(redirectedLoginPage.isOnLoginPage(),
                "User should be redirected to the login page after logout. Current URL: "
                        + redirectedLoginPage.getCurrentUrl());
        Assert.assertTrue(redirectedLoginPage.isLoginButtonDisplayed(),
                "Login button should be visible on the login page.");
    }
}