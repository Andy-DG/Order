package com.example.order.user.footing.domain;

import com.example.order.util.Validator;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
public final class Name {
    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "LAST_NAME")
    private String lastName;

    public Name(String firstName, String lastName) {
        this.firstName = validateFirstName(firstName);;
        this.lastName = validateLastName(lastName);;
    }

    private String validateLastName(String lastName) {
        if (Validator.isEmptyOrNull(lastName)) throw new IllegalArgumentException("A name must have a last-name.");
        return lastName;
    }

    private String validateFirstName(String firstName) {
        if (Validator.isEmptyOrNull(firstName)) throw new IllegalArgumentException("A name must have a first-name.");
        return firstName;
    }

    @Override
    public String toString() {
        return "Name{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Name) obj;
        return Objects.equals(this.firstName, that.firstName) &&
                Objects.equals(this.lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }

}
