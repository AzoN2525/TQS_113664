package com.example;

public class Customer {
    private long id;
    private String name;

    // Construtor
    public Customer(long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getters
    public long id() {
        return id;
    }

    public String name() {
        return name;
    }


}
