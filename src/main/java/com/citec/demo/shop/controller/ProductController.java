package com.citec.demo.shop.controller;

import com.citec.demo.shop.model.entity.Product;
import com.citec.demo.shop.service.ProductService;
import com.citec.demo.shop.validator.ProductValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import static java.util.Objects.nonNull;

@Controller
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;
    private final ProductValidator productValidator;

    public ProductController(ProductService productService, ProductValidator productValidator) {
        this.productService = productService;
        this.productValidator = productValidator;
    }

    @GetMapping("/product/new")
    public String newProduct(Model model) {
        model.addAttribute("productForm", new Product());
        model.addAttribute("method", "new");

        return "product";
    }

    @PostMapping("/product/new")
    public String newProduct(@ModelAttribute("productForm") Product productForm, BindingResult bindingResult, Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "new");
            return "product";
        }
        productService.save(productForm);
        logger.debug(String.format("Product with id: %s successfully created.", productForm.getId()));

        return "redirect:/home";
    }

    @GetMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable long productId, Model model) {
        Product product = productService.findById(productId);
        if (nonNull(product)) {
            model.addAttribute("productForm", product);
            model.addAttribute("method", "edit");
            return "product";
        }
        return "error/404";
    }

    @PostMapping("/product/edit/{productId}")
    public String editProduct(@PathVariable long productId,
                              @ModelAttribute("productForm") Product productForm,
                              BindingResult bindingResult, Model model) {
        productValidator.validate(productForm, bindingResult);

        if (bindingResult.hasErrors()) {
            logger.error(String.valueOf(bindingResult.getFieldError()));
            model.addAttribute("method", "edit");
            return "product";
        }
        productService.edit(productId, productForm);
        logger.debug(String.format("Product with id: %s has been successfully edited.", productId));

        return "redirect:/home";
    }

    @PostMapping("/product/delete/{productId}")
    public String deleteProduct(@PathVariable long productId) {
        Product product = productService.findById(productId);
        if (nonNull(product)) {
            productService.delete(productId);
            logger.debug(String.format("Product with id: %s successfully deleted.", product.getId()));
            return "redirect:/home";
        }
        return "error/404";
    }
}
