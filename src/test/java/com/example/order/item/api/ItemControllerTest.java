package com.example.order.item.api;

import com.example.order.item.api.dto.CreateItemDTO;
import com.example.order.item.api.dto.ItemDTO;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class ItemControllerTest {

    @LocalServerPort
    private int port;

    @Test
    void createItem_givenItemToCreate_thenTheNewlyCreatedItemIsSavedAndReturned() {
        CreateItemDTO createItemDTO = new CreateItemDTO(
                "I'm a test Carrot",
                "This is a carrot",
                0.5,
                10);



        ItemDTO itemDTO =
                RestAssured
                        .given()
                        .port(port)
                        .body(createItemDTO)
                        .contentType(JSON)
                        .when()
                        .accept(JSON)
                        .post("/items/add")
                        .then()
                        .assertThat()
                        .statusCode(HttpStatus.CREATED.value())
                        .extract()
                        .as(ItemDTO.class);

        Assertions.assertThat(itemDTO.getId()).isNotBlank();
        Assertions.assertThat(itemDTO.getName()).isEqualTo(createItemDTO.getName());
        Assertions.assertThat(itemDTO.getDescription()).isEqualTo(createItemDTO.getDescription());
        Assertions.assertThat(itemDTO.getPrice()).isEqualTo(createItemDTO.getPrice());
        Assertions.assertThat(itemDTO.getStock()).isEqualTo(createItemDTO.getStock());
    }

    @Test
    void createItem_givenItemWithoutName_thenGetHttpStatusBadRequest() {
        CreateItemDTO createItemDTO = new CreateItemDTO(
                null,
                "This is a carrot",
                0.5,
                10);


        RestAssured
                .given()
                .port(port)
                .body(createItemDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createItem_givenItemWithExistingName_thenGetHttpStatusBadRequest() {

        CreateItemDTO createItemDTO = new CreateItemDTO(
                "Carrot",
                "This is a potato",
                0.5,
                10);


        RestAssured
                .given()
                .port(port)
                .body(createItemDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createItem_givenItemWithoutDescription_thenGetHttpStatusBadRequest() {
        CreateItemDTO createItemDTO = new CreateItemDTO(
                "Carrot",
                null,
                0.5,
                10);

        RestAssured
                .given()
                .port(port)
                .body(createItemDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createItem_givenItemWithNegativePrice_thenGetHttpStatusBadRequest() {
        CreateItemDTO createItemDTO = new CreateItemDTO(
                "Carrot",
                "This is a carrot",
                -50.5,
                10);

        RestAssured
                .given()
                .port(port)
                .body(createItemDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    void createItem_givenItemWithNegativeAmountAvailable_thenGetHttpStatusBadRequest() {
        CreateItemDTO createItemDTO = new CreateItemDTO(
                "Carrot",
                "This is a carrot",
                0.5,
                -8);

        RestAssured
                .given()
                .port(port)
                .body(createItemDTO)
                .contentType(JSON)
                .when()
                .accept(JSON)
                .post("/items/add")
                .then()
                .assertThat()
                .statusCode(HttpStatus.BAD_REQUEST.value());
    }


}