package com.redhat.coolstore.productcataloglab1.service;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
public class ProductCatalogService {

    @Value("${coolstore.message:Hello World!}")
    private String message;

    @GetMapping("/products")
    public String sayHello() {
        return message;
    }
}