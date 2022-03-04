package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.product.domain.ProductCategoryRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Getter
@Service
public class ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;


}
