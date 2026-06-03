package com.erzan.task_api.dto.api_response.list_response;

import java.time.LocalDateTime;

public record ProductResponse(
        Long id,
        String name,
        String color,
        String expiry,
        String sellerName,
        LocalDateTime createdAt
) {}
