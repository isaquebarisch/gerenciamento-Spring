package com.example.exercicio.repositories;

import com.example.exercicio.models.LivroModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LivroRepository extends JpaRepository<LivroModel, Long> {
    List<LivroModel> findByGenero(String genero);
}
