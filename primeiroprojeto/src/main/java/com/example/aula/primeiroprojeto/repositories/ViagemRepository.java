package com.example.aula.primeiroprojeto.repositories;

import com.example.aula.primeiroprojeto.models.DestinoModel;
import com.example.aula.primeiroprojeto.models.ViagemModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ViagemRepository extends JpaRepository<ViagemModel, Long> {

    List<ViagemModel> findByDestino(DestinoModel destino);

}
