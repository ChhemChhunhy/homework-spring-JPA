package co.istad.springhomeworkjpa.dto;

import jakarta.validation.constraints.*;

public record ProductEditRequest(
        @NotBlank
        @Size(max = 40)
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
