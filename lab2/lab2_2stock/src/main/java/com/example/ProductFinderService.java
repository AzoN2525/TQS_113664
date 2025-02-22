package com.example;

import java.util.List;
import java.util.ArrayList;

public class ProductFinderService {

    private String API_PRODUCTS = "https://fakestoreapi.com/products";
    private ISimpleHttpClient httpClient;
    
    public ProductFinderService(ISimpleHttpClient httpClient) {
        this.httpClient = httpClient;
    }


    public List<Product> findProductDetails(Integer ID) {

        if(ID == null || ID < 0){
            return new ArrayList<>();
        }

        String response = httpClient.doHttpGet(API_PRODUCTS + "/" + ID);

        if(response == null || response.isEmpty()){
            return new ArrayList<>();
        }
        
        Product product = new Product();
        product = product.fromJson(response);
        List<Product> products = new ArrayList<Product>();
        products.add(product);
        return products;

    }


}
