package com.architects.happydeals.services;

import com.architects.happydeals.entity.InventoryKeeper;
import com.architects.happydeals.Repositories.InventoryKeeperRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryKeeperService {

    @Autowired
    private InventoryKeeperRepository inventoryKeeperRepository;

    public void saveInventoryKeeper(InventoryKeeper inventoryKeeper) {
      inventoryKeeperRepository.save(inventoryKeeper);
    }

    // Update Product by productId
    public void updateInventoryKeeper(Long inventoryKeeperId, InventoryKeeper updatedInventoryKeeper) {
        InventoryKeeper existingInventoryKeeper = inventoryKeeperRepository.findById(inventoryKeeperId)
                .orElseThrow(() -> new RuntimeException("Inventory Keeper not found with id " + inventoryKeeperId));

        existingInventoryKeeper.setInventoryKeeperName(updatedInventoryKeeper.getInventoryKeeperName());
        existingInventoryKeeper.setInventoryKeeperPhoneNumber(updatedInventoryKeeper.getInventoryKeeperPhoneNumber());
        existingInventoryKeeper.setInventoryKeeperEmail(updatedInventoryKeeper.getInventoryKeeperEmail());
        existingInventoryKeeper.setInventoryKeeperPassword(updatedInventoryKeeper.getInventoryKeeperPassword());


        inventoryKeeperRepository.save(existingInventoryKeeper);
    }

    // Delete Inventory Keeper by Inventory Keeper ID
    public void deleteInventoryKeeper(Long inventoryKeeperId) {
        inventoryKeeperRepository.deleteById(inventoryKeeperId);
    }

    // Retrieve all Inventory Keepers
    public List<InventoryKeeper> getAllInventoryKeepers() {
        return inventoryKeeperRepository.findAll();
    }

    // Retrieve Inventory Keeper by inventoryKeeperId
    public InventoryKeeper getInventoryKeeperById(Long inventoryKeeperId) {
        return inventoryKeeperRepository.findById(inventoryKeeperId)
                .orElseThrow(() -> new RuntimeException("Inventory Keeper not found with id " + inventoryKeeperId));
    }
}