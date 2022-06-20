package com.example.order.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ValidateTest {
    @Test
    @DisplayName("Given An Empty String, Throw An Exception")
    void givenAnEmptyStringThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("String");
        assertThrows(IllegalArgumentException.class, ()-> Validate.stringIsNotEmptyOrNull(errorSpecification, ""));
    }

    @Test
    @DisplayName("Given A Null String, Throw An Exception")
    void givenANullStringThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("String");
        assertThrows(IllegalArgumentException.class, ()-> Validate.stringIsNotEmptyOrNull(errorSpecification, (String) null));
    }

    @Test
    @DisplayName("Given A Null Object, Throw An Exception")
    void givenANullObjectThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Object");
        assertThrows(IllegalArgumentException.class, ()-> Validate.objectIsNotNull(errorSpecification, (Object) null));
    }

    @Test
    @DisplayName("Given A Negative Number, Throw An Exception")
    void givenANegativeNumberThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotNegative(errorSpecification, -1));
    }

    @Test
    @DisplayName("Given Zero, Throw An Exception")
    void givenZeroThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotZero(errorSpecification, 0));
    }

    @Test
    @DisplayName("Given A Negative Number Double, Throw An Exception")
    void givenANegativeNumberDoubleThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotNegative(errorSpecification, -1.6));
    }

    @Test
    @DisplayName("Given Zero Double, Throw An Exception")
    void givenZeroDoubleThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.numberIsNotZero(errorSpecification, 0.0));
    }

    @Test
    @DisplayName("Given A Bad Email Format, Throw An Exception")
    void givenABadEmailFormatThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("E-mail");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail(errorSpecification, "this-is-not-a-correct-email-format"));
    }

    @Test
    @DisplayName("Given An Empty Email, Throw An Exception")
    void givenAnEmptyEmailThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("E-mail");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail(errorSpecification,""));
    }

    @Test
    @DisplayName("Given A Null Email, Throw An Exception")
    void givenANullEmailThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("E-mail");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validateEmail(errorSpecification, (String) null));
    }

    @Test
    @DisplayName("Given An Invalid PhoneNumber, Throw An Exception")
    void givenAnInvalidPhoneNumberThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Phone-number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber(errorSpecification,"999-8754-775"));
    }

    @Test
    @DisplayName("Given An Empty PhoneNumber, Throw An Exception")
    void givenAnEmptyPhoneNumberThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Phone-number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber(errorSpecification,""));
    }

    @Test
    @DisplayName("Given A Null PhoneNumber, Throw An Exception")
    void givenANullPhoneNumberThrowAnException() {
        ErrorSpecification errorSpecification = new ErrorSpecification("Phone-number");
        assertThrows(IllegalArgumentException.class, ()-> Validate.validatePhoneNumber(errorSpecification, (String) null));
    }

}