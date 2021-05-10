package com.gleyser.productms.Specification;

import com.gleyser.productms.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class MinPriceSpecification {
    public static Specification<Product> minPrice(BigDecimal minPrice) {
        if (minPrice == null){
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        } else {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.<String>get("price"), minPrice.toString());
        }

    }

}
