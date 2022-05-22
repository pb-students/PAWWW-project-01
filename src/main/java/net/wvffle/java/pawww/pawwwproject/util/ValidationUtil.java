package net.wvffle.java.pawww.pawwwproject.util;

import java.util.Locale;

public class ValidationUtil {
    public static String validateUsername (String username) {
        if (username.length() < 3) {
            return "Username too short (min. 3 chars)";
        }

        if (username.length() > 20) {
            return "Username too long (max. 20 chars)";
        }

        if (!username.equals(username.toLowerCase(Locale.ROOT))) {
            return "Username has to be lowercase";
        }

        return null;
    }

    public static String validatePassword (String password) {
        if (password.length() < 5) {
            return "Password too short (min. 5 chars)";
        }

        return null;
    }
}
