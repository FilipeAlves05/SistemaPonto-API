package com.example.demo.services;

import com.example.demo.repositories.*;
import com.example.demo.models.*;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {
    //Construtor para interface, pois não pode ser instanciada
    @Autowired
    FuncionarioRepository funcionarioRepository;
    @Autowired
    RegistroPontoRepository registroPontoRepository;

    //Na nossa lógica de negócio implementada não haverá o update, pois são dados únicos

    //Read simples para encontrar o funcionário no banco de dados
    public Funcionario findByID(Integer ID){
        Optional<Funcionario> funcionario = this.funcionarioRepository.findById(ID);
        return funcionario.orElseThrow(() -> new RuntimeException( 
            "O ID " + ID + "Não está cadastrado em nosso database! Sua classe é " + Funcionario.class.getName()));
    }

    //Create simples para adicionar usuário no banco de dados
    @Transactional
    public Funcionario createFuncionario(Funcionario funcionario){
        funcionario.setId(null);
        funcionario = this.funcionarioRepository.save(funcionario);
        this.registroPontoRepository.saveAll(funcionario.getRegistroPontos());
        return funcionario;
    }

    //Update usuário, caso seja necessário em algum momento, por erros de input ou qualquer outra ocasião
    @Transactional
    public Funcionario updateNomeDoFuncionario(Funcionario funcionario){
        Funcionario newFuncionario = findByID(funcionario.getId());
        newFuncionario.setNome(funcionario.getNome());
        return funcionarioRepository.save(newFuncionario);
    }

    @Transactional
    public Funcionario updateCpfDoFuncionario(Funcionario funcionario){
        Funcionario newFuncionario = findByID(funcionario.getId());
        newFuncionario.setCpf(funcionario.getCpf());
        return funcionarioRepository.save(newFuncionario);
    }

    @Transactional
    public Funcionario updateCargoDoFuncionario(Funcionario funcionario){
        Funcionario newFuncionario = findByID(funcionario.getId());
        newFuncionario.setNome(funcionario.getCargo());
        return funcionarioRepository.save(newFuncionario);
    }

    public void deleteFuncionarioByID(Integer ID){
        findByID(ID);
        try {
            this.funcionarioRepository.deleteById(ID);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar, pois existem entidades relacionadas!");
        }
    }
}
