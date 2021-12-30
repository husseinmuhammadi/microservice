package com.digiboy.product;

import com.digiboy.product.api.ProductService;
import com.digiboy.product.dto.ProductDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @PostMapping
    public ProductDTO save(@Valid @RequestBody ProductDTO product) {
        return service.save(product);
    }

}
