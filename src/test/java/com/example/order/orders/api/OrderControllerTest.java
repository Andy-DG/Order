package com.example.order.orders.api;

import com.example.order.item.domain.Item;
import com.example.order.item.domain.ItemRepository;
import com.example.order.item_group.api.dto.CreateItemGroupDTO;
import com.example.order.item_group.api.dto.ItemGroupDTO;
import com.example.order.orders.api.dto.CreateOrderDTO;
import com.example.order.orders.api.dto.OrderDTO;
import com.example.order.orders.service.OrderService;
import com.example.order.user.customer.domain.Customer;
import com.example.order.user.customer.domain.CustomerRepository;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;
import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class OrderControllerTest {
    public static final String TEST_ITEM_ID1 = "123e4567-e89b-12d3-a456-426614174002";
    public static final String TEST_ITEM_ID2 = "123e4567-e89b-12d3-a456-426614174003";
    public static final String TEST_CUSTOMER_ID = "123e4567-e89b-12d3-a456-426614174000";

    @LocalServerPort
    private int port;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderService orderService;

    @Test
    void createOrder_givenOrderToSave_thenOrderIsCreatedAndSavedCorrectly() {
        CreateItemGroupDTO createItemGroupDTO1 = new CreateItemGroupDTO()
                .setItemId(TEST_ITEM_ID1)
                .setAmount(5);
        CreateItemGroupDTO createItemGroupDTO2 = new CreateItemGroupDTO()
                .setItemId(TEST_ITEM_ID1)
                .setAmount(3);

        List<CreateItemGroupDTO> createItemGroupDTOList = Lists.newArrayList(createItemGroupDTO1, createItemGroupDTO2);

        // WHEN
        CreateOrderDTO createOrderDTO = new CreateOrderDTO()
                .setCustomerId(TEST_CUSTOMER_ID)
                .setCreateItemGroupDTOList(createItemGroupDTOList);

        OrderDTO orderDTO =
                RestAssured
                        .given()
                        .port(port)
                        .body(createOrderDTO)
                        .contentType(JSON)
                        .when()
                        .accept(JSON)
                        .post("/orders/add")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(OrderDTO.class);

        // THEN
        Assertions.assertThat(orderDTO.getId()).isNotBlank();
        Assertions.assertThat(orderDTO.getCustomerId()).isEqualTo(customerRepository.getReferenceById(TEST_CUSTOMER_ID).getId());

        Assertions.assertThat(orderDTO.getTotalPrice()).isEqualTo(1);

        List<ItemGroupDTO> expectedItemGroupDTOList = Lists.newArrayList(
                new ItemGroupDTO()
                        .setId(orderDTO.getItemGroupDTOList().get(0).getId())
                        .setItemId(TEST_ITEM_ID1)
                        .setAmount(5)
                        .setPricePerItem(0.125)
                        .setShippingDate(LocalDate.now().plusDays(1)),
                new ItemGroupDTO()
                        .setId(orderDTO.getItemGroupDTOList().get(1).getId())
                        .setItemId(TEST_ITEM_ID1)
                        .setAmount(3)
                        .setPricePerItem(0.125)
                        .setShippingDate(LocalDate.now().plusDays(1)));
        Assertions.assertThat(orderDTO.getItemGroupDTOList()).isEqualTo(expectedItemGroupDTOList);
    }

    @Test
    void createOrder_givenOrderWithMultipleItems_thenOrderPriceCalculatedCorrectly() {
        // GIVEN

        CreateItemGroupDTO createItemGroupDTO1 = new CreateItemGroupDTO()
                .setItemId(TEST_ITEM_ID1)
                .setAmount(5);
        CreateItemGroupDTO createItemGroupDTO2 = new CreateItemGroupDTO()
                .setItemId(TEST_ITEM_ID2)
                .setAmount(7);

        List<CreateItemGroupDTO> createItemGroupDTOList = Lists.newArrayList(createItemGroupDTO1, createItemGroupDTO2);

        // WHEN
        CreateOrderDTO createOrderDTO = new CreateOrderDTO()
                .setCustomerId(TEST_CUSTOMER_ID)
                .setCreateItemGroupDTOList(createItemGroupDTOList);

        OrderDTO orderDTO =
                RestAssured
                        .given()
                        .port(port)
                        .body(createOrderDTO)
                        .contentType(JSON)
                        .when()
                        .accept(JSON)
                        .post("/orders/add")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(OrderDTO.class);

        // THEN
        Assertions.assertThat(orderDTO.getTotalPrice()).isEqualTo(1.605);



    }

    @Test
    void createOrder_givenItemGroupToAddToOrderWithSufficientStock_thenAmountAvailableCorrectlyUpdatedInItemRepository() {
        // GIVEN
        CreateItemGroupDTO createItemGroupDTO = new CreateItemGroupDTO().setItemId(TEST_ITEM_ID1).setAmount(5);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO()
                .setCustomerId(TEST_CUSTOMER_ID)
                .setCreateItemGroupDTOList(Lists.newArrayList(createItemGroupDTO));

        RestAssured
                .given()
                .port(port)
                .body(createOrderDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/orders/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.CREATED.value());

        Assertions.assertThat(orderService.findAmountForItem(TEST_ITEM_ID1)).isEqualTo(5);
    }

    @Test
    void createOrder_givenIncorrectCustomerId_thenGetHttpStatusBadRequest() {
        // GIVEN
        Item item = new Item("Tomato", "A clean, round tomato with lots of vitamins", 0.125, 10);
        itemRepository.save(item);
        CreateItemGroupDTO createItemGroupDTO = new CreateItemGroupDTO().setItemId(item.getId()).setAmount(5);
        CreateOrderDTO createOrderDTO = new CreateOrderDTO()
                .setCustomerId("notAnId")
                .setCreateItemGroupDTOList(Lists.newArrayList(createItemGroupDTO));

        RestAssured
                .given()
                .port(port)
                .body(createOrderDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/orders/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }
}