package com.example;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.testcontainers.containers.PostgreSQLContainer;
import io.restassured.module.mockmvc.RestAssuredMockMvc;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CarControllerIntegrationTest {

    private static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @LocalServerPort
    private int port;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @BeforeAll
    public static void setUp() {
        // Inicia o container PostgreSQL
        postgres.start();
    }

    @AfterAll
    public static void tearDown() {
        postgres.stop();
    }

    @Test
    void testCarEndpoint() {
        // Verifica que a tabela foi criada corretamente
        String query = "SELECT COUNT(*) FROM cars";
        Integer count = jdbcTemplate.queryForObject(query, Integer.class);
        System.out.println("Number of cars: " + count);

        // Verifica o endpoint de carros
        given().port(port)
            .when().get("/api/cars")
            .then().statusCode(200)
            .body("[0].make", equalTo("Toyota"))
            .body("[0].model", equalTo("Corolla"))
            .body("[1].make", equalTo("Honda"))
            .body("[1].model", equalTo("Civic"));
    }
}
