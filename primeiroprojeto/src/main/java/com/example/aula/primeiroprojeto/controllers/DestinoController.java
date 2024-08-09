package com.example.aula.primeiroprojeto.controllers;

import com.example.aula.primeiroprojeto.dtos.DestinoRecordDto;
import com.example.aula.primeiroprojeto.models.DestinoModel;
import com.example.aula.primeiroprojeto.models.ViagemModel;
import com.example.aula.primeiroprojeto.repositories.DestinoRepository;
import com.example.aula.primeiroprojeto.repositories.ViagemRepository;
import jakarta.validation.Valid;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("gerenciamento/destino")
public class DestinoController {
    @Autowired
    DestinoRepository destinoRepository;

    @Autowired
    ViagemRepository viagemRepository;


    @GetMapping
    public ResponseEntity<List<DestinoModel>> getAllDestinos(){
        return ResponseEntity.status(HttpStatus.OK).body(destinoRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getDestinoById(@PathVariable(value="id") long id ){
        DestinoModel destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        return ResponseEntity.status(HttpStatus.OK).body(destino);
    }


    @PostMapping
    public ResponseEntity<DestinoModel> addDestino(@RequestBody @Valid DestinoRecordDto destinoDto){
        var destinoModel =  new DestinoModel();
        BeanUtils.copyProperties(destinoDto, destinoModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(destinoRepository.save(destinoModel));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateDestino(@PathVariable(value="id") long id,
                                                @RequestBody @Valid DestinoRecordDto destinoDto) {
        DestinoModel destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        BeanUtils.copyProperties(destinoDto, destino);
        return ResponseEntity.status(HttpStatus.OK).body(destinoRepository.save(destino));
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteDestino(@PathVariable(value="id") long id) {
        DestinoModel destino = destinoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        destinoRepository.delete(destino);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{destinoId}/viagens")
    public ResponseEntity<List<ViagemModel>> getViagensByDestino(@PathVariable(value="destinoId") long destinoId) {
        DestinoModel destino = destinoRepository.findById(destinoId)
                .orElseThrow(() -> new RuntimeException("Destino não encontrado"));

        return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.findByDestino(destino));
    }
}
