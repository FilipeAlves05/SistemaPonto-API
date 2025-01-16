package com.example.demo.models;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.time.Duration;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = RegistroPonto.TABLE_NAME)
public class RegistroPonto {
    public static final String TABLE_NAME = "registro_ponto";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", nullable = false, updatable = false)
    private Funcionario funcionario;

    @Column(name = "data")
    private LocalDate dataPonto;

    @Column(name = "horario_entrada")
    private LocalTime horarioEntrada;

    @Column(name = "horario_saida")
    private LocalTime horarioSaida;

    @Column(name = "horas_trabalhadas")
    private Duration horasTrabalhadas;

    public RegistroPonto() {}

    /* Isso acontece porque no momento em que o funcionário bater o ponto, ele ainda não tem horário de saída definido,
    para não deixar como nulo, preferi trabalhar com ele sendo o horário de entrada no momento em que o ponto é batido*/
    public RegistroPonto(Long id, Funcionario funcionario, LocalDate dataPonto, LocalTime horarioEntrada, LocalTime horarioSaida) {
        this.id = id;
        this.funcionario = funcionario;
        this.dataPonto = dataPonto;
        this.horarioEntrada = horarioEntrada;
        this.horarioSaida = horarioSaida;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public LocalDate getDataPonto() {
        return dataPonto;
    }

    public void setDataPonto(LocalDate dataPonto) {
        this.dataPonto = dataPonto;
    }

    public LocalTime getHorarioEntrada() {
        return horarioEntrada;
    }

    public void setHorarioEntrada(LocalTime horarioEntrada) {
        this.horarioEntrada = horarioEntrada;
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



        @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof RegistroPonto))
            return false;
        
        RegistroPonto other = (RegistroPonto) obj;

        if(this.id == null)
            if(other.id != null)
                return false;
            else if(!this.id.equals(other.id))
                return false;
        
        return Objects.equals(this.id, other.id) && Objects.equals(this.dataPonto, other.dataPonto) &&
            Objects.equals(this.horarioEntrada, other.horarioEntrada) && Objects.equals(this.horarioSaida, other.horarioSaida) 
            && Objects.equals(this.horasTrabalhadas, other.horasTrabalhadas);
    }
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
    
}




}
