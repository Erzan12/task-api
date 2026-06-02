package com.erzan.task_api.controller;

import com.erzan.task_api.dto.BuyProductRequest;
import com.erzan.task_api.dto.api_response.ApiResponse;
import com.erzan.task_api.entity.Order;
import com.erzan.task_api.entity.User;
import com.erzan.task_api.repository.OrderRepository;
import com.erzan.task_api.service.OrderService;
import com.erzan.task_api.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(
            OrderService orderService
    ) {
        this.orderService = orderService;
    }

    @PostMapping("/place-order")
    public ResponseEntity<ApiResponse<Order>> placeOrder(@RequestBody BuyProductRequest request) {

        Order order = orderService.placeOrder(request.getUserId(), request.getProductId());

        ApiResponse<Order> response =
                new ApiResponse<>(
                        true,
                        "Order has been placed",
                        order
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public List<Order> getOrders() {
        return orderService.getOrders();
    }
}
