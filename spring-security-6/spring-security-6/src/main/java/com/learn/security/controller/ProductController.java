package com.learn.security.controller;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    record Product(Integer productId, String productName, double price) {}

    private final List<Product> products = new ArrayList<>(List.of(
            new Product(1, "iPhone", 999.0),
            new Product(2, "Mac Pro", 2099.0)
    ));

    //1.Fetching List of Product
    @GetMapping
    public List<Product> getProducts() {
        return products;
    }

    //2.Create Product
    @PostMapping
    public Product saveProduct(@RequestBody Product product) {
        products.add(product);
        return product;
    }
}
