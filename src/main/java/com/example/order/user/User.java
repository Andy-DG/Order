package com.example.order.user;

import com.example.order.user.footing.domain.Address;
import com.example.order.user.footing.domain.Name;
import com.example.order.util.Validator;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@MappedSuperclass
public abstract class User {
    public static final String OWASP_EMAIL_VALIDATION = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

    public static final String PHONE_VALIDATION =
            "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
                    + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";

    @Id
    private String id;

    @Embedded
    private Name name;

    @Column(name = "EMAIL")
    private String email;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "FK_ADDRESS_ID")
    private Address address;

    @Column(name = "PHONE_NUMBER")
    private String phoneNumber;

    public User() {
    }

    protected User(Name name, String email, Address address, String phoneNumber) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.email = validateEmail(email);
        this.address = validateAddress(address);
        this.phoneNumber = validatePhoneNumber(phoneNumber);
    }

    private Address validateAddress(Address address) {
        if (address == null) throw new IllegalArgumentException("There must be an address");
        return address;
    }

    //  Email has to be of format: rick@sanchez.net
    private static String validateEmail(String email) throws IllegalArgumentException {
        if (Validator.isEmptyOrNull(email))
            throw new IllegalArgumentException("Email cannot be null or blank.");
        Pattern pattern = Pattern.compile(OWASP_EMAIL_VALIDATION);
        Matcher matcher = pattern.matcher(email);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid e-mail format");
        }
        return email;
    }

    //  Phone-number has to be of one of the following formats: 2055550125, 202 555 0125, (202) 555-0125, +111 (202) 555-0125,
    //      636 856 789, +111 636 856 789, 636 85 67 89, +111 636 85 67 89
    private static String validatePhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (Validator.isEmptyOrNull(phoneNumber))
            throw new IllegalArgumentException("Phone-number cannot be null or blank.");
        Pattern pattern = Pattern.compile(PHONE_VALIDATION);
        Matcher matcher = pattern.matcher(phoneNumber);
        if (!matcher.matches()) {
            throw new IllegalArgumentException("Invalid phone-number format");
        }
        return phoneNumber;
    }

    public String getId() {
        return id;
    }

    public Name getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name=" + name +
                ", email='" + email + '\'' +
                ", address=" + address +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return Objects.equals(id, user.id) && Objects.equals(name, user.name) && Objects.equals(email, user.email) && Objects.equals(address, user.address) && Objects.equals(phoneNumber, user.phoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, address, phoneNumber);
    }
}
