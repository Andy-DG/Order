package com.example.order.user.footing.domain;

import com.example.order.util.Validator;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "ADDRESS")

public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "address_seq")
    @SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
    private long id;

    @Column(name = "STREET_NAME")
    private String streetName;

    @Column(name = "STREET_NUMBER")
    private int streetNumber;

    @Column(name = "POSTAL_CODE")
    private int postalCode;

    @Column(name = "CITY")
    private String city;

    public Address(String streetName, int streetNumber, int postalCode, String city) {
        this.streetName = validateStreetName(streetName);
        this.streetNumber = validateStreetNumber(streetNumber);
        this.postalCode = validatePostalCode(postalCode);;
        this.city = validateCity(city);
    }

    public Address() {

    }

    private String validateCity(String city) {
        if (Validator.isEmptyOrNull(city)) throw new IllegalArgumentException("An address needs a city.");
        return city;
    }

    private int validatePostalCode(int postalCode) {
        if (Validator.isNegative(postalCode)) throw new IllegalArgumentException("A postal-code can't be negative.");
        return postalCode;
    }

    private int validateStreetNumber(int streetNumber) {
        if (Validator.isNegative(streetNumber))
            throw new IllegalArgumentException("A street-number can't be negative.");
        return streetNumber;
    }

    private String validateStreetName(String streetName) {
        if (Validator.isEmptyOrNull(streetName)) throw new IllegalArgumentException("An address needs a street-name.");
        return streetName;
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetName='" + streetName + '\'' +
                ", streetNumber=" + streetNumber +
                ", postalCode=" + postalCode +
                ", city='" + city + '\'' +
                '}';
    }

    public String streetName() {
        return streetName;
    }

    public int streetNumber() {
        return streetNumber;
    }

    public int postalCode() {
        return postalCode;
    }

    public String city() {
        return city;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Address) obj;
        return Objects.equals(this.streetName, that.streetName) &&
                this.streetNumber == that.streetNumber &&
                this.postalCode == that.postalCode &&
                Objects.equals(this.city, that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streetName, streetNumber, postalCode, city);
    }

}
