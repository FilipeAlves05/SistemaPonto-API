package com.example.demo.models;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Funcionario.TABLE_NAME)
public class Funcionario {

    public interface CreateFuncionario{}
    public interface UpdateFuncionario{}


    public static final String TABLE_NAME = "funcionario";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "nome", length = 100, nullable = false, unique = false)
    @NotNull(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @NotEmpty(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @Size(groups = {CreateFuncionario.class, UpdateFuncionario.class},min = 10, max = 100)
    private String nome;

    @Column(name = "cpf", length = 11, nullable = false, unique = true)
    @NotNull(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @NotEmpty(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @Size(groups = {CreateFuncionario.class, UpdateFuncionario.class},min = 11, max = 11)
    private String cpf;

    @Column(name = "data_nascimento", length = 10, nullable = false, unique = false)
    @NotNull(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @NotEmpty(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @Size(groups = {CreateFuncionario.class, UpdateFuncionario.class},min = 10, max = 10)
    private String dataNascimento;

    @Column(name = "cargo", length = 20, nullable = false, unique = true)
    @NotNull(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @NotEmpty(groups = {CreateFuncionario.class, UpdateFuncionario.class})
    @Size(groups = {CreateFuncionario.class, UpdateFuncionario.class}, min = 3, max = 20)
    private String cargo;

    @OneToMany(mappedBy = "funcionario")
    private List<RegistroPonto> registroPonto = new ArrayList<RegistroPonto>();

    public Funcionario() {}

    public Funcionario(Long id, String nome, String cpf, String cargo, String dataNascimento){
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.cargo = cargo;
        this.dataNascimento = dataNascimento;
    }


    
    public String getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(String dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    @JsonIgnore
    public List<RegistroPonto> getRegistroPonto() {
        return registroPonto;
    }

    public void setRegistroPonto(List<RegistroPonto> registroPonto) {
        this.registroPonto = registroPonto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    @Override
    public boolean equals(Object obj){
        if(obj == this)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof Funcionario))
            return false;
        
        Funcionario other = (Funcionario) obj;

        if(this.id == null)
            if(other.id != null)
                return false;
            else if(!this.id.equals(other.id))
                return false;
        
        return Objects.equals(this.id, other.id) && Objects.equals(this.nome, other.nome) &&
            Objects.equals(this.cpf, other.cpf) && Objects.equals(this.cargo, other.cargo);
    }
    
    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((this.id == null) ? 0 : this.id.hashCode());
        return result;
    }

}

