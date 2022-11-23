package com.digiboy.product.service;

import com.digiboy.product.api.ProductService;
import com.digiboy.product.dto.ProductDTO;
import com.digiboy.product.repository.ProductRepository;
import com.digiboy.product.to.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public ProductDTO save(ProductDTO product) {
        // Convert DTO to TO
        Product product1 = new Product();
        product1.setBarCode(product.getBarCode());
        product1.setName(product.getName());

        product1.setInternalId(UUID.randomUUID().toString());
        Product product2 = repository.save(product1);

        ProductDTO productDTO2 = new ProductDTO();
        productDTO2.setId(product2.getId());
        productDTO2.setName(product2.getName());
        productDTO2.setBarCode(product2.getBarCode());

        return productDTO2;
    }

}
