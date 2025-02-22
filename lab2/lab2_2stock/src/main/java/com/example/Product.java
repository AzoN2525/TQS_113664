package com.example;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Product {

    private Integer id;
    private String image;
    private String description;
    private Double price;
    private String title;
    private String category;
    
    public Product(){};

    public Product(Integer id, String image, String description, Double price, String title, String category) {
        this.id = id;
        this.image = image;
        this.description = description;
        this.price = price;
        this.title = title;
        this.category = category;
    }

    public Integer getId() {
        return id;
    }

    public String getImage() {
        return image;
    }

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Product fromJson(String response) {

        if (response == null || response.isEmpty()) {
            return null; 
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(response, Product.class);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }



}