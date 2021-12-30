package com.digiboy.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Random;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {


    @Autowired
    private ProductService service;

    @Autowired
    private ProductRepository repository;

    @PostMapping
    public Product save(@Valid @RequestBody Product product) {
        return service.save(product);
    }

}
