/*package com.example.demo.models;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class DatabaseConfig {
    
    @Primary
    @Bean
    public DataSource data(){
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        //ENDEREÇO DO BANCO DE DADOS
        dataSourceBuilder.url("jdbc:mysql://localhost:3306/sistemaPonto?createDatabaseIfNotExist=true");
        //NOME DE USUARIO
        dataSourceBuilder.username("root");
        //SENHA
        dataSourceBuilder.password("tgcC@#%39354ZKm");
        return dataSourceBuilder.build();
    }
}*/
