CREATE TABLE tutors(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    phone VARCHAR(255),
    city VARCHAR(255),
    state VARCHAR(255),
    about VARCHAR(255),
    image VARCHAR(255),
    user_id BIGINT NOT NULL,
    CONSTRAINT FOREIGN KEY(user_id) REFERENCES users(id),
    primary key(id)
)