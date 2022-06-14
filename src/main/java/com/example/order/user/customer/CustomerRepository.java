package com.example.order.user.customer;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import com.example.order.util.Validate;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.logging.Logger;

@Repository
public class CustomerRepository {
    private final Logger logger = Logger.getLogger(this.getClass().getName());

    private Map<UUID, Customer> customerMap;

    public CustomerRepository() {
        this.customerMap = createAndInitializeCustomerMap();
    }

    public Map<UUID, Customer> createAndInitializeCustomerMap() {
        Customer customer = new Customer(new Name("Morty", "Smith"),
                "morty@smith.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");
        customerMap = new HashMap<>();
        customerMap.put(customer.getId(), customer);
        return customerMap;
    }

    public void register(Customer customer) {
        Validate.objectIsNotNull(customer);
        checkIfCustomerWithIdAlreadyExists(customer);
        checkIfCustomerWithEmailAlreadyExists(customer);
        customerMap.put(customer.getId() ,customer);
        logger.info("Registered: " + customer);
    }

    private void checkIfCustomerWithEmailAlreadyExists(Customer customer) {
        if (this.customerMap.values().stream().map(Customer::getEmail).anyMatch(member1-> Objects.equals(member1, customer.getEmail()))){
            throw new IllegalArgumentException("Email is already in use");
        }
    }

    private void checkIfCustomerWithIdAlreadyExists(Customer customer) {
        if (this.customerMap.containsKey(customer.getId())){
            throw new IllegalArgumentException("Member already registered!");
        }
    }

    public Map<UUID, Customer> getCustomerMap() {
        return customerMap;
    }
}
