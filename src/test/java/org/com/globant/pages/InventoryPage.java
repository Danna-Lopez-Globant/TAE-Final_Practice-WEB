package org.com.globant.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class InventoryPage extends AuthenticatedBasePage {

    @FindBy(className = "title")
    private WebElement pageTitle;

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItems;

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLoaded() {
        return getCurrentUrl().contains("inventory")
                && isDisplayed(pageTitle)
                && "Products".equalsIgnoreCase(getText(pageTitle));
    }

    public int getProductCount() {
        return inventoryItems.size();
    }

    /**
     * Selects a random product, adds it to the cart, and returns its display name.
     */
    public String addRandomProductToCart() {
        List<WebElement> addButtons = driver.findElements(By.cssSelector("button[data-test^='add-to-cart']"));
        if (addButtons.isEmpty()) {
            throw new IllegalStateException("No products available to add on the inventory page.");
        }

        int index = new Random().nextInt(addButtons.size());
        WebElement addButton = addButtons.get(index);
        WebElement product = addButton.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));
        String productName = product.findElement(By.className("inventory_item_name")).getText();

        int before = getCartBadgeCount();
        click(addButton);
        wait.until(d -> getCartBadgeCount() == before + 1);
        return productName;
    }

    /**
     * Adds the first {@code count} distinct products to the cart and returns their names.
     */
    public List<String> addFirstNProductsToCart(int count) {
        List<String> addedNames = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            List<WebElement> addButtons = driver.findElements(By.cssSelector("button[data-test^='add-to-cart']"));
            if (addButtons.isEmpty()) {
                throw new IllegalStateException("Not enough products to add. Added so far: " + addedNames);
            }

            WebElement addButton = addButtons.get(0);
            WebElement product = addButton.findElement(By.xpath("./ancestor::div[contains(@class,'inventory_item')]"));
            addedNames.add(product.findElement(By.className("inventory_item_name")).getText());

            int expectedBadge = i + 1;
            click(addButton);
            wait.until(d -> getCartBadgeCount() == expectedBadge);
        }

        return addedNames;
    }
}