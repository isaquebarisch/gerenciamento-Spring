package com.example.exercicio.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LivroDto(
        @NotBlank String titulo,
        @NotBlank String autor,
        @NotBlank String isbn,
        @NotBlank String genero
) {
}
