package com.rodrigoschonardt.orderservice.dto;

import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record AddOrderData
(
    @NotEmpty
    List<AddOrderItemData> orderItems
) {}
