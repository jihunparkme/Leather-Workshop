package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.service.ProductService;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.response.PageResponse;
import com.leather.workshop.global.common.util.ClientIpAddressUtil;
import com.leather.workshop.global.config.session.SessionConst;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService productService;

    @GetMapping("")
    public String list(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "12") Integer size,
            @RequestParam(value = "category", required = false, defaultValue = "ALL") String category,
            Model model) {

        Page<Product> productListPage = productService.findAllSortByIdDescPaging(category, page, size);

        model.addAttribute("productListPage", productListPage);
        model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());
        model.addAttribute("page", page);
        model.addAttribute("category", category);

        return "product/product-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id,
                         @SessionAttribute(name = SessionConst.VIEW_PRODUCT, required = false) String viewProduct,
                         Model model,
                         HttpServletRequest request) {

        String sessionValue = id + "/" + ClientIpAddressUtil.getClientIP(request);
        if (viewProduct == null || !sessionValue.equals(viewProduct)) {
            Product product = productService.getProductRepository().findById(id).get();
            product.countHits();
            productService.getProductRepository().save(product);
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.VIEW_PRODUCT, sessionValue);
        session.setMaxInactiveInterval(300);

        model.addAttribute("product", productService.findById(id));

        return "product/product-view";
    }

    @ResponseBody
    @GetMapping("/scroll/{category}")
    public ResponseEntity<PageResponse> scrollList(
            @PathVariable String category,
            @PageableDefault(page = 0, size = 10) Pageable pageable,
            Model model) {

        Page<Product> productListPage = productService.findAllSortByIdDescPaging(category, pageable.getPageNumber(), pageable.getPageSize());
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
