package com.erzan.task_api.dto.api_response.list_response.user_list_response;

import com.erzan.task_api.dto.api_response.list_response.OrderResponse;
import com.erzan.task_api.dto.api_response.list_response.ProductResponse;

import java.time.LocalDateTime;
import java.util.List;

public record UserResponse(
        Long id,
        String name,
        String email,
        Integer age,
        String address,
        List<ProductResponse>productsForSale,
        List<UserOrderResponse>purchases,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {}
