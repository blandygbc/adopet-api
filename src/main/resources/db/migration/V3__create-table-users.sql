CREATE TABLE users(
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT FOREIGN KEY(role_id) REFERENCES roles(id),
    primary key(id)
)