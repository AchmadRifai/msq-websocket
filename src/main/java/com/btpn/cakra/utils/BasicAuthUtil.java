package com.btpn.cakra.utils;

import java.util.Base64;

public class BasicAuthUtil {
    public static String basicHeader(String username, String password) {
        final var valueToEncode = String.format("%s:%s", username, password);
        return String.format("Basic %s", Base64.getEncoder().encodeToString(valueToEncode.getBytes()));
    }

    private BasicAuthUtil() {
    }
}
