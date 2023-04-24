package com.example.springprojectdemo.controller;

import com.example.springprojectdemo.model.Products;
import com.example.springprojectdemo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    public ProductController(ProductService productService){
        this.productService = productService;
    }
    @GetMapping("/products")
    public ModelAndView findAll(Model model){
        ModelAndView modelAndView = new ModelAndView();
        List<Products> products = productService.findAll();
        model.addAttribute("products", products);
        modelAndView.setViewName("index");
        return modelAndView;
    }
    @GetMapping("/product-create")
    public String createProductForm(Products products){
        return "product-create";
    }

    @PostMapping("/product-create")
    public String createProduct(Products product){
        productService.saveProduct(product);
        return "redirect:/products_editor";
    }

    @GetMapping("product-delete/{id}")
    public String deleteProduct(@PathVariable("id") Long id){
        productService.deleteById(id);
        return "redirect:/products_editor";
    }

    @GetMapping("product-update/{id}")
    public String updateProductForm(@PathVariable("id") Long id, Model model){
        Products product = productService.findById(id);
        model.addAttribute("products", product);
        return "/product-update";
    }

    @PostMapping("/updateProduct")
    public String updateProduct(Products product){
        productService.saveProduct(product);
        return "redirect:/products_editor";
    }

}
