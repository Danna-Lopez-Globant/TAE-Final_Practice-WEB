package org.com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class CartPage extends AuthenticatedBasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "cart_item")
    private List<WebElement> cartItems;

    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public CartPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return getCurrentUrl().contains("cart.html")
                && isDisplayed(pageTitle)
                && "Your Cart".equalsIgnoreCase(getText(pageTitle));
    }

    public int getItemCount() {
        return cartItems.size();
    }

    public boolean isCartEmpty() {
        return driver.findElements(By.className("cart_item")).isEmpty();
    }

    public CartPage removeAllItems() {
        List<WebElement> removeButtons = driver.findElements(By.cssSelector("button[data-test^='remove']"));
        while (!removeButtons.isEmpty()) {
            click(removeButtons.get(0));
            removeButtons = driver.findElements(By.cssSelector("button[data-test^='remove']"));
        }
        return this;
    }

    public CheckoutInfoPage clickCheckout() {
        click(checkoutButton);
        waitForUrlContains("checkout-step-one");
        return new CheckoutInfoPage(driver);
    }
}