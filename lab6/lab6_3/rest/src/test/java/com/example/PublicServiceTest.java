package com.example;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.Matchers.hasItems;


import org.junit.jupiter.api.Test;

public class PublicServiceTest {

    // Testa a listagem dos ToDos
    @Test
    void testGetTodos() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url)
            .then().assertThat()
            .statusCode(200)  // Verifica se o status da resposta é 200
            .body("size()", equalTo(200));  // Verifica que a quantidade de "todos" é 200
    }

    // Testa a consulta de um ToDo específico (ID 4)
    @Test
    void testGetTodoById() {
        String url = "https://jsonplaceholder.typicode.com/todos/4";
        given().when().get(url)
            .then().assertThat()
            .statusCode(200)  // Verifica se o status da resposta é 200
            .body("title", equalTo("et porro tempora"));  // Verifica o título do ToDo
    }

    // Verifica que os IDs 198 e 199 estão presentes na resposta
    @Test
    void testTodosContainsIds198And199() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url)
            .then().assertThat()
            .statusCode(200)  // Verifica se o status da resposta é 200
            .body("id", hasItems(198, 199));  // Verifica se os IDs 198 e 199 estão na lista
    }
    

    // Testa se a consulta dos todos leva menos de 2 segundos
    @Test
    void testGetTodosResponseTime() {
        String url = "https://jsonplaceholder.typicode.com/todos";
        given().when().get(url)
            .then().assertThat()
            .statusCode(200)  // Verifica se o status da resposta é 200
            .time(lessThan(2000L));  // Verifica se a resposta é recebida em menos de 2 segundos
    }
}
