package com.leather.workshop.domain.product.web;

import com.leather.workshop.domain.product.domain.Product;
import com.leather.workshop.domain.product.service.ProductService;
import com.leather.workshop.domain.product.web.dto.ProductDto;
import com.leather.workshop.global.common.exception.EntityNotFoundException;
import com.leather.workshop.global.common.util.ClientIpAddressUtil;
import com.leather.workshop.global.common.util.web.dto.AlertMessage;
import com.leather.workshop.global.config.security.LoginUser;
import com.leather.workshop.global.config.security.dto.SessionUser;
import com.leather.workshop.global.config.session.SessionConst;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
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
            Product product = productService.getProductRepository().findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("?????? ???????????? ???????????? ????????????. id=" + id));
            product.countHits();
            productService.getProductRepository().save(product);
        }

        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.VIEW_PRODUCT, sessionValue);
        session.setMaxInactiveInterval(300);

        model.addAttribute("product", productService.findById(id));

        return "product/product-view";
    }

    @GetMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String add(Model model) {

        model.addAttribute("product", new ProductDto.Response());
        model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());

        return "product/product-add";
    }
    
    @PostMapping("/add")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String add(@Validated @ModelAttribute("product") ProductDto.SaveRequest form,
                      BindingResult bindingResult,
                      @LoginUser SessionUser user,
                      RedirectAttributes redirectAttributes,
                      Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());
            return "product/product-add";
        }

        if (form.getThumbnailFile().isEmpty()) {
            model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());
            model.addAttribute("thumbnailFileError", "???????????? ???????????? ????????? ??????????????????.");
            return "product/product-add";
        }

        Long id = null;
        try {
            id = productService.save(form, user);
        } catch (IOException e) {
            model.addAttribute("error", new AlertMessage("?????? ????????? ?????????????????????.\n??????????????? ????????? ?????????."));
            return "/common/util/message-redirect";
        }
        redirectAttributes.addAttribute("id", id);
        redirectAttributes.addAttribute("status", true);

        return "redirect:/product/{id}";
    }

    @GetMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable Long id, Model model) {

        model.addAttribute("product", productService.findById(id));
        model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());

        return "product/product-edit";
    }

    @PostMapping("/{id}/edit")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public String edit(@PathVariable Long id,
                       @Validated @ModelAttribute("product") ProductDto.UpdateRequest product,
                       BindingResult bindingResult,
                       @LoginUser SessionUser user,
                       RedirectAttributes redirectAttributes,
                       Model model) {

        if (bindingResult.hasErrors()) {
            model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());
            return "product/product-edit";
        }

        if (product.getIsDeleteThumbnail() && product.getThumbnailFile().isEmpty()) {
            model.addAttribute("categoryList", productService.getCategoryRepository().findAllOrderByTitle());
            model.addAttribute("thumbnailFileError", "???????????? ???????????? ????????? ??????????????????.");
            return "product/product-edit";
        }

        try {
            productService.edit(id, product, user);
        } catch (IOException e) {
            model.addAttribute("error", new AlertMessage("?????? ????????? ?????????????????????.\n??????????????? ????????? ?????????."));
            return "/common/util/message-redirect";
        }

        redirectAttributes.addAttribute("status", true);

        return "redirect:/product/{id}";
    }

    @ResponseBody
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    public Long delete(@PathVariable Long id) {
        productService.delete(id);
        return id;
    }
}
