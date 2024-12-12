package com.example.demo.services;
import com.example.demo.models.Funcionario;
import com.example.demo.models.RegistroPonto;
import com.example.demo.repositories.RegistroPontoRepository;
import com.example.demo.services.FuncionarioService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegistroPontoService {

    @Autowired
    RegistroPontoRepository registroPontoRepository;

    @Autowired
    FuncionarioService funcionarioService;

    public RegistroPonto findByID(Integer ID){
        Optional<RegistroPonto> registroPonto = this.registroPontoRepository.findById(ID);
        return registroPonto.orElseThrow(() -> new RuntimeException( 
            "O ID " + ID + "Não está cadastrado em nosso database! Sua classe é " + Funcionario.class.getName()));
    }

    @Transactional
    public RegistroPonto createRegistroPonto(RegistroPonto registroPonto){
        Funcionario funcionario = this.funcionarioService.findByID(registroPonto.getFuncionario().getId());
        registroPonto.setFuncionario(funcionario);
        registroPonto = registroPontoRepository.save(registroPonto);
        return registroPonto;
    }

    /*FIXME: Precisamos verificar na classe RegistroPonto a forma como está sendo armazenado, eu acho que devemos armazenar a hora 
    de entrada/saída, além de fazer a subtração de uma pela outra para sabermos a quantidade de horas que o funcionário trabalhou no dia.
    Assim conseguiremos saber mais ao certo o que deve ser atualizado nessa parte do código.

    @Transactional
    public RegistroPonto updateRegistroPonto(){

    }*/

    public void deleteById(Integer ID){
        findByID(ID);
        try {
            registroPontoRepository.deleteById(ID);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível deletar, pois existem entidades relacionadas!");
        }
    }
    
}
