package com.rodrigoschonardt.inventoryservice.service;

import com.rodrigoschonardt.inventoryservice.dto.InventoryStatusData;
import com.rodrigoschonardt.inventoryservice.model.InventoryItem;
import com.rodrigoschonardt.inventoryservice.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class InventoryService
{
    @Autowired
    private InventoryRepository repository;

    public List<InventoryStatusData>  getInventoryItemStatus( List<String> codes )
    {
        List<InventoryStatusData> inventoryStatusDataList = new ArrayList<>();

        for ( String skuCode : codes )
        {
            Optional<InventoryItem> inventoryItem = repository.findBySkuCode( skuCode );

            if ( inventoryItem.isEmpty()
                    || inventoryItem.get().getQuantity() == 0 )
            {
                inventoryStatusDataList.add( new InventoryStatusData( skuCode, 0 ) );
                continue;
            }

            inventoryStatusDataList.add( new InventoryStatusData( skuCode, inventoryItem.get().getQuantity() ) );
        }

        return inventoryStatusDataList;
    }
}
