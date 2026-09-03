package org.com.globant.data;

/**
 * Typed access to checkout personal information.
 */
public final class CheckoutData {

    private final String firstName;
    private final String lastName;
    private final String postalCode;

    public CheckoutData(String firstName, String lastName, String postalCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.postalCode = postalCode;
    }

    public static CheckoutData fromDataLayer() {
        return new CheckoutData(
                DataReader.get("checkout.firstName"),
                DataReader.get("checkout.lastName"),
                DataReader.get("checkout.postalCode")
        );
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPostalCode() {
        return postalCode;
    }
}