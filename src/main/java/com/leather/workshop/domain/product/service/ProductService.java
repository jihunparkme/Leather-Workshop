package com.leather.workshop.domain.product.service;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.domain.ProductCategoryRepository;
import com.leather.workshop.domain.product.domain.ProductRepository;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Getter
@Service
public class ProductService {

    private final ProductCategoryRepository categoryRepository;

    private final ProductRepository productRepository;

    @Transactional(readOnly = true)
    public Page<Product> findAllSortByIdDescPaging(String category, Integer page, Integer size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findAllProductPagination(category, pageable);
    }

    @Transactional(readOnly = true)
    public ProductDto.Response findById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("해당 상품이 없습니다. id=" + id));

        return new ProductDto.Response(product);
    }
}
