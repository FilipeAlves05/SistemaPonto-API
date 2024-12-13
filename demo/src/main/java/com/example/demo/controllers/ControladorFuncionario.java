package com.example.demo.controllers;

import com.example.demo.models.Funcionario;
import com.example.demo.models.Funcionario.CreateFuncionario;
import com.example.demo.models.Funcionario.UpdateFuncionario;
import com.example.demo.services.FuncionarioService;

import jakarta.validation.Valid;

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

@RestController
@RequestMapping("/funcionario")
@Validated
public class ControladorFuncionario {

    @Autowired
    FuncionarioService funcionarioService;

    @PostMapping("/{id}")
    @Validated(CreateFuncionario.class)
    public ResponseEntity<Void> create(@Valid @RequestBody Funcionario funcionario){
        this.funcionarioService.createFuncionario(funcionario);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{id}").buildAndExpand(funcionario.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    //localhost::8080/funcionario/{id}
    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> read(@PathVariable Integer ID){
        Funcionario funcionario = funcionarioService.findByID(ID);
        return ResponseEntity.ok().body(funcionario);
    }

    @PutMapping("/{id}")
    @Validated(UpdateFuncionario.class)
    public ResponseEntity<Void> update(@Valid @RequestBody Funcionario funcionario, @PathVariable Integer ID){
        funcionario.setId(ID);
        this.funcionarioService.updateFuncionario(funcionario);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer ID){
        this.funcionarioService.deleteFuncionarioByID(ID);
        return ResponseEntity.noContent().build();
    }
}
