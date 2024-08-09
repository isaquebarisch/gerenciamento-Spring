package com.example.aula.primeiroprojeto.controllers;

import com.example.aula.primeiroprojeto.dtos.ViagemRecordDto;
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
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("gerenciamento/viagem")
public class ViagemController {

    @Autowired
    ViagemRepository viagemRepository;

    @Autowired
    DestinoRepository destinoRepository;


    @GetMapping
    public ResponseEntity<List<ViagemModel>> getAllViagens(){
        return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.findAll());
    }


    @GetMapping("/{id}")
    public ResponseEntity<Object> getViagemById(@PathVariable(value="id") long id ){
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);
        if(viagemO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(viagemO.get());
        }
    }


    @PostMapping
    public ResponseEntity<Object> addViagem(@RequestBody @Valid ViagemRecordDto viagemDto) {
        Optional<DestinoModel> destinoO = destinoRepository.findById(viagemDto.destinoId());
        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não encontrado");
        }

        var viagemModel = new ViagemModel();
        BeanUtils.copyProperties(viagemDto, viagemModel);
        viagemModel.setDestino(destinoO.get());

        return ResponseEntity.status(HttpStatus.CREATED).body(viagemRepository.save(viagemModel));
    }


    @PutMapping("/{id}")
    public ResponseEntity<Object> updateViagem(@PathVariable(value = "id") Long id,
                                               @RequestBody @Valid ViagemRecordDto viagemDto) {
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);
        if (viagemO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        }

        Optional<DestinoModel> destinoO = destinoRepository.findById(viagemDto.destinoId());
        if (destinoO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Destino não encontrado");
        }

        var viagemModel = viagemO.get();
        viagemModel.setNome(viagemDto.nome());
        viagemModel.setValor(viagemDto.valor());
        viagemModel.setDestino(destinoO.get());

        return ResponseEntity.status(HttpStatus.OK).body(viagemRepository.save(viagemModel));
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteViagem(@PathVariable(value="id") long id){
        Optional<ViagemModel> viagemO = viagemRepository.findById(id);
        if(viagemO.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viagem não encontrada");
        } else {
            viagemRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.OK).body("Viagem excluída com sucesso!");
        }
    }
}
