package com.leather.workshop.domain.product.domain;

import com.leather.workshop.global.common.domain.BaseTimeEntity;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class ProductUploadFile extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @Column(length = 100, nullable = false)
    private String uploadFileName;

    @Column(length = 100, nullable = false)
    private String storeFileName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "BIGINT default N")
    private BooleanFormatType thumbnailYn;

    @Builder
    public ProductUploadFile(Product product, String uploadFileName, String storeFileName, BooleanFormatType thumbnailYn) {
        this.product = product;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.thumbnailYn = thumbnailYn;
    }
}
