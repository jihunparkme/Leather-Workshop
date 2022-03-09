package com.leather.workshop.domain.product.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepositoryCustom {

    public Page<Product> findAllProductPagination(String category, Pageable pageable);

    public List<Product> findAllProduct(String category);
}
