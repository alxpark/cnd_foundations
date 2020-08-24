package com.redhat.coolstore.productcatalog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductCatalogService {

    @Value("${coolstore.message:Hello World!}")
    private String message;
    
    @Autowired
	private ProductRepository productRepository;
    
    @GetMapping("/product")
    public String sayHello() {
        return message;
    }	
    
    @GetMapping("/products")
    public List<Product> list() {
        return productRepository.findAll();
    }
}
