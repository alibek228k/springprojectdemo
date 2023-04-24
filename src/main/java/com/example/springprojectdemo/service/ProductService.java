package com.example.springprojectdemo.service;
import com.example.springprojectdemo.model.Products;
import com.example.springprojectdemo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository){
        this.productRepository = productRepository;
    }
    public Products getProductById(Long id) {

        return productRepository.findById(id).get();
    }
    public Products findById(Long id) {
        return productRepository.getOne(id);
    }
    public Products saveProduct(Products product){
        return productRepository.save(product);
    }
    public List<Products> findAll(){
        return productRepository.findAll();
    }
    public void deleteById(Long id){
        productRepository.deleteById(id);
    }
}
