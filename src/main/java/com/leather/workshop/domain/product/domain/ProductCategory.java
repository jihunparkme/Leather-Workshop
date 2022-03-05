package com.leather.workshop.domain.product.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@NoArgsConstructor
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    private Integer orderNo;

    @Column(length = 1)
    private String categoryUseYn;

    @OneToMany(mappedBy = "productCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Product> products = new LinkedHashSet<>();
}