package com.example.exercicio.controllers;

import com.example.exercicio.dtos.LivroDto;
import com.example.exercicio.models.LivroModel;
import com.example.exercicio.repositories.LivroRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("livraria/livros")
public class LivroController {

    @Autowired
    LivroRepository livroRepository;

    @GetMapping
    public ResponseEntity<List<LivroModel>> getAllLivros(){
        return ResponseEntity.status(HttpStatus.OK).body(livroRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getLivroById(@PathVariable(value="id") Long id ){
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if(livroO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(livroO.get());
        }
    }

    @PostMapping
    public ResponseEntity<LivroModel> addLivro(@RequestBody @Valid LivroDto livroDto){
        var livroModel = new LivroModel();
        BeanUtils.copyProperties(livroDto, livroModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(livroRepository.save(livroModel));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteLivro(@PathVariable(value="id") Long id){
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if(livroO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } else {
            livroRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Livro excluído com sucesso!");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateLivro(@PathVariable(value = "id") Long id, @RequestBody @Valid LivroDto livroDto){
        Optional<LivroModel> livroO = livroRepository.findById(id);
        if(livroO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Livro não encontrado");
        } else {
            var livroModel = livroO.get();
            BeanUtils.copyProperties(livroDto, livroModel);
            return ResponseEntity.status(HttpStatus.OK).body(livroRepository.save(livroModel));
        }
    }

    @GetMapping("/{genero}")
    public ResponseEntity<List<LivroModel>> getLivrosByGenero(@PathVariable String genero) {
        List<LivroModel> livros = livroRepository.findByGenero(genero);
        if (livros.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(livros);
        }
    }
}
