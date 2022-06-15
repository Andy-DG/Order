package com.example.order.user.customer;

import com.example.order.orders.Order;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
    }

    public void registerCustomer(CreateCustomerDTO createCustomerDTO) {
        Customer customer = this.customerMapper.toEntity(createCustomerDTO);
        customerRepository.register(customer);
    }

    public void addOrder(Order order) {
        Customer customer = order.getCustomer();
        customer.addCustomerOrder(order);
    }
}
