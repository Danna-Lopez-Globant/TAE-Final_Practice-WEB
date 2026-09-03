package org.com.globant.base;

import org.com.globant.data.TestData;
import org.com.globant.data.UserCredentials;
import org.com.globant.pages.InventoryPage;
import org.openqa.selenium.WebDriver;
import org.com.globant.pages.LoginPage;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public abstract class BaseTest {
    protected WebDriver driver;
    protected LoginPage loginPage;
    protected InventoryPage inventoryPage;

    @BeforeMethod(alwaysRun = true)
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--disable-notifications");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-features=PasswordLeakDetection,PasswordManagerOnboarding,PasswordCheck");

        // Prevent Chrome Password Manager breach warning from blocking UI clicks.
        Map<String, Object> prefs = new HashMap<>();
        prefs.put("credentials_enable_service", false);
        prefs.put("profile.password_manager_enabled", false);
        prefs.put("profile.password_manager_leak_detection", false);
        options.setExperimentalOption("prefs", prefs);
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().window().maximize();
        driver.get(TestData.getBaseUrl());

        loginPage = new LoginPage(driver);
    }

    /**
     * Shared precondition: authenticate with credentials from the data layer.
     */
    protected InventoryPage loginAsStandardUser() {
        UserCredentials credentials = TestData.getStandardUser();
        inventoryPage = loginPage.login(credentials.getUsername(), credentials.getPassword());
        return inventoryPage;
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}