package com.example.order.customer;

import com.example.order.customer.details.Address;
import com.example.order.customer.details.Name;
import com.example.order.util.Validate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Logger;

@Repository
public class CustomerRepository {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private Map<String, Customer> customerMap;

    public CustomerRepository() {
        this.customerMap = createAndInitializeCustomerMap();
    }

    public Map<String, Customer> createAndInitializeCustomerMap() {
        Customer customer = new Customer("123", new Name("Morty", "Smith"),
                "morty@smith.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");
        customerMap = new HashMap<>();
        customerMap.put(customer.id(), customer);
        return customerMap;
    }

    public Customer register(Customer customer) {
        Validate.objectIsNotNull(customer);
        checkIfCustomerWithIdAlreadyExists(customer);
        checkIfCustomerWithEmailAlreadyExists(customer);
        customerMap.put(customer.id() ,customer);
        logger.info("Registered: " + customer);
        return customer;
    }

    private void checkIfCustomerWithEmailAlreadyExists(Customer customer) {
        if (this.customerMap.values().stream().map(Customer::email).anyMatch(member1-> Objects.equals(member1, customer.email()))){
            throw new IllegalArgumentException("Email is already in use");
        }
    }

    private void checkIfCustomerWithIdAlreadyExists(Customer customer) {
        if (this.customerMap.containsKey(customer.id())){
            throw new IllegalArgumentException("Member already registered!");
        }
    }

    public Map<String, Customer> getCustomerMap() {
        return customerMap;
    }
}
