package com.example.cobe.cryptonews.common.utils;

/**
 * Created by cobe on 03/04/2018.
 */

public class ValidationUtils {

    public static boolean isEmpty(String string) {
        return (string != null && string.isEmpty() && string.trim().isEmpty());
    }
}
