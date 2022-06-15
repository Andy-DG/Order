package com.example.order.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    @Test
    @DisplayName("givenAnEmptyStringThrowAnException")
    void givenAnEmptyStringThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.stringIsNotEmptyOrNull(""));
    }

    @Test
    @DisplayName("givenANullStringThrowAnException")
    void givenANullStringThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.stringIsNotEmptyOrNull((String) null));
    }

    @Test
    @DisplayName("givenANullObjectThrowAnException")
    void givenANullObjectThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.objectIsNotNull((Object) null));
    }

    @Test
    @DisplayName("givenANegativeNumberThrowAnException")
    void givenANegativeNumberThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotNegative(-1));
    }

    @Test
    @DisplayName("givenZeroThrowAnException")
    void givenZeroThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotZero(0));
    }

    @Test
    @DisplayName("givenANegativeNumberDoubleThrowAnException")
    void givenANegativeNumberDoubleThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotNegative(-1.6));
    }

    @Test
    @DisplayName("givenZeroDoubleThrowAnException")
    void givenZeroDoubleThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotZero(0.0));
    }

    @Test
    @DisplayName("givenABadEmailFormatThrowAnException")
    void givenABadEmailFormatThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail("this-is-not-a-correct-email-format"));
    }

    @Test
    @DisplayName("givenAnEmptyEmailThrowAnException")
    void givenAnEmptyEmailThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail(""));
    }

    @Test
    @DisplayName("givenAnNullEmailThrowAnException")
    void givenANullEmailThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail((String) null));
    }

    @Test
    @DisplayName("givenAnInvalidPhoneNumberThrowAnException")
    void givenAnInvalidPhoneNumberThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber("999-8754-775"));
    }

    @Test
    @DisplayName("givenAnEmptyPhoneNumberThrowAnException")
    void givenAnEmptyPhoneNumberThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber(""));
    }

    @Test
    @DisplayName("givenANullPhoneNumberThrowAnException")
    void givenANullPhoneNumberThrowAnException() {
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber((String) null));
    }

}