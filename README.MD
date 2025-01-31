# SISTEMA DE PONTO - API

## Descrição do projeto

- Esse projeto foi feito como atividade avaliativa da disciplina LP2 (Linguagem de Programação 2), no curso de Bacharelado em Tecnologia da Informação da UFRN, na qual tivemos que criar uma API de sistema de ponto para ser utilizada em alguma empresa qualquer. Nesse projeto, utilizamos Spring Boot com Gradle para gerenciar as dependências e MySQL como banco de dados. Com esse projeto, tivemos a capacidade de aprender na prática como que funciona um sistema web e aplicar conceitos base de orientação a objetos (OOP). Além disso tudo, tivemos a oportunidade de trabalhar pensando em conceitos voltados para a área de negócios do desenvolvimento de aplicações web e assim melhorar o nosso código como um todo para dar uma solução eficiente para uma empresa registrar os pontos de seus funcionários.

## Participantes do projeto


**Filipe de Medeiros Alves**
- Responsável pela criação das classes Funcionário e Registro Ponto e suas integrações ao banco de dados; 


**Lucas Rafael Arruda de Amorim**
- Responsável pela criação do Front-End da API e sua relação com os controllers;


**Rafael Araújo de Andrade**
- Responsável por implementar a parte de services do sistema, organizando um CRUD para fazer alterações dentro do banco de dados tanto do lado do servidor(services) como do lado do Frontend (controllers).
- <a href="https://github.com/leafaraujo">Github</a>

## Tecnologias utilizadas:

<div style = "display: inline block"><br/>
  <img align="center" alt="html5" src="https://img.shields.io/badge/Spring-6DB33F?style=for-the-badge&logo=spring&logoColor=white"/>
  <img align="center" alt="html5" src="https://img.shields.io/badge/MySQL-00000F?style=for-the-badge&logo=mysql&logoColor=white"/>
  <img align="center" alt="html5" src="https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white"/>
</div>

## Classes

- Funcionario
    - Classe destinada a definir de um funcionário dentro do programa, bem como sua implementação ao banco de dados; 
- Registro ponto
    - Classe destinada a definir de um  registro de ponto dentro do programa, bem como sua implementação ao banco de dados; 
- Services
    - Serve para pegar a requisição do Controller e buscar no banco de dados a informação requisitada por eles, essa classe conta com algumas verificações para deletar ou criar, adicionar ou deletar um usuário, sempre verificando se ele já existe no banco de dados ou não.
- Controllers
    - Serve para comunicação direta com o frontend/serviço que vai consumir a API, ele faz a ligação entre o banco de dados e o frontednd.

