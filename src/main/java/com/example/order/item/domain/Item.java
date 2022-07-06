package com.example.order.item.domain;

import com.example.order.util.Validator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "ITEM")
public class Item {
    @Transient
    private final Logger itemLogger = LoggerFactory.getLogger(Item.class);

    @Id
    private String id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "PRICE")
    private double price;

    @Column(name = "STOCK")
    private int stock;

    public Item() {
    }

    public Item(String name, String description, double price, int stock) {
        this.id = UUID.randomUUID().toString();
        this.name = validateName(name);
        this.description = validateDescription(description);
        this.price = validatePrice(price);
        this.stock = validateStock(stock);
        itemLogger.info("New item created.");
    }

    public void updateStock(int newStock) {
        stock = newStock;
    }

    private String validateName(String name) {
        if (Validator.isEmptyOrNull(name)) {
            itemLogger.error("An item must have a name.");
            throw new IllegalArgumentException("An item must have a name.");
//            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "An item must have a name.");
        }
        return name;
    }

    private String validateDescription(String description) {
        if (Validator.isEmptyOrNull(description)) {
            itemLogger.error("An item must have a description.");
            throw new IllegalArgumentException("An item must have a description.");
        }
        return description;
    }

    private double validatePrice(double price) {
        if (Validator.isNegative(price)) {
            itemLogger.error("Price cannot be negative.");
            throw new IllegalArgumentException("Price cannot be negative.");
        }
        return price;
    }

    private int validateStock(int stock) {
        if (Validator.isNegative(stock)) {
            itemLogger.error("Stock cannot be negative.");
            throw new IllegalArgumentException("Stock cannot be negative.");
        }
        return stock;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", stock=" + stock +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return Objects.equals(id, item.id) && Objects.equals(name, item.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
