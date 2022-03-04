package com.leather.workshop.domain.product.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class Product extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_category_id", nullable = false)
    private ProductCategory productCategory;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 20000)
    private String contents;

    @Column(nullable = false, columnDefinition = "BIGINT default 0")
    private Long hits;

    @Column(length = 1, nullable = false, columnDefinition = "BIGINT default N")
    private String deleteYn;

    @Column(nullable = false)
    private Long userId;

    private LocalDateTime deletedDateTime;
}
