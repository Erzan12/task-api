package com.erzan.task_api.dto.api_response;

import java.time.LocalDateTime;

public record OrderResponse (
    Long id,
    String buyerName,
    String sellerName,
    String productName,
    LocalDateTime createdAt
) {}
