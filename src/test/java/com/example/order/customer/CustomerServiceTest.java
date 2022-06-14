package com.example.order.customer;

import com.example.order.user.customer.CustomerMapper;
import com.example.order.user.customer.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;

class CustomerServiceTest {
    CustomerMapper customerMapper;
    CustomerRepository customerRepository;

    @BeforeEach
    void beforeEach() {
        this.customerMapper = new CustomerMapper();
        this.customerRepository = new CustomerRepository();
    }
//
//    @Test
//    @DisplayName("Given a Customer with proper fields, when registering customer, then success")
//    void givenCustomerWithProperFields_whenRegistering_thenSuccess() {
//        String id = UUID.randomUUID().toString();
//        CreateLibrarianDTO createLibrarianDTO = new CreateLibrarianDTO(inss, "libr@vox.com", "John", "Doe", "12345pass");
//
//        this.librarianRepository.registerLibrarian(this.librarianMapper.toEntity(createLibrarianDTO));
//
//        assertTrue(this.librarianRepository.getMap().containsKey(createLibrarianDTO.getInss()));
//    }
//
//
//    @Test
//    @DisplayName("Given two librarian with proper fields but same isnn, when registering, then exception")
//    void givenTwoLibrarianWithSameIsnn_whenRegistering_throwException() {
//        String inss = UUID.randomUUID().toString();
//        CreateLibrarianDTO createLibrarianDTO = new CreateLibrarianDTO(inss, "libr@vox.com", "John", "Doe", "12345pass");
//        CreateLibrarianDTO createLibrarianDTO2 = new CreateLibrarianDTO(inss, "frr@vox.com", "Fred", "Mex", "54321wordup");
//
//        this.librarianRepository.registerLibrarian(this.librarianMapper.toEntity(createLibrarianDTO));
//
//        assertThrows(IllegalArgumentException.class, () -> this.librarianRepository.registerLibrarian(this.librarianMapper.toEntity((createLibrarianDTO2))));
//    }
//
//    @Test
//    @DisplayName("When creating a librarian with no inss, throw Exception")
//    void whenCreatingLibrarianWithNoInss_throwException() {
//        CreateLibrarianDTO createLibrarianDTO = new CreateLibrarianDTO(" ", "libr@vox.com", "John", "Doe", "12345pass");
//        assertThrows(IllegalArgumentException.class, () -> this.librarianMapper.toEntity(createLibrarianDTO));
//    }
//
//    @Test
//    @DisplayName("When creating a librarian with bad email, throw Exception")
//    void whenCreatingLibrarianWithbadEmail_throwException() {
//        String inss = UUID.randomUUID().toString();
//        CreateLibrarianDTO createLibrarianDTO = new CreateLibrarianDTO(inss, "john@gmail", "John", "Doe", "12345pass");
//        assertThrows(IllegalArgumentException.class, () -> this.librarianMapper.toEntity(createLibrarianDTO));
//    }

}