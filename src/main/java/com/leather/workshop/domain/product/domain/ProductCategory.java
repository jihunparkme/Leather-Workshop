package com.leather.workshop.domain.product.domain;

import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class ProductCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 20, nullable = false)
    private String title;

    private Integer orderNo;

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false, columnDefinition = "BIGINT default N")
    private BooleanFormatType categoryUseYn;

    @OneToMany(mappedBy = "productCategory", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Product> products = new LinkedHashSet<>();
}