package com.example.order.user.customer.api;

import com.example.order.user.customer.api.dto.CreateCustomerDTO;
import com.example.order.user.customer.api.dto.CustomerDTO;
import com.example.order.user.customer.service.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customers")
public class CustomerController {
    CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping(path = "/add", consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDTO createCustomer(@RequestBody CreateCustomerDTO createCustomerDTO){
        return customerService.createCustomer(createCustomerDTO);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDTO> viewAllCustomers() {
        return customerService.viewAllCustomers();
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDTO viewCustomer(@PathVariable String id){
        return customerService.viewCustomer(id);
    }

}
