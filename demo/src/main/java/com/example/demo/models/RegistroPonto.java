package com.example.demo.models;

import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

// Trata a classe como uma tabela no banco de dados
@Entity
@Table(name = RegistroPonto.TABLE_NAME)

public class RegistroPonto {
    public static final String TABLE_NAME = "registroPonto";

    //Atributos de RegistroPonto (gerando colunas para cada atributo e tratando "id" como único)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column(name = "horario", nullable = false)
    private LocalDateTime horario;


    //Construtor Vazio (Necessário para o SpringBoot)
    public RegistroPonto(){
    }
    

    //Construtor de Funcionario
    public RegistroPonto(Integer id, Funcionario funcionario, LocalDateTime horario) {
        this.id = id;
        this.funcionario = funcionario;
        this.horario = horario;
    }


    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDateTime getHorario() {
        return horario;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }


    //Equals e HashCode 
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final RegistroPonto other = (RegistroPonto) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.funcionario, other.funcionario)) {
            return false;
        }
        return Objects.equals(this.horario, other.horario);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
