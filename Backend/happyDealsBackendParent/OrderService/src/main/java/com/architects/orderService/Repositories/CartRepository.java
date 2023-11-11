package com.architects.orderService.Repositories;

import com.architects.orderService.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableJpaRepositories
public interface CartRepository extends JpaRepository<Cart, Long> {
    boolean existsByCustomerId(Long customerId);
    Cart findByCustomerId(Long customerId);
}
