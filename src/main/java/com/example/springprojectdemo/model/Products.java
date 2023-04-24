package com.example.springprojectdemo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "products")
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type_of_clothing")
    private String type_of_clothing;

    @Column(name = "name_of_product")
    private String name_of_product;

    @Column(name = "price")
    private int price;
    @Column(name = "photo_src")
    private String photo_src;
}
