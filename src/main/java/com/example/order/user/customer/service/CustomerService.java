package com.example.order.user.customer.service;

import com.example.order.orders.domain.Order;
import com.example.order.user.customer.api.dto.CreateCustomerDTO;
import com.example.order.user.customer.api.dto.CustomerDTO;
import com.example.order.user.customer.domain.Customer;
import com.example.order.user.customer.domain.CustomerRepository;
import com.example.order.user.footing.api.dto.AddressDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {
    private final Logger customerServiceLogger = LoggerFactory.getLogger(CustomerService.class);

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO createCustomer(CreateCustomerDTO createCustomerDTO) {
        assertAddressExists(createCustomerDTO.getAddressDTO());
        assertEmailIsUnique(createCustomerDTO.getEmail());
        Customer customer = this.customerMapper.toEntity(createCustomerDTO);
        customerRepository.save(customer);
        return customerMapper.toDTO(customer);
    }


    public List<CustomerDTO> viewAllCustomers() {
        return customerMapper.toDTO(customerRepository.findAll());
    }

    public CustomerDTO viewCustomer(String id) {
        return customerMapper.toDTO(customerRepository.getReferenceById(id));
    }

    private void assertEmailIsUnique(String email) {
        if (customerRepository.findAll().stream()
                .anyMatch(customer -> customer.getEmail().equalsIgnoreCase(email))) {
            customerServiceLogger.error("Attempted to create customer with already existing e-mail address");
            throw new IllegalArgumentException("Unable to use given e-mail address for new customer's email address");
        }
        customerServiceLogger.info("Successfully validated new customer's email address to be unique");
    }


    private void assertAddressExists(AddressDTO address) {
        if (address == null) {
            customerServiceLogger.error("Customer can't be created without address");
            throw new IllegalArgumentException("No address given for new customer");
        }
        customerServiceLogger.info("Successfully validated new customer's address");
    }
}
