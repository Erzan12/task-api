package com.erzan.task_api.controller;

import com.erzan.task_api.dto.ProductRequest;
import com.erzan.task_api.entity.Product;
import com.erzan.task_api.service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @GetMapping("/{id}")
    public Product getSingleProduct(@PathVariable Long id) {
        return productService.getSingleProduct(id);
    }

    @PostMapping
    public Product createProduct(@RequestBody ProductRequest request) {
        return productService.createProduct(request);
    }

    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable Long id, @RequestBody ProductRequest request) {
        return  productService.updateProduct(id, request);
    }
}
