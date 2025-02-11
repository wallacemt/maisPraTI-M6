package com.example.spring_api;

import com.example.spring_api.model.Products;
import com.example.spring_api.repository.ProductsRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Java6Assertions.assertThat;

import java.math.BigDecimal;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductsRepository ProductsRepository;

    @Test
    public void testCreateAndGetProducts() {
        Products Products = new Products("Produto Exemplo", "Descrição do Produto", new BigDecimal("99.99"));

        ResponseEntity<Products> postResponse = restTemplate
                .withBasicAuth("user", "password")
                .postForEntity("/api/products", Products, Products.class);

        assertThat(postResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        Products createdProducts = postResponse.getBody();
        assertThat(createdProducts).isNotNull();
        assertThat(createdProducts.getId()).isNotNull();

        ResponseEntity<Products> getResponse = restTemplate
                .withBasicAuth("user", "password")
                .getForEntity("/api/products/" + createdProducts.getId(), Products.class);

        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);

        Products retrievedProducts = getResponse.getBody();
        assertThat(retrievedProducts.getName()).isEqualTo("Produto Exemplo");
    }
}
