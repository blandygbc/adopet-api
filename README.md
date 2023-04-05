![Alura Challenges](https://github.com/blandygbc/adopet/blob/master/alura_challenges.jpeg?raw=true)
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
