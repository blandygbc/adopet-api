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
- `Maven`: [Maven](https://maven.apache.org/download.cgi?.).
- `Docker`: [Docker](https://www.docker.com/).
- `IDE`: Uma IDE ou editor de texto de sua escolha, eu utilizo o [VS CODE](https://code.visualstudio.com/Download).

### Instruções de utilização

1. Primeiro clone ou baixe o projeto.
2. Após importar o projeto na IDE ou editor de texto, rode o comando abaixo na pasta raíz do projeto para baixar as dependências:

```bash

mvn install

```

3. Após baixar as dependências crie um arquivo chamado `init-script.sql` para criar um usuário que irá manipular o banco seguindo o modelo abaixo:

```SQL

CREATE USER 'SEU_USUARIO'@'%' IDENTIFIED BY 'SUA_SENHA';
GRANT ALL ON adopet.* TO 'SEU_USUARIO'@'%';
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
FLYWAY_URL=jdbc:mysql://IP_DO_CONAINER_DOCKER:PORTA_UTILIZADA_NO_MYSQL_TCP_PORT/adopet?createDatabaseIfNotExist=true
FLYWAY_USER=USUARIO_CADASTRADO_NO_INIT_SCRIPT_SQL
FLYWAY_PASSWORD=SENHA_CADASTRADA_NO_INIT_SCRIPT_SQL

```

5. Agora crie o container docker do banco com o comando abaixo:
```BASH

docker compose --env-file .env up -d

```

6. Verifique se o container foi startado corretamente com o comando abaixo:
```BASH

docker ps

```
7. Pronto, uma vez iniciado o container é só iniciar o Spring Boot e testar os endpoints.

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

To clone and use this project install the tools below:

- `Java 17`: [Adoptium](https://adoptium.net/temurin/releases/).
- `Maven`: [Maven](https://maven.apache.org/download.cgi?.).
- `Docker`: [Docker](https://www.docker.com/).
- `IDE`: Choose an IDE or Text Editor of your choice, I use [VS CODE](https://code.visualstudio.com/Download).

## How to start the project

1. First clone or download the project.
2. After importing the project in the IDE or text editor run the command below in the root folder to download the dependencies:

```bash

mvn install

```

3. After downloading the dependencies create a file `init-script.sql` to create a user for manipulate the database with following content:

```SQL

CREATE USER 'YOUR_USER'@'%' IDENTIFIED BY 'YOUR_PASSWORD';
GRANT ALL ON adopet.* TO 'YOUR_USER'@'%';
FLUSH PRIVILEGES;

```

4. create a `.env` file in the root folder of the project with the following:

```ENV

# Docker variables
DB_DATA_LOCATION="Localization to database mounting point"
INIT_SCRIPT_LOCATION="Localization to init-script.sql file"
MYSQL_ROOT_PASSWORD="Strong random password higher than 14 characters"
MYSQL_TCP_PORT="MySQL port of your choice"

# Project variables. Use the same as docker.
# Usually docker's IPs are 172.17.0.2
# *** In Windows leave as localhost ***
FLYWAY_URL=jdbc:mysql://DOCKER_CONAINER_IP:USED_PORT_IN_MYSQL_TCP_PORT/adopet?createDatabaseIfNotExist=true
FLYWAY_USER="User created in init-script.sql"
FLYWAY_PASSWORD="Password created in init-script.sql"

```

5. Now get docker container up with the command below:
```BASH

docker compose --env-file .env up -d

```

6. Check if was started correctly:
```BASH

docker ps

```

7. Done, once the container has started, just start the Spring Boot and test the endpoints.

### Tutor endpoints

|                         **Description**                      | **Verb**  |  **Endpoint** |           **Return**          |
|:------------------------------------------------------------:|:---------:|:-------------:|:-----------------------------:|
| Add new tutor                                                | POST      | /tutores      | Json with created tutor.      |
| Get all tutors                                               | GET       | /tutores      | Json com uma lista de tutores |
| Detail a tutor                                               | GET       | /tutores/{id} | Json with the tutor           |
| Update a tutor sending the data in requisition body          | PUT       | /tutores      | Json with the updated tutor   |
| Delete a tutor                                               | DELETE    | /tutores/{id} | Success Message               |
