package com.architects.inventoryService.Repositories;

import com.architects.inventoryService.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

    // You can add custom queries or methods here if needed.
}