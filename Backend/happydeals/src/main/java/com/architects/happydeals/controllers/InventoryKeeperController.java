package com.architects.happydeals.controllers;

import com.architects.happydeals.entity.InventoryKeeper;
import com.architects.happydeals.services.InventoryKeeperService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.architects.happydeals.entity.InventoryKeeper;
import com.architects.happydeals.services.InventoryKeeperService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/inventoryKeepers")
public class InventoryKeeperController {

    @Autowired
    private InventoryKeeperService inventoryKeeperService;

    @PostMapping
    public String createInventoryKeeper(@RequestBody InventoryKeeper inventoryKeeper){
        inventoryKeeperService.saveInventoryKeeper(inventoryKeeper);
        return "Inventory Keeper created successfully!";
    }

    // Update InventoryKeeper by inventoryKeeperId
    @PutMapping("/{inventoryKeeperId}")
    public String updateInventoryKeeper(@PathVariable Long inventoryKeeperId, @RequestBody InventoryKeeper updatedInventoryKeeper){
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
    public List<InventoryKeeper> getAllInventoryKeepers(){
        return  inventoryKeeperService.getAllInventoryKeepers();
    }

    // Retrieve InventoryKeeper by inventoryKeeperId
    @GetMapping("/{InventoryKeeperId}")
    public InventoryKeeper getAllInventoryKeepersById(@PathVariable Long inventoryKeeperId){
        return inventoryKeeperService.getInventoryKeeperById(inventoryKeeperId);
    }


}
