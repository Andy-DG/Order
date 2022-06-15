package com.example.order.user.customer;

import com.example.order.user.customer.Customer;
import com.example.order.user.customer.CustomerRepository;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.UUID;

class CustomerRepositoryTest {
    Customer rick = new Customer(new Name("Rick", "Sanchez"),
            "rick@sanchez.com",
            new Address("Morty-street", 11, 6910, "Seattle"),
            "+111 (202) 555-0125");

    UUID id = UUID.randomUUID();

    @Test
    @DisplayName("Given a map of customers, when we register a customer, then the customer is stored in the map")
    void givenAMapOfCustomers_whenWeRegisterACustomer_thenTheCustomerIsStoredInTheMap() {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.register(rick);
        Assertions.assertTrue(customerRepository.getCustomerMap().containsValue(rick));
    }

    @Test
    @DisplayName("Given a map of customers, when we register a customer that has an email that is already used, then an error is thrown")
    void givenAMapOfCustomers_whenWeRegisterACustomerThatHasAnEmailThatIsAlreadyUsed_thenAnErrorIsThrown() {
        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.register(rick);

        Customer ricky = new Customer(new Name("Ricky", "Sanchez"),
                "rick@sanchez.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        Assertions.assertThrows(IllegalArgumentException.class, () -> customerRepository.register(ricky));
    }

    @Test
    @DisplayName("Given a map of customers, when we register a customer that is null, then an error is thrown")
    void givenAMapOfCustomers_whenWeRegisterACustomerThatIsNull_thenAnErrorIsThrown() {
        CustomerRepository customerRepository = new CustomerRepository();
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerRepository.register(null));
    }

    @Test
    @DisplayName("Given a map of customers, when we register a customer with same ID, then an error is thrown")
    void givenAMapOfCustomers_whenWeRegisterACustomerWithSameID_thenAnErrorIsThrown() {
        Customer ricky = new Customer(id, new Name("Ricky", "Sanchez"),
                "ricky@sanchez.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        Customer ricky2 = new Customer(id, new Name("Ricky", "Sanchez"),
                "ricky2@sanchez.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        CustomerRepository customerRepository = new CustomerRepository();
        customerRepository.register(ricky);
        Assertions.assertThrows(IllegalArgumentException.class, () -> customerRepository.register(ricky2));
    }
}