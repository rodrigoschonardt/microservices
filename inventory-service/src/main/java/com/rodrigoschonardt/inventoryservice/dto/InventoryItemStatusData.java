package com.rodrigoschonardt.inventoryservice.dto;

import com.rodrigoschonardt.inventoryservice.model.InventoryItem;

public record InventoryItemStatusData
(
    InventoryItem.Status status,
    Integer quantity
) {}
