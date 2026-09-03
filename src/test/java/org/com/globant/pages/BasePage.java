package org.com.globant.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

/**
 * Base page holding shared WebDriver utilities. PageFactory is initialized here
 * so every page object reuses the same wait/click/type helpers.
 */
public abstract class BasePage {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    protected final Actions actions;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        this.actions = new Actions(driver);
        PageFactory.initElements(driver, this);
    }

    protected void click(WebElement element) {
        WebElement clickable = wait.until(ExpectedConditions.elementToBeClickable(element));
        actions.moveToElement(clickable).click().perform();
    }

    protected void type(WebElement element, String text) {
        WebElement visible = wait.until(ExpectedConditions.visibilityOf(element));
        actions.moveToElement(visible).click().perform();
        visible.sendKeys(Keys.chord(Keys.CONTROL, "a"));
        visible.sendKeys(Keys.DELETE);
        visible.sendKeys(text);
    }

    protected String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText().trim();
    }

    protected boolean isDisplayed(WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    protected void waitForUrlContains(String fragment) {
        wait.until(ExpectedConditions.urlContains(fragment));
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}