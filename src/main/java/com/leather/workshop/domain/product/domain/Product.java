package com.leather.workshop.domain.product.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    private List<ProductUploadFile> productUploadFileList = new ArrayList<>();
}
