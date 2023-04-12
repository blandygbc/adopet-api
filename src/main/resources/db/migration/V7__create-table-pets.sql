CREATE TABLE pets(
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    personality VARCHAR(255) NOT NULL,
    age VARCHAR(255) NOT NULL,
    image VARCHAR(255),
    species VARCHAR(255) NOT NULL,
    size VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    shelter_id BIGINT NOT NULL,
    CONSTRAINT FOREIGN KEY(shelter_id) REFERENCES shelters(id),
    primary key(id)
)