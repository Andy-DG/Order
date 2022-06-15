package com.example.order.user.admin;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdminRepositoryTest {
    @Test
    @DisplayName("given a new admin repository, the basic admin is already in the repository")
    void givenANewAdminRepositoryTheBasicAdminIsAlreadyInTheRepository() {
        AdminRepository adminRepository = new AdminRepository();
        Admin admin = new Admin(new Name("Rick", "Sanchez"),
                "rick@sanchez.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (111) 111-1111");
        assertTrue(adminRepository.getAdminMap().values().stream().filter(admins -> admins.getEmail().equals("rick@sanchez.com")).count() == 1);
    }

}