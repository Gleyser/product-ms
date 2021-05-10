package com.gleyser.productms.Specification;

import com.gleyser.productms.entity.Product;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;

public class MaxPriceSpecification {
    public static Specification<Product> maxPrice(BigDecimal maxPrice) {
        if (maxPrice == null){
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        } else {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.<String>get("price"), maxPrice.toString());
        }

    }

}
