package com.example.aula.primeiroprojeto.repositories;

import com.example.aula.primeiroprojeto.models.DestinoModel;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DestinoRepository extends JpaRepository<DestinoModel, Long> {
}
