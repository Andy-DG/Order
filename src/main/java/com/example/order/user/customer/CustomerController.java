package com.example.order.user.customer;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> viewAllCustomers() {
        return this.customerService.viewAllCustomers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO viewCustomer(@PathVariable UUID id){
        return this.customerService.getCustomerById(id);
    }

}
