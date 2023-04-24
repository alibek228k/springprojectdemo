package com.example.springprojectdemo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "shopping_cart")
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Temporal(TemporalType.DATE)
    private Date date;
    @Transient
    private Double totalPrice;
    @Transient
    private int ItemsNumber;
    @OneToMany(cascade = CascadeType.ALL)
    private Collection<CartItem> item;

    public ShoppingCart(){
        item = new ArrayList<CartItem>();
    }

    private String tokenSession;

    public Double getTotalPrice() {
        Double sum = 0.0;
        for (CartItem item : this.item){
            sum = sum + item.getProduct().getPrice();
        }
        return sum;
    }

    public int getItemsNumber() {
        return this.item.size();
    }
}
