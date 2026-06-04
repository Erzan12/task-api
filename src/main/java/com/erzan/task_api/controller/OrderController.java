package com.erzan.task_api.controller;

import com.erzan.task_api.dto.PlaceOrderRequest;
import com.erzan.task_api.dto.api_response.ApiResponse;
import com.erzan.task_api.dto.api_response.list_response.OrderResponse;
import com.erzan.task_api.entity.Order;
import com.erzan.task_api.service.OrderService;
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

    @PostMapping
    public ResponseEntity<ApiResponse<OrderResponse>> placeOrder(@RequestBody PlaceOrderRequest request) {

        Order order = orderService.placeOrder(
                request.getBuyerId(),
                request.getProductId()
        );

        OrderResponse orderResponse = new OrderResponse(
                order.getId(),
                order.getBuyer().getName(),
                order.getSeller().getName(),
                order.getProduct().getName(),
                order.getCreatedAt()
        );

        ApiResponse<OrderResponse> response =
                new ApiResponse<>(
                        true,
                        "Order has been placed",
                        orderResponse
                );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<OrderResponse>>> getOrders() {

        List<OrderResponse> responses = orderService.getOrders()
                .stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getBuyer().getName(),
                        order.getSeller().getName(),
                        order.getProduct().getName(),
                        order.getCreatedAt()
                ))
                .toList();

        ApiResponse<List<OrderResponse>> response =
                new ApiResponse<>(
                        true,
                        "Orders fetched successfully",
                        responses
                );

        return ResponseEntity.ok(response);
    }
}
