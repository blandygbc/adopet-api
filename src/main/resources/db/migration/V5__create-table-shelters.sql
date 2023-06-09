CREATE TABLE shelters(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    image VARCHAR(255),
    about VARCHAR(255),
    city VARCHAR(255) NOT NULL,
    state VARCHAR(255),
    phone VARCHAR(255) NOT NULL,
    user_id BIGINT NOT NULL,
    CONSTRAINT FOREIGN KEY(user_id) REFERENCES users(id),
    primary key(id)
)