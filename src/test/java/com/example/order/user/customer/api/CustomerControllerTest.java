package com.example.order.user.customer.api;

import com.example.order.user.customer.api.dto.CreateCustomerDTO;
import com.example.order.user.customer.api.dto.CustomerDTO;
import com.example.order.user.customer.domain.Customer;
import com.example.order.user.customer.domain.CustomerRepository;
import com.example.order.user.customer.service.CustomerMapper;
import com.example.order.user.footing.api.dto.AddressDTO;
import com.example.order.user.footing.api.dto.NameDTO;
import com.example.order.user.footing.domain.Address;
import com.example.order.user.footing.domain.Name;
import io.restassured.RestAssured;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;

import java.util.List;

import static io.restassured.http.ContentType.JSON;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureTestDatabase
class CustomerControllerTest {

    private static final String TEST_CUSTOMER_ID = "123e4567-e89b-12d3-a456-426614174000";

    @LocalServerPort
    private int port;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerMapper customerMapper;

    @Nested
    @DisplayName("OrderCreationTests")
    class CreateOrderIntegrationTest {


        @Test
        void createCustomer_givenACustomerToCreate_thenTheNewlyCreatedCustomerIsSavedAndReturned() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.mcclane@diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            CustomerDTO customerDTO =
                    RestAssured
                            .given()
                            .port(port)
                            .body(createCustomerDTO)
                            .contentType(JSON)
                            .when()
                            .accept(JSON)
                            .post("/customers/add")
                            .then()
                            .assertThat()
                            .statusCode(HttpStatus.CREATED.value())
                            .extract()
                            .as(CustomerDTO.class);

            Assertions.assertThat(customerDTO.getId()).isNotBlank();
            Assertions.assertThat(customerDTO.getNameDTO()).isEqualTo(createCustomerDTO.getName());
            Assertions.assertThat(customerDTO.getEmail()).isEqualTo(createCustomerDTO.getEmail());
            Assertions.assertThat(customerDTO.getAddressDTO()).isEqualTo(createCustomerDTO.getAddressDTO());
            Assertions.assertThat(customerDTO.getPhoneNumber()).isEqualTo(createCustomerDTO.getPhoneNumber());
        }

        @Test
        void createCustomer_givenACustomerWithoutFirstName_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO(null, "McClane"))
                    .setEmail("john.mcclane@diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithoutLastName_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", null))
                    .setEmail("john.mcclane@diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithIncorrectEmail_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithExistingEmail_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("John.McTest@diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithoutAddress_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.diehard.com")
                    .setAddressDTO(null)
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithoutPhoneNumber_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john@diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, "Brussel"))
                    .setPhoneNumber(null);

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithNoStreetName_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.diehard.com")
                    .setAddressDTO(new AddressDTO("", 1, 1000, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithNoPostalCode_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, -5, "Brussel"))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }

        @Test
        void createCustomer_givenACustomerWithNoCity_thenGetHttpStatusBadRequest() {
            CreateCustomerDTO createCustomerDTO = new CreateCustomerDTO()
                    .setName(new NameDTO("John", "McClane"))
                    .setEmail("john.diehard.com")
                    .setAddressDTO(new AddressDTO("Hero Street", 1, 1000, ""))
                    .setPhoneNumber("897 654 875");

            RestAssured
                    .given()
                    .port(port)
                    .body(createCustomerDTO)
                    .contentType(JSON)
                    .when()
                    .accept(JSON)
                    .post("/customers/add")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }

    @Nested
    @DisplayName("GetOrderTests")
    class GetOrderIntegrationTest {
        @Test
        void getAllCustomers_customersAreShownCorrectly() {

            CustomerDTO[] result = RestAssured
                    .given()
                    .port(port)
                    .when()
                    .accept(JSON)
                    .get("/customers")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .as(CustomerDTO[].class);

            List<CustomerDTO> expectedList = customerMapper.toDTO(customerRepository.findAll());
            Assertions.assertThat(List.of(result)).hasSameElementsAs(expectedList);
        }

        @Test
        void getSingleCustomer_customerIsShownCorrectly() {
            Customer actualCustomer = customerRepository.getReferenceById(TEST_CUSTOMER_ID);
            Customer expectedCustomer = new Customer(new Name("John", "McClane"), "John.McTest@diehard.com", new Address("Hero Street", 1, 1000, "Brussel"), "165 874 894");

            CustomerDTO result = RestAssured
                    .given()
                    .port(port)
                    .when()
                    .accept(JSON)
                    .get("/customers/" + actualCustomer.getId())
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.OK.value())
                    .extract()
                    .as(CustomerDTO.class);

            Assertions.assertThat(result).isEqualTo(customerMapper.toDTO(expectedCustomer));
        }

        @Test
        void getSingleCustomerWithNonExistingId_ThenGetHttpStatusBadRequest() {
            RestAssured
                    .given()
                    .port(port)
                    .when()
                    .accept(JSON)
                    .get("/customers/trolled")
                    .then()
                    .assertThat()
                    .statusCode(HttpStatus.BAD_REQUEST.value());
        }
    }
}