package com.example.lab6_4;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.equalTo;

@WebMvcTest(CarController.class)
public class CarControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc); // Configura o RestAssuredMockMvc para usar o MockMvc
    }

    @Test
    void testGetCars() {
        RestAssuredMockMvc.given()
            .when().get("/api/cars")
            .then()
            .statusCode(200)
            .body("[0].id", equalTo(1))
            .body("[0].name", equalTo("Toyota"))
            .body("[1].id", equalTo(2))
            .body("[1].name", equalTo("Honda"));
    }
}
