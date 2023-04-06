CREATE TABLE shelters(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    phone VARCHAR(255) NOT NULL,
    city VARCHAR(255) NOT NULL,
    about VARCHAR(255),
    image VARCHAR(255),
    role_id BIGINT NOT NULL,
    CONSTRAINT FOREIGN KEY(role_id) REFERENCES roles(id),
    primary key(id)
)