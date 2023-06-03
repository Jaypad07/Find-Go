package com.sei.findgo.repository;

import com.sei.findgo.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findByProductNameIgnoreCase(String product);

    List<Product> findProductsByCategoryIgnoreCase(String category);

    Optional<Product> findByProductNameIgnoreCaseAndCategoryIgnoreCase(String product, String category);
}
