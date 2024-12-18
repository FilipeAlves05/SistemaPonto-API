package com.example.demo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Funcionario;
import com.example.demo.repositories.FuncionarioRepository;

@Service
public class FuncionarioService {
    
    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public Funcionario findById(Long id){
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(id);
        return funcionario.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id " + id + " Tipo: " + Funcionario.class.getName()
        ));
    }

    @Transactional
    public Funcionario create(Funcionario funcionario){
        funcionario.setId(null);
        funcionario = this.funcionarioRepository.save(funcionario);
        return funcionario;
    }

    @Transactional
    public Funcionario update(Funcionario funcionario){
        Funcionario newFuncionario = findById(funcionario.getId());
        newFuncionario.setCargo(funcionario.getCargo());
        newFuncionario.setCpf(funcionario.getCpf());
        newFuncionario.setNome(funcionario.getNome());
        newFuncionario.setDataNascimento(funcionario.getDataNascimento());
        return this.funcionarioRepository.save(newFuncionario);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.funcionarioRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois existem entidades relacionadas");
        }
    }
}
