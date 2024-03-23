package co.istad.springhomeworkjpa.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.constraints.*;

public record ProductCreateRequest(
        @NotBlank
        @Size(max = 60)
        String name,
        @NotNull
        @Positive
        Double price,
        @Positive
        @NotNull
        @Max(100)
        Integer qty
) {
}
