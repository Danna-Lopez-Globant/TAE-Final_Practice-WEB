package org.com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

/**
 * Shared header/menu elements reused by every page after login (Page Factory).
 */
public abstract class AuthenticatedBasePage extends BasePage {

    @FindBy(className = "shopping_cart_link")
    protected WebElement shoppingCartLink;

    @FindBy(className = "shopping_cart_badge")
    protected List<WebElement> shoppingCartBadge;

    @FindBy(id = "react-burger-menu-btn")
    protected WebElement burgerMenuButton;

    @FindBy(id = "logout_sidebar_link")
    protected WebElement logoutLink;

    protected AuthenticatedBasePage(WebDriver driver) {
        super(driver);
    }

    public CartPage openCart() {
        click(shoppingCartLink);
        waitForUrlContains("cart.html");
        return new CartPage(driver);
    }

    public int getCartBadgeCount() {
        List<WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        if (badges.isEmpty()) {
            return 0;
        }
        return Integer.parseInt(badges.get(0).getText().trim());
    }

    public boolean isCartBadgeVisible() {
        return !driver.findElements(By.className("shopping_cart_badge")).isEmpty();
    }

    public LoginPage logout() {
        click(burgerMenuButton);
        wait.until(ExpectedConditions.visibilityOf(logoutLink));
        click(logoutLink);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("login-button")));
        wait.until(ExpectedConditions.not(ExpectedConditions.urlContains("inventory")));
        return new LoginPage(driver);
    }
}