package org.com.globant.data;

/**
 * Facade over the data layer for tests and base classes.
 */
public final class TestData {

    private TestData() {
    }

    public static String getBaseUrl() {
        return DataReader.get("base.url");
    }

    public static UserCredentials getStandardUser() {
        return UserCredentials.fromDataLayer();
    }

    public static CheckoutData getCheckoutData() {
        return CheckoutData.fromDataLayer();
    }

    public static int getCartItemsToAdd() {
        return DataReader.getInt("cart.items.to.add");
    }

    public static String getCheckoutSuccessMessage() {
        return DataReader.get("checkout.success.message");
    }
}