package com.example.demo.controllers;

import java.net.URI;

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
import com.example.demo.models.RegistroPonto.CreateRegistroPonto;
import com.example.demo.models.RegistroPonto.UpdateRegistroPonto;
import com.example.demo.services.RegistroPontoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/registroPonto")
@Validated

public class ControladorRegistroPonto{

    @Autowired
    RegistroPontoService registroPontoService;

    @PostMapping("/{id}")
    @Validated(CreateRegistroPonto.class)
    public ResponseEntity<Void> create(@Valid @RequestBody RegistroPonto registroPonto){
        this.registroPontoService.createRegistroPonto(registroPonto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(registroPonto.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegistroPonto> read(@PathVariable Integer ID){
        RegistroPonto registroPonto = registroPontoService.findByID(ID);
        return ResponseEntity.ok().body(registroPonto);
    }

    @PutMapping("/{id}")
    @Validated(UpdateRegistroPonto.class)
    public ResponseEntity<Void> update(@Valid @RequestBody RegistroPonto registroPonto, @PathVariable Integer ID){
        registroPonto.setId(ID);
        this.registroPontoService.updateRegistroPonto(registroPonto);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer ID){
        this.registroPontoService.deleteById(ID);
        return ResponseEntity.noContent().build();
    }



}