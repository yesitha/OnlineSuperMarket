package com.architects.userService.Repositories;

import com.architects.userService.entity.DeliveryPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface DeliveryPersonRepository extends JpaRepository<DeliveryPerson, Long> {

    // You can add custom queries or methods here if needed.
}
