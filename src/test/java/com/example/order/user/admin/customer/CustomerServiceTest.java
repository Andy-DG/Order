package com.example.order.user.admin.customer;

import com.example.order.user.customer.*;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerServiceTest {
    CustomerService customerService;
    CustomerMapper customerMapper;
    CustomerRepository customerRepository;

    @BeforeEach
    void setUp(){
        this.customerMapper = new CustomerMapper();
        this.customerRepository = new CustomerRepository();
        this.customerService = new CustomerService(customerMapper, customerRepository);
    }

    @Test
    void givenCorrectData_whenCreatingACustommer_ThenOk(){
        Name name = new Name("Morty", "Smith");
        Address address = new Address("Morty-street", 11, 6910, "Seattle");
        String email = "morty@smithy.com";
        String phoneNumber = "+111 (202) 555-0125";
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(name,
                email,
                address,
                phoneNumber);

        CustomerDTO customerAccount = this.customerService.registerCustomer(createCustomerDTO);

        assertEquals(customerAccount, createCustomerDTO);
    }

    @Test
    @DisplayName("Given all fields are filled in properly when creating two costumers with different emails, then succesfully create customers.")
    void givenAllFieldsFilled_whenCreatingTwoUniqueCustomer_thenSuccesfullyCreate(){
        int intialSize = this.customerRepository.getCustomerMap().size();

        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                new Name("Morty", "Smith"),
                "morty@smithy.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        CreateCustomerDTO createCustomerDTO2 = new CreateCustomerDTO(
                new Name("Morty", "Smith"),
                "morty@smithy2.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        CustomerDTO customerAccount = this.customerService.registerCustomer(createCustomerDTO);
        CustomerDTO customerAccount2 = this.customerService.registerCustomer(createCustomerDTO2);

        assertEquals(intialSize+2, this.customerRepository.getCustomerMap().keySet().size());
    }

    @Test
    @DisplayName("When trying to register same email twice then throw exception")
    void givenACustomer_whenTryingToRegisterSameEmail_thenCustomerAlreadyExistsException(){
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                new Name("Morty", "Smith"),
                "morty@smithy.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        CustomerDTO customerAccount = this.customerService.registerCustomer(createCustomerDTO);

        assertThrows(IllegalArgumentException.class, ()->this.customerService.registerCustomer(createCustomerDTO));
    }



    @Test
    @DisplayName("When trying to register a customer without an email, then throw exception")
    void givenCustomerWithNoFirstName_thenThrowIllegalArgumentException(){
        CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO(
                new Name("Morty", "Smith"),
                "",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");
        assertThrows(IllegalArgumentException.class, ()->{
            customerService.registerCustomer(createCustomerDTO);
        });
    }

}