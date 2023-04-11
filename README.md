![Alura Challenges](https://github.com/blandygbc/adopet/blob/master/alura_challenges.jpeg?raw=true)

[English Version](#english-version)

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

Este projeto está sendo desenvolvido utilizando Java 17, String Boot 3, Maven, MySQL 8 e Docker.

Para clonar e usar o projeto instale as ferramentas abaixo:

- `Java 17`: [Adoptium](https://adoptium.net/temurin/releases/).
- `Docker`: [Docker](https://www.docker.com/).
- `IDE`: Uma IDE ou editor de texto de sua escolha, eu utilizo o [VS CODE](https://code.visualstudio.com/Download).

### Instruções de utilização

1. Primeiro clone ou baixe o projeto.
2. Após abrir o projeto na IDE ou editor de sua escolha rode o comando abaixo para baixar as dependências:

No windows:
```bash

./mvnw install

```

No linux:
```

mvnw install

```

3. Após baixar as dependências crie um arquivo chamado `init-init-script.sql` para criar um usuário que irá manipular o banco seguindo o modelo abaixo:

```SQL

CREATE USER 'SEU_USUARIO'@'localhost' IDENTIFIED BY 'SUA_SENHA';
GRANT ALL ON adopet.* TO 'SEU_USUARIO'@'localhost';
FLUSH PRIVILEGES;

```

4.  crie um arquivo `.env` na raíz do projeto com as seguintes informações:

```ENV

# Variáveis para o docker
DB_DATA_LOCATION=LOCALIZAÇÃO_DO_PONTO_DE_MONTAGEM_DO_BANCO_NO_PC
INIT_SCRIPT_LOCATION=LOCALIZAÇÃO_DO_INIT_SCRIPT_SQL
MYSQL_ROOT_PASSWORD=USE_UMA_SENHA_FORTE_ALEATÓRIA_MAIOR_QUE_14_CARACTERES
MYSQL_TCP_PORT=PORTA_QUE_SERÁ_UTILIZADA_NO_MYSQL

# Variáveis para o projeto. Use as mesmas informações do docker.
# Geralmente os IPs do docker são 172.17.0.2
# *** No Windows deixe como localhost ***
FLYWAY_URL=jdbc:mysql://IP_DO_CONAINER_DOCKER:PORTA_UTILIZADA_NO_MYSQL_TCP_PORT/adopet?
FLYWAY_USER=USUARIO_CADASTRADO_NO_INIT_SCRIPT_SQL
FLYWAY_PASSWORD=SENHA_CADASTRADA_NO_INIT_SCRIPT_SQL

```

5. Trabalho em progresso.

### Endpoints para o Tutor

|                         **Descrição**                        | **Verbo** |  **Endpoint** |          **Retorno**          |
|:------------------------------------------------------------:|:---------:|:-------------:|:-----------------------------:|
| Adicionar um novo tutor                                      | POST      | /tutores      | Json com o tutor cadastrado.  |
| Recuperar todos os tutores                                   | GET       | /tutores      | Json com uma lista de tutores |
| Detalhar um tutor                                            | GET       | /tutores/{id} | Json com o tutor solicitado   |
| Atualizar um tutor enviando  os dados no corpo da requisição | PUT       | /tutores      | Json com o tutor atualizado   |
| Excluir um tutor                                             | DELETE    | /tutores/{id} | Mensagem de sucesso           |

#### English Version

# Alura Backend Challenge 6° Edition

![Adopet Logo](https://github.com/blandygbc/adopet/blob/master/Adopet_logo.png?raw=true)

### Detailed resume of the Backend Challenge.

The Challenge is divided in three phases, that is distributed between 4 weeks.

In this challenge, we're gonna create a backend from the abstraction of the frontend developing Adopet, a platform to connect people that wants to adopt pets to shelters.

- Week 01: API creation and database integration.

- Week 02: Relation between entities and software engineering.

- Week 03 & 04: Authentication, tests and deploy.

### Used technologies

This project are been developed using Java 17, Spring Boot 3, Maven, MySQL 8 and Docker.

### *Work In progress*

### Tutor endpoints

|                         **Description**                      | **Verb**  |  **Endpoint** |           **Return**          |
|:------------------------------------------------------------:|:---------:|:-------------:|:-----------------------------:|
| Add new tutor                                                | POST      | /tutores      | Json with created tutor.      |
| Get all tutors                                               | GET       | /tutores      | Json com uma lista de tutores |
| Detail a tutor                                               | GET       | /tutores/{id} | Json with the tutor           |
| Update a tutor sending the data in requisition body          | PUT       | /tutores      | Json with the updated tutor   |
| Delete a tutor                                               | DELETE    | /tutores/{id} | Success Message               |
