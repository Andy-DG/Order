package com.example.order.util;

public class Validator {
    public static boolean isEmptyOrNull(String string) {
        return string == null || string.isBlank();
    }

    public static boolean isNegative(int number) {
        return number < 0;
    }

    public static boolean isNegative(double number) {
        return number < 0;
    }
}
