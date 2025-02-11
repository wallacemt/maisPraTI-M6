package com.example.spring_api.repository;

import com.example.spring_api.model.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {

    List<Products> findByNameContaining(String name);
    @Query("SELECT p FROM Products p WHERE p.price BETWEEN :min AND :max")
    List<Products> findByPriceBetween(@Param("min")BigDecimal min, @Param("max") BigDecimal max);

}
