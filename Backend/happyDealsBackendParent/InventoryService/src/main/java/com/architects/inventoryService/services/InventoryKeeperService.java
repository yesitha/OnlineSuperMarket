package com.architects.inventoryService.services;


import com.architects.inventoryService.dto.request.RequestInventoryKeeperDto;
import com.architects.inventoryService.dto.response.ResponseInventoryKeeperDto;

import java.util.List;

public interface InventoryKeeperService {
    public void saveInventoryKeeper(RequestInventoryKeeperDto inventoryKeeper);

    // Update Product by productId
    public void updateInventoryKeeper(Long inventoryKeeperId, RequestInventoryKeeperDto updatedInventoryKeeper);


    // Delete Inventory Keeper by Inventory Keeper ID
    public void deleteInventoryKeeper(Long inventoryKeeperId);

    // Retrieve all Inventory Keepers
    public List<ResponseInventoryKeeperDto> getAllInventoryKeepers();

    // Retrieve Inventory Keeper by inventoryKeeperId
    public ResponseInventoryKeeperDto getInventoryKeeperById(Long inventoryKeeperId);

    // Retrieve Inventory Keeper by inventoryKeeperName
    public ResponseInventoryKeeperDto getInventoryKeeperByName(String inventoryKeeperName);
}
