package com.example.Ecom.Test;

import com.example.Ecom.Classes.Product;
import com.example.Ecom.Repositories.ProductRepository;
import com.mysql.cj.x.protobuf.MysqlxCursor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRespitoryTest {
    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testSaveProduct() {
        // Given
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(200);

        // When
        Product savedProduct = productRepository.save(product);

        // Then
        assertTrue(savedProduct.getId() != null);
        assertEquals("TestProduct", savedProduct.getName());
        assertEquals(200, savedProduct.getPrice());
    }

    @Test
    public void testFindById() {
        // Given
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(200);
        Product savedProduct = productRepository.save(product);

        // When
        Optional<Product> foundProduct = productRepository.findById(savedProduct.getId());

        // Then
        assertTrue(foundProduct.isPresent());
        assertEquals(savedProduct.getName(), foundProduct.get().getName());
        assertEquals(savedProduct.getPrice(), foundProduct.get().getPrice());
    }

    @Test
    public void testFindAll() {
        // Given
        Product product1 = new Product();
        product1.setName("Product1");
        product1.setPrice(200);
        productRepository.save(product1);

        Product product2 = new Product();
        product2.setName("Product2");
        product2.setPrice(250);
        productRepository.save(product2);

        // When
        List<Product> products = productRepository.findAll();

        // Then
        assertEquals(2, products.size());
    }

    @Test
    public void testDeleteProduct() {
        // Given
        Product product = new Product();
        product.setName("TestProduct");
        product.setPrice(200);
        Product savedProduct = productRepository.save(product);

        // When
        productRepository.deleteById(savedProduct.getId());

        // Then
        Optional<Product> deletedProduct = productRepository.findById(savedProduct.getId());
        assertTrue(deletedProduct.isEmpty());
    }
}

