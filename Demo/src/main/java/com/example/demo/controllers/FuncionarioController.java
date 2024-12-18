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

import com.example.demo.models.Funcionario;
import com.example.demo.models.Funcionario.CreateFuncionario;
import com.example.demo.models.Funcionario.UpdateFuncionario;
import com.example.demo.services.FuncionarioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/funcionario")
@Validated
public class FuncionarioController {
    
    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> findById(@PathVariable Long id){
        Funcionario funcionario = this.funcionarioService.findById(id);
        return ResponseEntity.ok().body(funcionario);
    }

    @PostMapping
    @Validated(CreateFuncionario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Funcionario funcionario){
        this.funcionarioService.create(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(UpdateFuncionario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Funcionario funcionario, @PathVariable Long id){
        funcionario.setId(id);
        this.funcionarioService.update(funcionario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        this.funcionarioService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
