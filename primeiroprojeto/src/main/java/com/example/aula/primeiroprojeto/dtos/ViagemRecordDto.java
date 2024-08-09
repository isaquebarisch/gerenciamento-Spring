package com.example.aula.primeiroprojeto.dtos;

import com.example.aula.primeiroprojeto.models.DestinoModel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.UUID;

import java.math.BigDecimal;

public record ViagemRecordDto(
        @NotBlank String nome,
        @NotNull BigDecimal valor,
        @NotNull Long destinoId,
        DestinoModel destino) {
}

