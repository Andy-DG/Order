package com.example.order.user.customer;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("customers/add")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public CreateCustomerDTO registerCustomer(@RequestBody CreateCustomerDTO createCustomerDTO){
        this.customerService.registerCustomer(createCustomerDTO);
        return createCustomerDTO;
    }

}
