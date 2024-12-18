package com.example.demo.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.models.RegistroPonto;
import com.example.demo.services.FuncionarioService;
import com.example.demo.services.RegistroPontoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registro-ponto")
@Validated
public class RegistroPontoController {
    
    @Autowired
    private RegistroPontoService registroPontoService;


    @GetMapping("/{id}")
    public ResponseEntity<RegistroPonto> findById(@PathVariable Long id){
        RegistroPonto registroPonto = this.registroPontoService.findById(id);
        return ResponseEntity.ok(registroPonto);
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<RegistroPonto>> findAllByUserID(@PathVariable Long userId){
        List<RegistroPonto> registroPontos = this.registroPontoService.findAllByFuncionarioID(userId);
        return ResponseEntity.ok(registroPontos);
    }


    @PostMapping
    @Validated
    public ResponseEntity<Void> create (@Valid @RequestBody RegistroPonto registroPonto){
        this.registroPontoService.create(registroPonto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/id").buildAndExpand(registroPonto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated
    public ResponseEntity<Void> update(@Valid @RequestBody RegistroPonto registroPonto, @PathVariable Long id){
        registroPonto.setId(id);
        this.registroPontoService.update(registroPonto);
        return ResponseEntity.noContent().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.registroPontoService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
