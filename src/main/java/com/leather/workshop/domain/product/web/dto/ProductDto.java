package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.domain.ProductCategory;
import com.leather.workshop.domain.product.domain.ProductUploadFile;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
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
        private LocalDateTime createdDateTime;
        private LocalDateTime modifiedDateTime;
        private ProductUploadFileDto thumbnailFile;
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
            this.createdDateTime = entity.getCreatedDateTime();
            this.modifiedDateTime = entity.getModifiedDateTime();
            this.thumbnailFile = entity.getProductUploadFiles().stream()
                                        .filter(productUploadFile -> BooleanFormatType.Y.equals(productUploadFile.getThumbnailYn()))
                                        .findAny()
                                        .map(productUploadFile -> {
                                            return new ProductUploadFileDto(productUploadFile);
                                        })
                                        .orElse(null);
            this.productUploadFiles = entity.getProductUploadFiles().stream()
                                            .map(productUploadFile -> new ProductUploadFileDto(productUploadFile))
                                            .collect(Collectors.toList());
        }
    }

    @Getter
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

    @Getter
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class SaveRequest {
        @NotBlank
        private ProductCategory productCategory;
        @NotBlank
        private String name;
        private String contents;
        @NotBlank
        private Long userId;
        private Set<ProductUploadFile> productUploadFiles;

        public Product toEntity() {
            return Product.builder()
                    .productCategory(productCategory)
                    .name(name)
                    .contents(contents)
                    .hits(0L)
                    .deleteYn(BooleanFormatType.N)
                    .userId(userId)
                    .productUploadFiles(productUploadFiles)
                    .build();
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class UpdateRequest {
        @NotBlank
        private ProductCategory productCategory;
        @NotBlank
        private String name;
        private String contents;
        private Set<ProductUploadFile> productUploadFiles;

        public Product toEntity() {
            return Product.builder()
                    .productCategory(productCategory)
                    .name(name)
                    .contents(contents)
                    .productUploadFiles(productUploadFiles)
                    .build();
        }
    }
}
