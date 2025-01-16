package com.example.demo.services;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.models.Funcionario;
import com.example.demo.models.RegistroPonto;
import com.example.demo.repositories.RegistroPontoRepository;

@Service
public class RegistroPontoService {
    
    @Autowired
    private RegistroPontoRepository registroPontoRepository;

    @Autowired
    private FuncionarioService funcionarioService;

    public RegistroPonto findById(Long id){
        Optional<RegistroPonto> registroPonto = this.registroPontoRepository.findById(id);
        return registroPonto.orElseThrow(() -> new RuntimeException(
            "Usuário não encontrado! Id " + id + " Tipo: " + Funcionario.class.getName()
        ));
    }

    public List<RegistroPonto> findAllByFuncionarioID(long funcionarioID){
        List<RegistroPonto> registrosPonto = this.registroPontoRepository.findByFuncionario_Id(funcionarioID);
        return registrosPonto;
    }
    
    @Transactional
    public RegistroPonto create(RegistroPonto registroPonto){
        Funcionario funcionario = this.funcionarioService.findById(registroPonto.getFuncionario().getId());
        registroPonto.setId(null);
        registroPonto.setFuncionario(funcionario);
        registroPonto.setHorasTrabalhadas(Duration.between(registroPonto.getHorarioEntrada(), registroPonto.getHorarioSaida()));
        registroPonto = this.registroPontoRepository.save(registroPonto);
        return registroPonto;
    }

    @Transactional
    public RegistroPonto update(RegistroPonto registroPonto){
        RegistroPonto newRegistroPonto = findById(registroPonto.getId());
        newRegistroPonto.setHorarioSaida(registroPonto.getHorarioSaida());
        newRegistroPonto.setHorasTrabalhadas(Duration.between(newRegistroPonto.getHorarioEntrada(), newRegistroPonto.getHorarioSaida()));
        return this.registroPontoRepository.save(newRegistroPonto);
    }

    public void delete(Long id){
        findById(id);
        try {
            this.registroPontoRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException("Não é possível excluir, pois existem entidades relacionadas");
        }
    }
}
