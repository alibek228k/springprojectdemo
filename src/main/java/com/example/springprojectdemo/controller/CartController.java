package com.example.springprojectdemo.controller;

import com.example.springprojectdemo.model.CartItem;
import com.example.springprojectdemo.model.ShoppingCart;
import com.example.springprojectdemo.service.ProductService;
import com.example.springprojectdemo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.UUID;

@Controller
public class CartController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ShoppingCartService shoppingCartService;

    @PostMapping("/addToCart")
    public String addToCart(HttpSession session, Model model,@RequestParam("id") Long id, @RequestParam("quantity") int quantity){
        String sessionToken = (String) session.getAttribute("sessionToken");
        if (sessionToken == null){
            sessionToken = UUID.randomUUID().toString();
            session.setAttribute("sessionToken", sessionToken);
            System.out.println(id + quantity);
            shoppingCartService.addShoppingCartFirstTime(id, sessionToken, quantity);
        }else{
            shoppingCartService.addToExistingShoppingCart(id, sessionToken, quantity);
        }
        return "redirect:/products";
    }
}
