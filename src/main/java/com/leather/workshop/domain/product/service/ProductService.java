package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.domain.ProductCategoryRepository;
import com.leather.workshop.domain.product.domain.ProductRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Getter
@Service
public class ProductService {

    private final ProductCategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAllSortByIdDescPaging(Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
        return productRepository.findAll(pageable);
    }
}
