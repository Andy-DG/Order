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
}
