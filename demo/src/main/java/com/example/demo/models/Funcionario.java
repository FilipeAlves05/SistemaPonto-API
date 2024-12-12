package com.example.demo.models;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

// Trata a classe como uma tabela no banco de dados
@Entity
@Table(name = Funcionario.TABLE_NAME)

public class Funcionario implements Serializable {
    public static final String TABLE_NAME = "funcionario";

    //Atributos de Funcionario (gerando colunas para cada atributo e tratando "id" como único)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id" , unique = true)
    private Integer id;

    @Column(name = "cpf" , unique = true , nullable = false)
    @Size(min = 11 , max = 11)
    private String cpf;

    @Column(name = "nome" , nullable = false , length = 100)
    @Size(min = 2)
    private String nome;

    @Column(name = "cargo" , nullable = false , length = 100)
    @Size(min = 2)
    private String cargo;

    @OneToMany (mappedBy="funcionario")
    private List<RegistroPonto> registroPontos = new ArrayList<RegistroPonto>();

    //Construtor Vazio (Necessário para o SpringBoot)
    public Funcionario() {
    }

    //Construtor de Funcionario
    public Funcionario(Integer id, @Size(min = 14 , max = 14) String cpf , @Size(min = 2) String nome, @Size(min = 2) String cargo) {
        this.id = id;
        this.cpf = cpf;
        this.nome = nome;
        this.cargo = cargo;
    }

    //Getters e Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public List<RegistroPonto> getRegistroPontos() {
        return registroPontos;
    }

    public void setRegistroPontos(List<RegistroPonto> registroPontos) {
        this.registroPontos = registroPontos;
    }


    //Equals e hashCode
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
        final Funcionario other = (Funcionario) obj;
        if (!Objects.equals(this.cpf, other.cpf)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.cargo, other.cargo)) {
            return false;
        }
        return Objects.equals(this.id, other.id);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }
}
