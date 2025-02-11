package com.example.spring_api.controller;

import com.example.spring_api.model.Products;
import com.example.spring_api.service.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("api/products")
public class ProductsController {

    @Autowired
    private ProductsService productsService;

    @GetMapping
    public ResponseEntity<List<Products>> getAllProducts() {
        List<Products> products = productsService.getAllProducts();
        return  new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Products> createProduct(@RequestBody Products products){
        Products createProduct = productsService.createProduct(products);
        return  new ResponseEntity<>(createProduct, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Products> updateProduct(@PathVariable Long id, @RequestBody Products products){
        Optional<Products> existingProduct = productsService.getProductsById(id);
        if(existingProduct.isPresent()){
            products.setId(id);
            Products updateProduct = productsService.updateProducts(products);
            return  new ResponseEntity<>(updateProduct, HttpStatus.OK);
        }
        return  new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        productsService.deleteProduct(id);
        return  new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Products> getProductById(@PathVariable Long id){
        Optional<Products> products = productsService.getProductsById(id);
        return products.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/search")
    public ResponseEntity<List<Products>> searchProducts(@RequestParam(required = false) String name, @RequestParam(required = false)BigDecimal minPrice, @RequestParam(required = false) BigDecimal maxPrice) {
        List<Products> products = productsService.searchProducts(name, minPrice, maxPrice);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }
}
