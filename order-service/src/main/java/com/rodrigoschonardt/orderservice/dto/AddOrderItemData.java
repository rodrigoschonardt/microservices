package com.rodrigoschonardt.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddOrderItemData
(
    @NotBlank
    String skuCode,
    @NotNull
    BigDecimal price,
    @NotNull
    Integer quantity
)
{}
