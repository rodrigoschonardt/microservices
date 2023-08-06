package com.rodrigoschonardt.inventoryservice.service;

import com.rodrigoschonardt.inventoryservice.dto.InventoryItemStatusData;
import com.rodrigoschonardt.inventoryservice.model.InventoryItem;
import com.rodrigoschonardt.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class InventoryService
{
    @Autowired
    private InventoryRepository repository;

    public InventoryItemStatusData getInventoryItemStatus( String skuCode )
    {
        Optional<InventoryItem> inventoryItem = repository.findBySkuCode( skuCode );

        if ( inventoryItem.isEmpty()
             || inventoryItem.get().getQuantity() == 0 ) return new InventoryItemStatusData( InventoryItem.Status.UNAVAILABLE, null );

        return new InventoryItemStatusData( InventoryItem.Status.AVAILABLE, inventoryItem.get().getQuantity() );
    }
}
