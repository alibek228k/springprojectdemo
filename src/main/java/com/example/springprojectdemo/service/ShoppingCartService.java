package com.example.springprojectdemo.service;

import com.example.springprojectdemo.model.CartItem;
import com.example.springprojectdemo.model.Products;
import com.example.springprojectdemo.model.ShoppingCart;
import com.example.springprojectdemo.repository.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class ShoppingCartService {
    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductService productService;

    public ShoppingCart addShoppingCartFirstTime(Long id, String sessionToken, int quantity) {
        ShoppingCart shoppingCart = new ShoppingCart();
        CartItem cartItem = new CartItem();
        cartItem.setQuantity(quantity);
        cartItem.setDate(new Date());
        cartItem.setProduct(productService.getProductById(id));
        shoppingCart.getItem().add(cartItem);
        shoppingCart.setTokenSession(sessionToken);
        return shoppingCartRepository.save(shoppingCart);
    }

    public ShoppingCart addToExistingShoppingCart(Long id, String sessionToken, int quantity) {
        ShoppingCart shoppingCart = shoppingCartRepository.findByTokenSession(sessionToken);
        Products p = productService.getProductById(id);
        for (CartItem item : shoppingCart.getItem()){
            if (p.getId().equals(item.getProduct().getId())){
                item.setQuantity(item.getQuantity()+quantity);
                return shoppingCartRepository.save(shoppingCart);
            }
        }
        CartItem cartItem =new CartItem();
        cartItem.setDate(new Date());
        cartItem.setQuantity(quantity);
        cartItem.setProduct(p);
        shoppingCart.getItem().add(cartItem);
        return shoppingCartRepository.save(shoppingCart);
    }
}
