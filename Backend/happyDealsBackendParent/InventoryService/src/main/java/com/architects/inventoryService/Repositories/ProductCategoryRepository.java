package com.architects.inventoryService.Repositories;

import com.architects.inventoryService.entity.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {


    //@Query(value = "SELECT * FROM product_category WHERE product_category_name = :productCategoryName", nativeQuery = true)
    public ProductCategory findProductCategoryByProductCategoryName(String productCategoryName);
}