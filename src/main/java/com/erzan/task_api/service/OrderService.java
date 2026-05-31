package com.erzan.task_api.service;

import com.erzan.task_api.repository.OrderRepository;
import com.erzan.task_api.repository.ProductRepository;
import com.erzan.task_api.repository.UserRepository;
import org.springframework.stereotype.Service;

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

    
}
