package com.architects.inventoryService.Repositories;

import com.architects.inventoryService.entity.InventoryKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface InventoryKeeperRepository extends JpaRepository<InventoryKeeper, Long> {

    Optional<InventoryKeeper> findByInventoryKeeperName(String inventoryKeeperName);
}




