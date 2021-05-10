package com.gleyser.productms.repository;

import com.gleyser.productms.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ProductSpecificationRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {

}
