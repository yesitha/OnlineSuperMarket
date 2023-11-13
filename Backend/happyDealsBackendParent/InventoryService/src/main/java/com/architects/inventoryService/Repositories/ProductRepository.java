package com.architects.inventoryService.Repositories;

import com.architects.inventoryService.entity.InventoryKeeper;
import com.architects.inventoryService.entity.Product;
import com.architects.inventoryService.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByProductName(String productName);
    List<Product> findByProductCategory(ProductCategory productCategory);
}