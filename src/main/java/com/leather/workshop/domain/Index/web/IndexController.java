package com.leather.workshop.domain.Index.web;

import com.leather.workshop.domain.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

    private final ProductService productService;

    @GetMapping("/")
    public String index(Model model) {

        model.addAttribute("productListPage", productService.findTop12OrderByIdDesc());

        return "index";
    }
}
