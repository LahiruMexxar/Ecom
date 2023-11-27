package com.example.Ecom.Repositories;

import com.example.Ecom.Classes.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface ProductRepository extends JpaRepository<Product,Long>{
    boolean existsByNameAndIdNot(@Param("name") String name, @Param("id") Long id);
//    @Query("SELECT COUNT(p) > 0 FROM Product p WHERE LOWER(p.name) = LOWER(:name) AND p.id != :id")
//    boolean existsByNameAndIdNotCaseInsensitive(@Param("name") String name, @Param("id") Long id);
    Optional<Product>findByName(String name);
    boolean existsByName(String name);
}
