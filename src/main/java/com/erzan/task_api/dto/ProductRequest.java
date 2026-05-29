package com.erzan.task_api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProductRequest {

    @NotBlank
    private String name;

    private String category;
    private String color;
    private String expiry;
}
