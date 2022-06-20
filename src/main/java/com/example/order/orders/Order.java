package com.example.order.orders;

import com.example.order.orders.item_group.ItemGroup;
import com.example.order.user.customer.Customer;
import com.example.order.util.ErrorSpecification;
import com.example.order.util.Validate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

public class Order {
    private final UUID id;
    private final List<ItemGroup> itemGroups;
    private final Customer customer;
    private double totalPrice;

    public Order(UUID id, List<ItemGroup> itemGroups, Customer customer) {
        Validate.objectIsNotNull(new ErrorSpecification("item-group: " + itemGroups + " "),itemGroups, customer);
        this.id = id;
        this.itemGroups = itemGroups;
        this.customer = customer;
        this.totalPrice = calculateTotalPrice();
    }

    public double calculateTotalPrice() {
        return itemGroups.stream().mapToDouble(itemGroup -> itemGroup.getSelectedItem().price() * itemGroup.getAmount()).sum();
    }

    public void updatePrice() {
        setTotalPrice(calculateTotalPrice());
    }

    public UUID getId() {
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

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Order order = (Order) o;

        if (Double.compare(order.totalPrice, totalPrice) != 0) return false;
        if (!Objects.equals(id, order.id)) return false;
        if (!Objects.equals(itemGroups, order.itemGroups)) return false;
        return Objects.equals(customer, order.customer);
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemGroups != null ? itemGroups.hashCode() : 0);
        result = 31 * result + (customer != null ? customer.hashCode() : 0);
        temp = Double.doubleToLongBits(totalPrice);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
