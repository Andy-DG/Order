package com.example.order.orders.domain;

import com.example.order.item_group.domain.ItemGroup;
import com.example.order.user.customer.domain.Customer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ORDERS")
public class Order {
    @Transient
    private final Logger orderLogger = LoggerFactory.getLogger(Order.class);

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "FK_CUSTOMER_ID")
    private Customer customer;

    @OneToMany(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "FK_ORDER_ID", nullable = false)
    private List<ItemGroup> itemGroups;

    @Column(name = "TOTAL_PRICE")
    private double totalPrice;

    public Order() {
    }

    public Order(Customer customer, List<ItemGroup> itemGroups) {
        this.id = UUID.randomUUID().toString();
        this.customer = customer;
        this.itemGroups = itemGroups;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        return itemGroups.stream().mapToDouble(itemGroup -> itemGroup.getPricePerItem() * itemGroup.getAmount()).sum();
    }

    public String getId() {
        return id;
    }

    public List<ItemGroup> getItemGroups() {
        return itemGroups;
    }

    public Customer getCustomer() {
        return customer;
    }

    public double getTotalPrice() {
        return totalPrice;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        return Objects.equals(id, order.id) && Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customer);
    }

    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", customer=" + customer +
                ", itemGroups=" + itemGroups +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
