package com.architects.deliveryService.Repositories;

import com.architects.deliveryService.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {


}
