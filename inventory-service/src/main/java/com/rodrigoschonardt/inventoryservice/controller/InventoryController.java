package com.rodrigoschonardt.inventoryservice.controller;

import com.rodrigoschonardt.inventoryservice.dto.InventoryStatusData;
import com.rodrigoschonardt.inventoryservice.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping( "/api/inventory-items" )
public class InventoryController
{
    @Autowired
    private InventoryService service;

    @GetMapping
    public ResponseEntity getStatus( @RequestParam( "sku-code" ) List<String> skuCode )
    {
        List<InventoryStatusData> inventoryStatusData = service.getInventoryItemStatus( skuCode );

        return ResponseEntity.ok( inventoryStatusData );
    }
}
