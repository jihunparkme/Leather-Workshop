package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Builder;
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
            this.productCategory = ProductCategoryDto.builder()
                    .id(entity.getProductCategory().getId())
                    .title(entity.getProductCategory().getTitle())
                    .orderNo(entity.getProductCategory().getOrderNo())
                    .categoryUseYn(entity.getProductCategory().getCategoryUseYn())
                    .build();
            this.name = entity.getName();
            this.contents = entity.getContents();
            this.hits = entity.getHits();
            this.deleteYn = entity.getDeleteYn();
            this.userId = entity.getUserId();
            this.deletedDateTime = entity.getDeletedDateTime();
            this.productUploadFiles = entity.getProductUploadFiles().stream()
                                            .map(productUploadFile -> ProductUploadFileDto.builder()
                                                    .id(productUploadFile.getId())
                                                    .uploadFileName(productUploadFile.getUploadFileName())
                                                    .storeFileName(productUploadFile.getStoreFileName())
                                                    .thumbnailYn(productUploadFile.getThumbnailYn())
                                                    .build())
                                            .collect(Collectors.toList());
        }
    }

    @Builder
    @Getter
    public static class ProductCategoryDto {
        private Long id;
        private String title;
        private Integer orderNo;
        private String categoryUseYn;
    }

    @Builder
    @Getter
    public static class ProductUploadFileDto {
        private Long id;
        private String uploadFileName;
        private String storeFileName;
        private BooleanFormatType thumbnailYn;
    }
}
