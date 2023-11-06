package com.architects.happydeals.Repositories;

import com.architects.happydeals.entity.InventoryKeeper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryKeeperRepository extends JpaRepository<InventoryKeeper, Long> {

}
