package com.example.order.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validate {
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String PHONE_VALIDATION =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    private Validate() {
    }

    public static void objectIsNotNull(ErrorSpecification errorSpecification, Object... objects) {
        for (Object object : objects) {
            if (object == null) {
                throw new IllegalArgumentException(errorSpecification + " cannot be null!");
            }
        }
    }

    public static void stringIsNotEmptyOrNull(ErrorSpecification errorSpecification, String... strings) throws IllegalArgumentException {
        for (String string : strings) {
            if (string == null || string.isBlank()) {
                throw new IllegalArgumentException(errorSpecification + " cannot be empty or null!");
            }
        }
    }

    public static void numberIsNotZero(ErrorSpecification errorSpecification, int... numbers) throws IllegalArgumentException {
        for (int number : numbers) {
            if (number == 0) throw new IllegalArgumentException(errorSpecification + " cannot be zero!");
        }
    }

    public static void numberIsNotNegative(ErrorSpecification errorSpecification, int... numbers) throws IllegalArgumentException {
        for (int number : numbers) {
            if (number < 0) throw new IllegalArgumentException(errorSpecification + " cannot be negative!");
        }
    }

    public static void numberIsNotZero(ErrorSpecification errorSpecification, double... numbers) throws IllegalArgumentException {
        for (double number : numbers) {
            if (number == 0) throw new IllegalArgumentException(errorSpecification + "Number cannot be zero!");
        }
    }

    public static void numberIsNotNegative(ErrorSpecification errorSpecification, double... numbers) throws IllegalArgumentException {
        for (double number : numbers) {
            if (number < 0) throw new IllegalArgumentException(errorSpecification + " cannot be negative!");
        }
    }

    //  Email has to be of format: rick@sanchez.net
    public static void validateEmail(ErrorSpecification errorSpecification, String... emails) throws IllegalArgumentException {
        for (String email : emails) {
            if (email == null) {
                throw new IllegalArgumentException(errorSpecification + " cannot be empty");
            }
            Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
            Matcher matcher = pattern.matcher(email);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid e-mail format");
            }
        }
    }

    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    public static void validatePhoneNumber(ErrorSpecification errorSpecification, String... phoneNumbers) throws IllegalArgumentException {
        for (String phoneNumber : phoneNumbers) {
            if (phoneNumber == null) {
                throw new IllegalArgumentException(errorSpecification + " cannot be empty");
            }
            Pattern pattern = Pattern.compile(PHONE_VALIDATION);
            Matcher matcher = pattern.matcher(phoneNumber);
            if (!matcher.matches()) {
                throw new IllegalArgumentException("Invalid phone-number format");
            }
        }
    }
}
