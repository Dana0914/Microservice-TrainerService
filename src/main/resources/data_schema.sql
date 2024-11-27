CREATE TABLE trainer (
    id BIGINT PRIMARY KEY NOT NULL,
    username VARCHAR(55) UNIQUE NOT NULL,
    firstname VARCHAR(55) NOT NULL,
    lastname VARCHAR(55) NOT NULL,
    status ENUM('ACTIVE', 'INACTIVE') CHECK (status IN ('ACTIVE', 'INACTIVE'))

);

CREATE TABLE training (
    id BIGINT PRIMARY KEY NOT NULL,
    trainer_id INT8 NOT NULL,
    training_date TIMESTAMP DEFAULT CURRENT_DATE,
    training_duration INT4 NOT NULL,
    action_type ENUM('ADD', 'DELETE') CHECK (action_type in ('ADD', 'DELETE')),
    FOREIGN KEY (trainer_id) REFERENCES trainer(id)

);