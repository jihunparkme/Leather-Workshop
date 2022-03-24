package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.service.ProductService;
import com.leather.workshop.domain.product.web.dto.PhotosDto;
import com.leather.workshop.global.common.response.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/photos")
public class PhotosController {

    private final ProductService productService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());

        return "photos/photo-list";
    }

    @ResponseBody
    @GetMapping("/list/{category}")
    public ResponseEntity<PageResponse> scrollList(
            @PathVariable String category,
            @PageableDefault(page = 0, size = 10) Pageable pageable) {

        Page<Product> productListPage = productService.findAllSortByIdDescPaging(category.toUpperCase(), pageable.getPageNumber(), pageable.getPageSize());
        List<Object> resultList = productListPage.getContent().stream()
                .map(product -> new PhotosDto.Response(product))
                .collect(Collectors.toList());

        PageResponse pageResponse = PageResponse.builder()
                .code(HttpStatus.OK.value())
                .httpStatus(HttpStatus.OK)
                .message("성공적으로 조회되었습니다.")
                .count(resultList.size())
                .totalElements(productListPage.getTotalElements())
                .totalPages(productListPage.getTotalPages())
                .result(resultList)
                .build();

        return new ResponseEntity<>(pageResponse, pageResponse.getHttpStatus());
    }
}
