package com.gleyser.productms.repository;

import com.gleyser.productms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findAllByName(String filter);

    List<Product> findAllByDescription(String filter);
}
