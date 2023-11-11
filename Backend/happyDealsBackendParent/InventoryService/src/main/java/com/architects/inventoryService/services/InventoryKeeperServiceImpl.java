package com.architects.inventoryService.services;

import com.architects.inventoryService.Repositories.InventoryKeeperRepository;
import com.architects.inventoryService.dto.request.RequestInventoryKeeperDto;
import com.architects.inventoryService.dto.response.ResponseInventoryKeeperDto;
import com.architects.inventoryService.entity.InventoryKeeper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;


import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class InventoryKeeperServiceImpl implements InventoryKeeperService {
    private final InventoryKeeperRepository inventoryKeeperRepository;

    private final WebClient.Builder webClientBuilder;

    @Autowired
    public InventoryKeeperServiceImpl(InventoryKeeperRepository inventoryKeeperRepository, WebClient.Builder webClientBuilder){
        this.inventoryKeeperRepository=inventoryKeeperRepository;
        this.webClientBuilder = webClientBuilder;
    }


    public void saveInventoryKeeper(RequestInventoryKeeperDto inventoryKeeper) {
        UUID uuid = UUID.randomUUID();
        Long inventoryKeeperID = uuid.getMostSignificantBits() & Long.MAX_VALUE;
        InventoryKeeper ip = new InventoryKeeper(
                inventoryKeeperID,
                inventoryKeeper.getInventoryKeeperName(),
                inventoryKeeper.getInventoryKeeperPhoneNumber(),
                inventoryKeeper.getInventoryKeeperEmail(),
                inventoryKeeper.getInventoryKeeperPassword()
        );
        inventoryKeeperRepository.save(ip);
    }

    // Update Product by productId
    public void updateInventoryKeeper(Long inventoryKeeperId, RequestInventoryKeeperDto updatedInventoryKeeper) {
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
    public List<ResponseInventoryKeeperDto> getAllInventoryKeepers() {
        return inventoryKeeperRepository.findAll().stream()
                .map(ip -> new ResponseInventoryKeeperDto(
                        ip.getInventoryKeeperId(),
                        ip.getInventoryKeeperName(),
                        ip.getInventoryKeeperPhoneNumber(),
                        ip.getInventoryKeeperEmail(),
                        ip.getInventoryKeeperPassword()
                ))
                .collect(Collectors.toList());
    }

    // Retrieve Inventory Keeper by inventoryKeeperId
    public ResponseInventoryKeeperDto getInventoryKeeperById(Long inventoryKeeperId) {
        Optional<InventoryKeeper> inventoryKeeperOptional = inventoryKeeperRepository.findById(inventoryKeeperId);
        if (inventoryKeeperOptional.isPresent()) {
            return new ResponseInventoryKeeperDto(
                    inventoryKeeperOptional.get().getInventoryKeeperId(),
                    inventoryKeeperOptional.get().getInventoryKeeperName(),
                    inventoryKeeperOptional.get().getInventoryKeeperPhoneNumber(),
                    inventoryKeeperOptional.get().getInventoryKeeperEmail(),
                    inventoryKeeperOptional.get().getInventoryKeeperPassword()
            );
        } else {
            throw new EntityNotFoundException("DeliveryPerson not found with ID: " + inventoryKeeperId);
        }
    }
}

