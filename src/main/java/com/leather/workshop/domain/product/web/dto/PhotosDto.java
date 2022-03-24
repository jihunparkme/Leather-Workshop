package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.global.common.RegexUtil;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class PhotosDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private ProductCategoryDto.Response productCategory;
        private String name;
        private String thumbnailFileName;
        private List<String> contentsFileNames = new ArrayList<>();

        public Response(Product entity) {
            this.id = entity.getId();
            this.productCategory = new ProductCategoryDto.Response(entity.getProductCategory());
            this.name = entity.getName();
            this.thumbnailFileName = entity.getProductUploadFiles().stream()
                    .filter(productUploadFile -> BooleanFormatType.Y.equals(productUploadFile.getThumbnailYn()))
                    .findAny()
                    .map(productUploadFile -> {
                        return productUploadFile.getStoreFileName();
                    }).orElse(null);
            this.contentsFileNames = RegexUtil.findAllRegEx(entity.getContents(), RegexUtil.FILE_NAME_EXPRESSION);
        }
    }
}
