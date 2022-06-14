package com.example.order.user.admin;

import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Repository
public class AdminRepository {
    private Map<UUID, Admin> adminMap;

    public AdminRepository() {
        this.adminMap = createAndInitializeAdminMap();
    }

    public Map<UUID, Admin> createAndInitializeAdminMap() {
        Admin admin = new Admin(new Name("Rick", "Sanchez"),
                "rick@sanchez.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (111) 111-1111");
        adminMap = new HashMap<>();
        adminMap.put(admin.getId(), admin);
        return adminMap;
    }
}
