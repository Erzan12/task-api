package com.erzan.task_api.controller;

import com.erzan.task_api.dto.ProductRequest;
import com.erzan.task_api.dto.api_response.ApiResponse;
import com.erzan.task_api.dto.api_response.list_response.ProductResponse;
import com.erzan.task_api.entity.Product;
import com.erzan.task_api.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(
            ProductService productService
    ) {
        this.productService = productService;
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getProducts() {

        List<ProductResponse> responses = productService.getProducts()
                .stream()
                .map(product -> new ProductResponse(
                        product.getId(),
                        product.getName(),
                        product.getColor(),
                        product.getExpiry(),
                        product.getSeller().getName(),
                        product.getCreatedAt()
                ))
                .toList();

        ApiResponse<List<ProductResponse>> response =
                new ApiResponse<>(
                        true,
                        "Products fetched successfully",
                        responses
                );

        return ResponseEntity.ok(response);
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
    public Product updateProduct(
            @PathVariable Long id,
            @RequestBody ProductRequest request
    ) {
        return  productService.updateProduct(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);

        return "Product has been deleted";
    }
}
