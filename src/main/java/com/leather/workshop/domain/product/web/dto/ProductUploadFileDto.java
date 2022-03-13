package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.ProductUploadFile;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.*;


public class ProductUploadFileDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String uploadFileName;
        private String storeFileName;
        private BooleanFormatType thumbnailYn;

        public Response(ProductUploadFile entity) {
            this.id = entity.getId();
            this.uploadFileName = entity.getUploadFileName();
            this.storeFileName = entity.getStoreFileName();
            this.thumbnailYn = entity.getThumbnailYn();
        }
    }
}
