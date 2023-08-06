package com.rodrigoschonardt.productservice.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AddProductData
(
    @NotBlank
    String name,
    String description,
    @NotNull
    BigDecimal price
) {}
