package org.com.globant.pages;

import org.com.globant.data.CheckoutData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CheckoutInfoPage extends AuthenticatedBasePage {

    @FindBy(id = "first-name")
    private WebElement firstNameInput;

    @FindBy(id = "last-name")
    private WebElement lastNameInput;

    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;

    @FindBy(id = "continue")
    private WebElement continueButton;

    @FindBy(className = "title")
    private WebElement pageTitle;

    public CheckoutInfoPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return getCurrentUrl().contains("checkout-step-one")
                && isDisplayed(firstNameInput)
                && isDisplayed(pageTitle);
    }

    public CheckoutInfoPage fillPersonalData(CheckoutData checkoutData) {
        type(firstNameInput, checkoutData.getFirstName());
        type(lastNameInput, checkoutData.getLastName());
        type(postalCodeInput, checkoutData.getPostalCode());
        return this;
    }

    public CheckoutOverviewPage continueToOverview() {
        click(continueButton);
        waitForUrlContains("checkout-step-two");
        return new CheckoutOverviewPage(driver);
    }
}