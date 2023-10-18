CREATE SCHEMA IF NOT EXISTS `wanted_job` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE IF NOT EXISTS companys
(
    id  BIGINT PRIMARY KEY AUTO_INCREMENT,
    name    varchar(255) NOT NULL,
    nation  varchar(255) NOT NULL,
    region  varchar(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS users
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    name   varchar(255) NOT NULL,
    email  varchar(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS jobs
(
    id   BIGINT PRIMARY KEY AUTO_INCREMENT,
    company_id BIGINT NOT NULL,
    position varchar(255) NOT NULL,
    compensation INTEGER,
    content TEXT NOT NULL,
    skill varchar(255),

    FOREIGN KEY (company_id) REFERENCES companys (id)
);