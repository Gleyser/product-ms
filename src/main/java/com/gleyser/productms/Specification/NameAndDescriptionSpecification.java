package com.gleyser.productms.Specification;

import com.gleyser.productms.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class NameAndDescriptionSpecification {
    public static Specification<Product> nameAndDescription(String nameAndDescriptionFilter) {
        if (nameAndDescriptionFilter == null){
            return (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.isTrue(criteriaBuilder.literal(true));
        } else {
            return (root, criteriaQuery, criteriaBuilder) ->
                    criteriaBuilder.or(
                            criteriaBuilder.like(root.<String>get("name"), "%" + nameAndDescriptionFilter + "%"),
                            criteriaBuilder.like(root.<String>get("description"), "%" + nameAndDescriptionFilter + "%")
                    );
        }
    }
}
