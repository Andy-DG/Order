package com.example.order.orders;

import com.example.order.item.*;
import com.example.order.orders.item_group.ItemGroup;
import com.example.order.user.customer.Customer;
import com.example.order.user.customer.CustomerMapper;
import com.example.order.user.customer.CustomerRepository;
import com.example.order.user.customer.CustomerService;
import com.example.order.user.customer.details.Address;
import com.example.order.user.customer.details.Name;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    UUID id = UUID.randomUUID();
    OrderRepository orderRepository;
    OrderMapper orderMapper;
    CustomerMapper customerMapper;
    CustomerRepository customerRepository;
    CustomerService customerService;
    ItemService itemService;
    ItemMapper itemMapper;
    ItemRepository itemRepository;
    OrderService orderService;

    @BeforeEach
    void setUp() {
        orderRepository = new OrderRepository();
        orderMapper = new OrderMapper();
        customerMapper = new CustomerMapper();
        customerRepository = new CustomerRepository();
        customerService = new CustomerService(customerMapper, customerRepository);
        itemMapper = new ItemMapper();
        itemRepository = new ItemRepository();
        itemService = new ItemService(itemRepository, itemMapper);
        orderService = new OrderService(customerService, itemService, orderMapper, orderRepository);
    }

    @Test
    @DisplayName("When adding a null order, an error is thrown")
    void whenAddingANullOrderAnErrorIsThrown() {
        assertThrows(IllegalArgumentException.class, () -> orderService.addOrder(null));
    }

    @Test
    @DisplayName("When adding an order, the order is added to the order repository")
    void whenAddingAnOrderTheOrderIsAddedToTheOrderRepository() {
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        itemRepository.addItem(item);

        ItemGroup itemGroup = new ItemGroup(id, item, 3);

        List<ItemGroup> itemGroups = new ArrayList<>();
        itemGroups.add(itemGroup);

        Customer customer = new Customer(id, new Name("Morty", "Smith"),
                "morty@smithy.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        Order order = new Order(id, itemGroups, customer);

        AddOrderDTO addOrderDTO = orderMapper.toAddOrderDTO(order);

        orderService.addOrder(addOrderDTO);

        assertEquals(order, orderRepository.getOrderById(id));
    }

    @Test
    @DisplayName("When Adding an order, the order goes into the customer order list")
    void whenAddingAnOrderTheOrderGoesIntoTheCustomerOrderList() {
        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        itemRepository.addItem(item);

        ItemGroup itemGroup = new ItemGroup(id, item, 3);

        List<ItemGroup> itemGroups = new ArrayList<>();
        itemGroups.add(itemGroup);

        Customer customer = new Customer(id, new Name("Morty", "Smith"),
                "morty@smithy.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        Order order = new Order(id, itemGroups, customer);

        AddOrderDTO addOrderDTO = orderMapper.toAddOrderDTO(order);

        orderService.addOrder(addOrderDTO);

        assertTrue(customer.getOrderList().contains(order));
    }

    @Test
    @DisplayName("When adding an order, the stock of the item gets updated")
    void whenAddingAnOrderTheStockOfTheItemGetsUpdated() {

        Item item = new Item(id,"Shirt", "A Shirt", 5.2, 5);
        itemRepository.addItem(item);

        ItemGroup itemGroup = new ItemGroup(id, item, 3);

        List<ItemGroup> itemGroups = new ArrayList<>();
        itemGroups.add(itemGroup);

        Customer customer = new Customer(id, new Name("Morty", "Smith"),
                "morty@smithy.com",
                new Address("Morty-street", 11, 6910, "Seattle"),
                "+111 (202) 555-0125");

        Order order = new Order(id, itemGroups, customer);

        AddOrderDTO addOrderDTO = orderMapper.toAddOrderDTO(order);
        orderService.addOrder(addOrderDTO);



        assertEquals(2, itemRepository.getItemMap().get(id).getStock());
    }

}