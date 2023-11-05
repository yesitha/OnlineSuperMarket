package com.architects.happydeals.Repositories;

import com.architects.happydeals.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

    // You can add custom queries or methods here if needed.
}
