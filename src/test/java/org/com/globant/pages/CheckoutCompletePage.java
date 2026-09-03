package org.com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutCompletePage extends AuthenticatedBasePage {

    @FindBy(className = "complete-header")
    private WebElement completeHeader;

    @FindBy(className = "complete-text")
    private WebElement completeText;

    @FindBy(id = "back-to-products")
    private WebElement backHomeButton;

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return getCurrentUrl().contains("checkout-complete") && isDisplayed(completeHeader);
    }

    public String getThankYouMessage() {
        return getText(completeHeader);
    }

    public boolean isPurchaseSuccessful(String expectedMessageFragment) {
        return getThankYouMessage().toLowerCase().contains(expectedMessageFragment.toLowerCase());
    }

    public InventoryPage backToProducts() {
        click(backHomeButton);
        return new InventoryPage(driver);
    }
}