package com.rodrigoschonardt.inventoryservice.repository;

import com.rodrigoschonardt.inventoryservice.model.InventoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository
    extends
        JpaRepository<InventoryItem, Long>
{
    Optional<InventoryItem> findBySkuCode(String skuCode );
}
