package com.example.order.customer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NameTest {
    @Test
    void givenALastName_whenCreatingName_thenLastNameIsNotEmpty() {
        String lastName = "Sanchez";
        Name name = new Name("Rick", lastName);
        assertEquals(lastName, name.lastName());
    }

    @Test
    void givenAFirstNameThatIsBlank_whenCreatingName_thenIllegalArgumentException() {
        String firstName = "";
        assertThrows(IllegalArgumentException.class, ()-> new Name(firstName, "Sanchez"));
    }

    @Test
    void givenAFirstNameThatIsNull_whenCreatingName_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> new Name(null, "Sanchez"));
    }

    @Test
    void givenALastNameThatIsBlank_whenCreatingName_thenIllegalArgumentException() {
        String lastName = "";
        assertThrows(IllegalArgumentException.class, ()-> new Name("Rick", lastName));
    }

    @Test
    void givenALastNameThatIsNull_whenCreatingName_thenIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, ()-> new Name("Rick", null));
    }
}