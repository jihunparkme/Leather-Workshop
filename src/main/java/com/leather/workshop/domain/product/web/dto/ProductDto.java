package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.domain.ProductCategory;
import com.leather.workshop.domain.product.domain.ProductUploadFile;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ProductDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private ProductCategoryDto productCategory;
        private String name;
        private String contents;
        private Long hits;
        private BooleanFormatType deleteYn;
        private Long userId;
        private LocalDateTime deletedDateTime;
        private List<ProductUploadFileDto> productUploadFiles = new ArrayList<>();

        public Response(Product entity) {
            this.id = entity.getId();
            this.productCategory = new ProductCategoryDto(entity.getProductCategory());
            this.name = entity.getName();
            this.contents = entity.getContents();
            this.hits = entity.getHits();
            this.deleteYn = entity.getDeleteYn();
            this.userId = entity.getUserId();
            this.deletedDateTime = entity.getDeletedDateTime();
            this.productUploadFiles = entity.getProductUploadFiles().stream()
                                            .map(productUploadFile -> new ProductUploadFileDto(productUploadFile))
                                            .collect(Collectors.toList());
        }
    }

    public static class ProductCategoryDto {
        private Long id;
        private String title;
        private Integer orderNo;
        private String categoryUseYn;

        public ProductCategoryDto(ProductCategory entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.orderNo = entity.getOrderNo();
            this.categoryUseYn = entity.getCategoryUseYn();
        }
    }

    public static class ProductUploadFileDto {
        private Long id;
        private String uploadFileName;
        private String storeFileName;
        private BooleanFormatType thumbnailYn;

        public ProductUploadFileDto(ProductUploadFile entity) {
            this.id = entity.getId();
            this.uploadFileName = entity.getUploadFileName();
            this.storeFileName = entity.getStoreFileName();
            this.thumbnailYn = entity.getThumbnailYn();
        }
    }
}
