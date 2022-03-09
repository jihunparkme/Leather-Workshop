package com.leather.workshop.domain.product.domain;

import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.QueryResults;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.jpa.JPQLQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;

import java.util.List;

public class ProductRepositoryCustomImpl extends QuerydslRepositorySupport implements ProductRepositoryCustom {

    public ProductRepositoryCustomImpl() {
        super(Product.class);
    }

    @Override
    public Page<Product> findAllProductPagination(String category, Pageable pageable) {

        JPQLQuery<Product> query = findAllGalleryQuery(category, pageable);
        QueryResults<Product> queryResults = query.fetchResults();

        return new PageImpl<>(queryResults.getResults(), pageable, queryResults.getTotal());
    }

    @Override
    public List<Product> findAllProduct(String category) {

        JPQLQuery<Product> query = findAllGalleryQuery(category, null);

        return query.fetchResults().getResults();
    }

    private JPQLQuery<Product> findAllGalleryQuery(String category, Pageable pageable) {

        QProduct product = QProduct.product;

        JPQLQuery<Product> query = from(product);
        BooleanBuilder bb = new BooleanBuilder();

        if (!"ALL".equals(category)) {
            bb.and(product.productCategory.title.eq(category));
        }

        if (pageable != null) {
            query.limit(pageable.getPageSize());
            query.offset(pageable.getOffset());
        }

        OrderSpecifier<Long> orderId = product.id.desc();

        return query.distinct()
                .leftJoin(product.productCategory)
                .leftJoin(product.productUploadFiles)
                .where(bb)
                .orderBy(orderId);
    }
}
