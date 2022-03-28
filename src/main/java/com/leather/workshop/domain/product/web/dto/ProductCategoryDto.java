package com.leather.workshop.domain.product.web.dto;

import com.leather.workshop.domain.product.domain.ProductCategory;
import com.leather.workshop.global.common.dto.BooleanFormatType;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class ProductCategoryDto {

    @Getter
    @NoArgsConstructor
    public static class Response {
        private Long id;
        private String title;
        private Integer orderNo;
        private BooleanFormatType categoryUseYn;

        public Response(ProductCategory entity) {
            this.id = entity.getId();
            this.title = entity.getTitle();
            this.orderNo = entity.getOrderNo();
            this.categoryUseYn = entity.getCategoryUseYn();
        }
    }
}
