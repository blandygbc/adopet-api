![Alura Challenges](https://github.com/blandygbc/adopet/blob/master/alura_challenges.jpeg?raw=true)
[English Version](https://github.com/blandygbc/adopet#english-version)
# Alura Challenge Back-End 6° Edição

![Adopet Logo](https://github.com/blandygbc/adopet/blob/master/Adopet_logo.png?raw=true)

### Resumo detalhado do Challenge Back-End

Nosso Challenge é dividido em 3 fases, que são distribuídas entre 4 semanas;

Neste Challenge, vamos criar o Back-End a partir da abstração do Front-End ao desenvolver o Adopet, uma plataforma para conectar pessoas que desejam 
adotar animais de estimação a abrigos.

- Semana 01: Criação de API e integração com Banco de Dados.

- Semana 02: Relacionamento entre entidades e engenharia de software.

- Semanas 03 & 04: Autenticação, testes e deploy.

### Tecnologias utilizadas

Este projeto está sendo desenvolvido utilizando Java 17, String Boot 3, MySQL 8 e Docker.

### Endpoints para o Tutor

|                         **Descrição**                        | **Verbo** |  **Endpoint** |          **Retorno**          |
|:------------------------------------------------------------:|:---------:|:-------------:|:-----------------------------:|
| Adicionar um novo tutor                                      | POST      | /tutores      | Json com o tutor cadastrado.  |
| Recuperar todos os tutores                                   | GET       | /tutores      | Json com uma lista de tutores |
| Detalhar um tutor                                            | GET       | /tutores/{id} | Json com o tutor solicitado   |
| Atualizar um tutor enviando  os dados no corpo da requisição | PUT       | /tutores      | Json com o tutor atualizado   |
| Excluir um tutor                                             | DELETE    | /tutores/{id} | Mensagem de sucesso           |

##### English Version

# Alura Backend Challenge 6° Edition

![Adopet Logo](https://github.com/blandygbc/adopet/blob/master/Adopet_logo.png?raw=true)

### Detailed resume of the Backend Challenge.

The Challenge is divided in three phases, that is distributed between 4 weeks.

In this challenge, we're gonna create a backend from the abstraction of the frontend developing Adopet, a platform to connect people that wants to adopt pets to shelters.

- Week 01: API creation and database integration.

- Week 02: Relation between entities and software engineering.

- Week 03 & 04: Authentication, tests and deploy.

### Used technologies

This project are been developed using Java 17, Spring Boot 3, MySQL 8 and Docker.

### Tutor endpoints

|                         **Description**                      | **Verb**  |  **Endpoint** |           **Return**          |
|:------------------------------------------------------------:|:---------:|:-------------:|:-----------------------------:|
| Add new tutor                                                | POST      | /tutores      | Json with created tutor.      |
| Get all tutors                                               | GET       | /tutores      | Json com uma lista de tutores |
| Detail a tutor                                               | GET       | /tutores/{id} | Json with the tutor           |
| Update a tutor sending the data in requisition body          | PUT       | /tutores      | Json with the updated tutor   |
| Delete a tutor                                               | DELETE    | /tutores/{id} | Success Message               |
