CREATE TABLE IF NOT EXISTS person(
                                    id     BIGINT AUTO_INCREMENT PRIMARY KEY,
                                    name      VARCHAR(255) NOT NULL,
    surname   VARCHAR(255) NOT NULL,
    birthdate DATE         NOT NULL
    );