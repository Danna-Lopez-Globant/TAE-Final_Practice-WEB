package org.com.globant.data;

/**
 * Typed access to credentials used for authentication scenarios.
 */
public final class UserCredentials {

    private final String username;
    private final String password;

    public UserCredentials(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public static UserCredentials fromDataLayer() {
        return new UserCredentials(
                DataReader.get("user.username"),
                DataReader.get("user.password")
        );
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}