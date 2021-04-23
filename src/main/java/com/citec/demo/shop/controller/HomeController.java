package com.citec.demo.shop.controller;

import com.citec.demo.shop.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    private final ProductService productService;

    public HomeController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(value = {"/", "/index", "/home"})
    public String home(Model model) {
        model.addAttribute("products", productService.findAllByOrderByIdAsc());
        model.addAttribute("productsCount", productService.count());

        return "home";
    }

    @GetMapping("/about")
    public String about() {
        return "about";
    }
}
