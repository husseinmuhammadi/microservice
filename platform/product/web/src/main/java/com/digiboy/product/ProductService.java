package com.digiboy.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repository;

    public Product save(Product product) {
        product.setInternalId(UUID.randomUUID().toString());
        return repository.save(product);
    }

}
