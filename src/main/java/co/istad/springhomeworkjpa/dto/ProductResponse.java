package co.istad.springhomeworkjpa.dto;

import jakarta.persistence.criteria.CriteriaBuilder;

public record ProductResponse(
     String uuid,
     String name,
     Double price,
     Integer qty
) {
}
