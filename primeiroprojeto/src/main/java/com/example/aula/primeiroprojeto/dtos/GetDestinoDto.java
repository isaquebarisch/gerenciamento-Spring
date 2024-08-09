package com.example.aula.primeiroprojeto.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record GetDestinoDto(@NotNull Long id, @NotBlank String nome) {
}
