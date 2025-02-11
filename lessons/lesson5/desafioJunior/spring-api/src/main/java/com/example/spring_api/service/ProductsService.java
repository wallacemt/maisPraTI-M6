package com.example.spring_api.service;

import com.example.spring_api.model.Products;
import com.example.spring_api.repository.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class ProductsService {
    @Autowired
    private ProductsRepository productsRepository;

    public List<Products> getAllProducts(){
        return productsRepository.findAll();
    }

    public Products createProduct(Products products){
        return productsRepository.save(products);
    }


    public Products updateProducts(Products products){
        return productsRepository.save(products);
    }

    public void deleteProduct(Long id){
        productsRepository.deleteById(id);
    }

    public Optional<Products> getProductsById(Long id){
        return productsRepository.findById(id);
    }

    public List<Products> searchProducts(String name, BigDecimal minPrice, BigDecimal maxPrice){
        if(name != null && !name.isEmpty()){
            return productsRepository.findByNameContaining(name);
        }

        if(minPrice != null && maxPrice != null){
            return productsRepository.findByPriceBetween(minPrice, maxPrice);
        }
        return productsRepository.findAll();
    }

}
