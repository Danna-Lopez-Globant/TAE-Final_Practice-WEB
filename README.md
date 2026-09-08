# TAE-Final_Practice-WEB: SauceDemo POM Practice

Selenium + Java + TestNG automation for [Sauce Demo](https://www.saucedemo.com/) using Page Object Model and Page Factory.

## Requirements

- JDK 17+
- Apache Maven 3.9+
- Google Chrome

## Scenarios

| Test | Description |
|------|-------------|
| `PurchaseProductTest` | Random product → cart → checkout data → thank-you page |
| `RemoveCartItemsTest` | Add 3 products → remove all → assert empty cart |
| `LogoutTest` | Open menu → logout → assert login page |

## Framework design

```
src/test/java/com/saucedemo/
├── base/BaseTest.java
├── data/
│   ├── DataReader.java             # Loads classpath properties
│   ├── TestData.java               # Facade for tests
│   ├── UserCredentials.java
│   └── CheckoutData.java
├── pages/
│   ├── BasePage.java
│   ├── AuthenticatedBasePage.java
│   ├── LoginPage.java
│   ├── InventoryPage.java
│   ├── CartPage.java
│   ├── CheckoutInfoPage.java
│   ├── CheckoutOverviewPage.java
│   └── CheckoutCompletePage.java
└── tests/
    ├── PurchaseProductTest.java
    ├── RemoveCartItemsTest.java
    └── LogoutTest.java
src/test/resources/data/testdata.properties
```

- **POM + Page Factory** via `@FindBy` and `PageFactory.initElements`
- **Base pages** for shared actions (`BasePage`) and authenticated header (`AuthenticatedBasePage`)
- **Data layer** in `src/test/resources/data/testdata.properties` (no hardcoded credentials/checkout data in tests)
- **`@BeforeMethod`** for browser launch and login / cart preconditions
- **`@AfterMethod`** quits the driver

## Data layer

Edit `src/test/resources/data/testdata.properties` to change URL, credentials, checkout fields, or cart item count. Access via `TestData`, `UserCredentials`, and `CheckoutData`.

## Run tests

```bash
mvn clean test
```

Single class:

```bash
mvn -Dtest=PurchaseProductTest test
```

## Credentials

| Field    | Value           |
|----------|-----------------|
| Username | `standard_user` |
| Password | `secret_sauce`  |


## AI usage disclosure

AI assistance was used to:

- Create this README
- Add or adjust code comments
- Structure the data layer (data/ + testdata.properties)
- Disable the Chrome Password Manager alert in BaseTest