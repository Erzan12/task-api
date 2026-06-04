package com.erzan.task_api.service;

import com.erzan.task_api.entity.Order;
import com.erzan.task_api.entity.Product;
import com.erzan.task_api.entity.User;
import com.erzan.task_api.repository.OrderRepository;
import com.erzan.task_api.repository.ProductRepository;
import com.erzan.task_api.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;

    public OrderService( UserRepository userRepository, ProductRepository productRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Transactional
    public Order placeOrder(Long buyerId, Long productId) {
        User buyer = userRepository.findById(buyerId)
                .orElseThrow(() -> new RuntimeException("Buyer not found"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        User seller = product.getSeller();

        Order order = Order.builder()
                .buyer(buyer)
                .seller(seller)
                .product(product)
                .build();

        return orderRepository.save(order);
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
