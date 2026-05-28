package com.erzan.task_api.service;

import com.erzan.task_api.dto.ProductRequest;
import com.erzan.task_api.entity.Product;
import com.erzan.task_api.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product getSingleProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product createProduct(ProductRequest request) {
        Product product = Product.builder()
                .name(request.getName())
                .category(request.getCategory())
                .color(request.getColor())
                .expiry(request.getExpiry())
                .build();

        return productRepository.save(product);
    }
}
