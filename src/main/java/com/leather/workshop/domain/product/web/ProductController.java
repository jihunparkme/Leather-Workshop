package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequiredArgsConstructor
@Controller
@RequestMapping(value = "/product")
public class ProductController {

    private final ProductService categoryService;

    @GetMapping("")
    public String list(
            @RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
            @RequestParam(value = "size", required = false, defaultValue = "5") Integer size,
            Model model) {

        model.addAttribute("categoryList", categoryService.getCategoryRepository().findAllOrderByTitle());
        return "product/product-list";
    }

    @GetMapping("{id}")
    public String detail() {
        return "product/product-details";
    }
}
