package com.erzan.task_api.dto.api_response.list_response.user_list_response;

import java.time.LocalDateTime;

public record UserOrderResponse(
        Long id,
        String sellerName,
        String productName,
        LocalDateTime createdAt
) {}
