![Alura Challenges](https://github.com/blandygbc/adopet/blob/master/repo_resources/alura_challenges.jpeg?raw=true)

[🇺🇸 English Version](#-english-version)

# Alura Challenge Back-End 6° Edição

![Adopet Logo](https://github.com/blandygbc/adopet/blob/master/repo_resources/Adopet_logo.png?raw=true)

## Resumo detalhado do Challenge Back-End

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
JWT_SECRET=SUA_SENHA_SUPER_SECRETA_PRO_TOKEN

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

8. Para testar os endpoints digite no navegador `http://localhost:8080/swagger-ui/index.html` e irá abrir a tela abaixo:

![Tela do Swagger](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/1_adopet_swagger_ui.jpeg?raw=true)

**OBS: Os endpoints que tem um cadeado exigem um token que é dado ao logar no sistema.**

09. Para testa-los no `swagger` siga os seguintes passos, primeiro crie um abrigo ou tutor para poder logar no sistema, aqui vamos criar um abrigo no endpoint abaixo:

![Endpoint do shelter](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/2_adopet_swagger_shelter.jpeg?raw=true)

10. Clicando em `try out` você poderá enviar uma requisição com as informações de cadastro seguindo o modelo demonstrado no próprio endpoint.

11. Com o abrigo criado vá no endpoint de login e faça uma requisição e na resposta receberá um token:

![Endpoint do login](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/3_adopet_swagger_login.jpeg?raw=true)

12. Com o token em mãos vá para o topo da tela e clique no botão `Authorize` que irá abrir um modal com o campo para incluí-lo:

![Modal do Authorize](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/4_adopet_swagger_bearer.jpeg?raw=true)

13. Após incluir o token no campo clique em `Authorize` e pronto! Agora você poderá testar todos os endpoints.

---

#### 🇺🇸 English Version

# Alura Backend Challenge 6° Edition

![Adopet Logo](https://github.com/blandygbc/adopet/blob/master/repo_resources/Adopet_logo.png?raw=true)

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

## Getting Started

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
JWT_SECRET="Your super secret JWT password"

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

8. To test the endpoints enter this address in the browser `http://localhost:8080/swagger-ui/index.html` then will show up on the screen below:

![Swagger UI](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/1_adopet_swagger_ui.jpeg?raw=true)

**PS: The endpoints that have a locker demand a token that is given when you login into the system.**

09. To test them on `swagger` follow this, first sign up as a shelter or a tutor to log into the system, here we're gonna sign up as a shelter in the endpoint below:

![Shelter endpoint](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/2_adopet_swagger_shelter.jpeg?raw=true)

10. Click on `try out` to send a requisition with info that is necessary to create one, as the model shown in the endpoint.

11. With the shelter created, then go to the login endpoint and do a requisition with the registered info and you'll receive a token:

![Login endpoint](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/3_adopet_swagger_login.jpeg?raw=true)

12. With the token in hands go to the top of screen then click on the `Authorize` button that will show a modal with a field to fill it:

![Authorize modal](https://github.com/blandygbc/adopet-api/blob/better-docs/repo_resources/4_adopet_swagger_bearer.jpeg?raw=true)

13. After paste the token in the field click on the `Authorize` button and thats it! Now you can test all the endpoints without any problems.
