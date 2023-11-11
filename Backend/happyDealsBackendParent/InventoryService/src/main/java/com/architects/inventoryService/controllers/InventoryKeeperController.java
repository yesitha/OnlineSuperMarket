package com.architects.inventoryService.controllers;


import com.architects.inventoryService.dto.request.RequestInventoryKeeperDto;
import com.architects.inventoryService.dto.response.ResponseInventoryKeeperDto;
import com.architects.inventoryService.services.InventoryKeeperServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventoryKeepers")
public class InventoryKeeperController {
    private  final InventoryKeeperServiceImpl inventoryKeeperService;
    @Autowired
    public InventoryKeeperController(InventoryKeeperServiceImpl inventoryKeeperService){
        this.inventoryKeeperService=inventoryKeeperService;
    }


    @PostMapping
    public String createInventoryKeeper(@RequestBody RequestInventoryKeeperDto inventoryKeeper){
        inventoryKeeperService.saveInventoryKeeper(inventoryKeeper);
        return "Inventory Keeper created successfully!";
    }

    // Update InventoryKeeper by inventoryKeeperId
    @PutMapping("/{inventoryKeeperId}")
    public String updateInventoryKeeper(@PathVariable Long inventoryKeeperId, @RequestBody RequestInventoryKeeperDto updatedInventoryKeeper){
        inventoryKeeperService.updateInventoryKeeper(inventoryKeeperId, updatedInventoryKeeper);
        return "Inventory Keeper updated successfully!";
    }

    // Delete InventoryKeeper by inventoryKeeperId
    @DeleteMapping("/{inventoryKeeperId}")
    public String deleteInventoryKeeper(@PathVariable Long inventoryKeeperId){
        inventoryKeeperService.deleteInventoryKeeper(inventoryKeeperId);
        return "Inventory Keeper deleted successfully!";
    }

    // Retrieve all InventoryKeepers
    @GetMapping
    public List<ResponseInventoryKeeperDto> getAllInventoryKeepers(){
        return  inventoryKeeperService.getAllInventoryKeepers();
    }

    // Retrieve InventoryKeeper by inventoryKeeperId
    @GetMapping("/{InventoryKeeperId}")
    public ResponseInventoryKeeperDto getAllInventoryKeepersById(@PathVariable Long inventoryKeeperId){
        return inventoryKeeperService.getInventoryKeeperById(inventoryKeeperId);
    }
}
