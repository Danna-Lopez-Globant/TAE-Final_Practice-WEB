package org.com.globant.pages;

import org.com.globant.data.TestData;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    private WebElement usernameInput;

    @FindBy(id = "password")
    private WebElement passwordInput;

    @FindBy(id = "login-button")
    private WebElement loginButton;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String username) {
        type(usernameInput, username);
        return this;
    }

    public LoginPage enterPassword(String password) {
        type(passwordInput, password);
        return this;
    }

    public InventoryPage clickLogin() {
        click(loginButton);
        waitForUrlContains("inventory");
        return new InventoryPage(driver);
    }

    public InventoryPage login(String username, String password) {
        enterUsername(username);
        enterPassword(password);
        return clickLogin();
    }

    public boolean isLoginButtonDisplayed() {
        return isDisplayed(loginButton);
    }

    public boolean isOnLoginPage() {
        String current = getCurrentUrl().replaceAll("/$", "");
        String expected = TestData.getBaseUrl().replaceAll("/$", "");
        return current.equalsIgnoreCase(expected) && isLoginButtonDisplayed();
    }
}