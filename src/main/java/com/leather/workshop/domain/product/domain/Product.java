package com.leather.workshop.domain.product.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;


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

    @Enumerated(EnumType.STRING)
    @Column(length = 1, nullable = false, columnDefinition = "BIGINT default N")
    private BooleanFormatType deleteYn;

    @Column(nullable = false)
    private Long userId;

    private LocalDateTime deletedDateTime;

    @OneToMany(mappedBy = "product", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<ProductUploadFile> productUploadFiles = new LinkedHashSet<>();

    @Builder
    public Product(ProductCategory productCategory, String name, String contents, Long hits, BooleanFormatType deleteYn, Long userId, Set<ProductUploadFile> productUploadFiles) {
        this.productCategory = productCategory;
        this.name = name;
        this.contents = contents;
        this.hits = hits;
        this.deleteYn = deleteYn;
        this.userId = userId;
        this.productUploadFiles = productUploadFiles;
    }

    public void update(ProductCategory productCategory, String name, String contents, Set<ProductUploadFile> productUploadFiles) {
        this.productCategory = productCategory;
        this.name = name;
        this.contents = contents;
        this.productUploadFiles = productUploadFiles;
    }

    public void delete() {
        this.deleteYn = BooleanFormatType.Y;
        this.deletedDateTime = LocalDateTime.now();
    }

    public void countHits() {
        this.hits += 1;
    }
}
