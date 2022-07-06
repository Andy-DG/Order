package com.example.order.item_group.domain;


import com.example.order.item.domain.Item;
import com.example.order.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ITEM_GROUP")
public class ItemGroup {
    private static final int DAYS_TO_ADD_IN_STOCK = 1;
    private static final int DAYS_TO_ADD_NO_STOCK = 7;

    @Transient
    private final Logger itemGroupLogger = LoggerFactory.getLogger(ItemGroup.class);

    @Id
    private String id;

    @ManyToOne
    @JoinColumn(name = "FK_ITEM_ID")
    private Item item;

    @Column(name = "AMOUNT")
    private int amount;

    @Column(name = "PRICE")
    private double pricePerItem;

    @Column(name = "SHIPPING_DATE")
    private LocalDate shippingDate;

    public ItemGroup() {
    }

    public ItemGroup(Item item, int amount) {
        this.id = UUID.randomUUID().toString();
        this.item = item;
        this.amount = validateAmount(amount);
        this.pricePerItem = item.getPrice();
        this.shippingDate = calculateShippingDate();
    }

    private LocalDate calculateShippingDate() {
        int stock = item.getStock();
        if (stock < amount) {
            return LocalDate.now().plusDays(DAYS_TO_ADD_NO_STOCK);
        }
        return LocalDate.now().plusDays(DAYS_TO_ADD_IN_STOCK);
    }

    private int validateAmount(int amount) {
        if (Validator.isNegative(amount)) {
            itemGroupLogger.error("Amount cannot be negative.");
            throw new IllegalArgumentException("Amount cannot be negative.");
        }
        return amount;
    }

    public String getId() {
        return id;
    }

    public Item getItem() {
        return item;
    }

    public int getAmount() {
        return amount;
    }

    public double getPricePerItem() {
        return pricePerItem;
    }

    public LocalDate getShippingDate() {
        return shippingDate;
    }

    @Override
    public String toString() {
        return "ItemGroup{" +
                "id='" + id + '\'' +
                ", item=" + item +
                ", amount=" + amount +
                ", pricePerItem=" + pricePerItem +
                ", shippingDate=" + shippingDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemGroup itemGroup = (ItemGroup) o;

        return Objects.equals(id, itemGroup.id) && Objects.equals(shippingDate, itemGroup.shippingDate) && amount == itemGroup.amount;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, item, amount);
    }
}
