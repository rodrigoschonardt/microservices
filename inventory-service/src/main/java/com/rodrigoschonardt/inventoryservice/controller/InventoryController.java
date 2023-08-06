package com.rodrigoschonardt.inventoryservice.controller;

import com.rodrigoschonardt.inventoryservice.dto.InventoryItemStatusData;
import com.rodrigoschonardt.inventoryservice.model.InventoryItem;
import com.rodrigoschonardt.inventoryservice.repository.InventoryRepository;
import com.rodrigoschonardt.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping( "/api/inventory-items" )
public class InventoryController
{
    @Autowired
    private InventoryService service;

    @GetMapping( "/{sku-code}" )
    public ResponseEntity getStatus( @PathVariable( "sku-code" ) String skuCode )
    {
        InventoryItemStatusData inventoryStatusData = service.getInventoryItemStatus( skuCode );

        return ResponseEntity.ok( inventoryStatusData );
    }
}
