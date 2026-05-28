package com.erzan.task_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductRequest {

    @NotBlank
    private String name;

    private String category;
    private String color;
    private String expiry;
}
