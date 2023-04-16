CREATE TABLE adoptions(
    id BIGINT NOT NULL AUTO_INCREMENT,
    pet_id BIGINT NOT NULL,
    tutor_id BIGINT NOT NULL,
    date TIMESTAMP NOT NULL,
    CONSTRAINT FOREIGN KEY(pet_id) REFERENCES pets(id),
    CONSTRAINT FOREIGN KEY(tutor_id) REFERENCES tutors(id),
    primary key(id)
)