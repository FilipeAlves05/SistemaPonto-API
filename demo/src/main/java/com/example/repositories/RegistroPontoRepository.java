package com.example.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.RegistroPonto;

@Repository
public interface RegistroPontoRepository extends JpaRepository<RegistroPonto, Integer> {
    List<RegistroPonto> findByFuncionario_Id(Integer id);
}
