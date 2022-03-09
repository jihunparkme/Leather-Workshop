package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.service.ProductService;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService categoryService;

    @GetMapping("")
    public String list(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "10") Integer size,
            Model model) {

        Page<Product> productListPage = categoryService.findAllSortByIdDescPaging("ALL", page, size);

        model.addAttribute("productListPage", productListPage);
        model.addAttribute("categoryList", categoryService.getCategoryRepository().findAllOrderByTitle());

        return "product/product-list";
    }

    @GetMapping("/{id}")
    public String detail() {
        return "product/product-details";
    }

    @ResponseBody
    @GetMapping("/scroll/{category}")
    public ResponseEntity<PageResponse> scrollList(
            @PathVariable String category,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {

        Page<Product> productListPage = categoryService.findAllSortByIdDescPaging(category, pageable.getPageNumber(), pageable.getPageSize());
        List<Object> resultList = productListPage.getContent().stream()
                                                            .map(product -> new ProductDto.Response(product))
                                                            .collect(Collectors.toList());

        PageResponse pageResponse = PageResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("성공적으로 조회되었습니다.")
                .count(productListPage.getSize())
                .totalElements(productListPage.getTotalElements())
                .totalPages(productListPage.getTotalPages())
                .result(resultList)
                .build();

        return new ResponseEntity<>(pageResponse, pageResponse.getHttpStatus());
    }
}
