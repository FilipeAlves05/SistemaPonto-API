package com.example.demo.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// Trata a classe como uma tabela no banco de dados
@Entity
@Table(name = RegistroPonto.TABLE_NAME)

public class RegistroPonto {
    public static final String TABLE_NAME = "registroPonto";

    public interface CreateRegistroPonto{ 

    }
    public interface UpdateRegistroPonto{

    }
    //Atributos de RegistroPonto (gerando colunas para cada atributo e tratando "id" como único)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @NotEmpty(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @Column(name = "id" , unique = true)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false)
    private Funcionario funcionario;

    @Column(name = "data", nullable = false)
    @NotNull(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @NotEmpty(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    private LocalDate date;

    @Column(name = "horario_entrada", nullable = false)
    @NotNull(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @NotEmpty(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    private LocalTime horarioEntrada;

    @Column(name = "horario_saída", nullable = false)
    @NotNull(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @NotEmpty(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    private LocalTime horarioSaida;

    @Column(name = "horas_trabalhadas", nullable = false)
    @NotNull(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    @NotEmpty(groups = {CreateRegistroPonto.class, UpdateRegistroPonto.class})
    private Duration horasTrabalhadas;

    //Construtor Vazio (Necessário para o SpringBoot)
    public RegistroPonto(){
    }
    

    //Construtor de Funcionario
    public RegistroPonto(Integer id, Funcionario funcionario, LocalDate date, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.id = id;
        this.funcionario = funcionario;
        this.date = date;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
        this.horasTrabalhadas = Duration.between(this.horarioEntrada, this.horarioSaida);
    }


    //Getters e Setters

    
    public Integer getId() {
        return id;
    }

    public LocalTime getHorarioSaida() {
        return horarioSaida;
    }


    public void setHorarioSaida(LocalTime horarioSaida) {
        this.horarioSaida = horarioSaida;
    }


    public Duration getHorasTrabalhadas() {
        return horasTrabalhadas;
    }


    public void setHorasTrabalhadas(Duration horasTrabalhadas) {
        this.horasTrabalhadas = horasTrabalhadas;
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

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horario) {
        this.horarioEntrada = horario;
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
        return Objects.equals(this.horarioEntrada, other.horarioEntrada);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}

