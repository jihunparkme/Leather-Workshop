package com.leather.workshop.domain.product.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Long> {

    @Query("SELECT DISTINCT pc FROM ProductCategory pc ORDER BY pc.title")
    List<ProductCategory> findAllOrderByTitle();
}
