package org.com.globant.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutOverviewPage extends AuthenticatedBasePage {

    @FindBy(id = "finish")
    private WebElement finishButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutOverviewPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return getCurrentUrl().contains("checkout-step-two")
                && isDisplayed(finishButton)
                && isDisplayed(pageTitle);
    }

    public CheckoutCompletePage finishCheckout() {
        wait.until(d -> finishButton.isDisplayed() && finishButton.isEnabled());
        click(finishButton);
        waitForUrlContains("checkout-complete");
        return new CheckoutCompletePage(driver);
    }
}