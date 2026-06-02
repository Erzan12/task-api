package com.erzan.task_api.dto;

import lombok.Data;

@Data
public class BuyProductRequest {

    private Long buyerId;
    private Long productId;
}
