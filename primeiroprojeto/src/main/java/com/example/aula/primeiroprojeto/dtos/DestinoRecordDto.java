package com.example.aula.primeiroprojeto.dtos;

import jakarta.validation.constraints.NotBlank;

public record DestinoRecordDto(@NotBlank String nome) {
}
