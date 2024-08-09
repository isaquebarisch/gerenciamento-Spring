package com.example.aula.primeiroprojeto.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "viagem")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViagemModel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "destino_id", nullable = false)
    private DestinoModel destino;
}
