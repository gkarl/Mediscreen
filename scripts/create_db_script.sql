drop database if exists patient;
create database patient;
use patient;

CREATE TABLE patient
(
    id INT AUTO_INCREMENT NOT NULL,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birthday VARCHAR(10)        NOT NULL,
    sex CHAR(10) NOT NULL,
    address VARCHAR(100) NOT NULL,
    phone VARCHAR(10) NOT NULL,
    PRIMARY KEY (id)
    )
    ENGINE = InnoDB;