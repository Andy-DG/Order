package com.example.order.user.customer;

import com.example.order.orders.Order;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public CustomerDTO registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = this.customerMapper.toEntity(createCustomerDTO);
        CustomerDTO customerDTO = this.customerMapper.toDTO(customer);
        customerRepository.register(customer);
        return customerDTO;
    }

    public void addOrder(Order order) {
        Customer customer = order.getCustomer();
        customer.addCustomerOrder(order);
    }

    private List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public List<CustomerDTO> viewAllCustomers() {
        List<Customer> customers = this.getAllCustomers();
        return customerMapper.toDTO(customers);
    }

    public CustomerDTO getCustomerById(String id) {
        Customer customer = this.customerRepository.getCustomerById(id);
        return customerMapper.toDTO(customer);
    }
}
