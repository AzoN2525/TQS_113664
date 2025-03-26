package com.example.lab6_4;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.Arrays;
import java.util.List;

@RestController
public class CarController {

    @GetMapping("/api/cars")
    public List<Car> getCars() {
        return Arrays.asList(new Car(1, "Toyota"), new Car(2, "Honda"));
    }
}

class Car {
    private int id;
    private String name;

    public Car(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
