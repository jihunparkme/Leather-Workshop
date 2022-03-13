package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

public class ProductDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private ProductCategoryDto.Response productCategory;
        private String name;
        private String contents;
        private Long hits;
        private BooleanFormatType deleteYn;
        private Long userId;
        private LocalDateTime deletedDateTime;
        private LocalDateTime createdDateTime;
        private LocalDateTime modifiedDateTime;
        private ProductUploadFileDto.Response thumbnailFile;
        private List<ProductUploadFileDto.Response> productUploadFiles = new ArrayList<>();

        public Response(Product entity) {
            this.id = entity.getId();
            this.productCategory = new ProductCategoryDto.Response(entity.getProductCategory());
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
                                            return new ProductUploadFileDto.Response(productUploadFile);
                                        })
                                        .orElse(null);
            this.productUploadFiles = entity.getProductUploadFiles().stream()
                                            .map(productUploadFile -> new ProductUploadFileDto.Response(productUploadFile))
                                            .collect(Collectors.toList());
        }
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {
        @NotBlank
        private Long productCategory;
        @NotBlank
        private String name;
        private String contents;
        @NotBlank
        private MultipartFile thumbnailFile;
        private List<MultipartFile> productUploadFiles;
    }
}
