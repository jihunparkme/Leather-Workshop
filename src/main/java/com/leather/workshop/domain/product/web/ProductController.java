package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.service.ProductCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductCategoryService productCategoryService;

    @GetMapping("")
    public String list(Model model) {

        model.addAttribute("categoryList", productCategoryService.getProductCategoryRepository().findAllOrderByTitle());
        return "product/product-list";
    }

    @GetMapping("{id}")
    public String detail() {
        return "product/product-details";
    }
}
