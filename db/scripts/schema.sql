CREATE TABLE IF NOT EXISTS rule
(
    id SERIAL PRIMARY KEY,
    name VARCHAR (2000)
);

INSERT INTO rule(name) VALUES ('Статья. 1');
INSERT INTO rule(name) VALUES ('Статья. 2');
INSERT INTO rule(name) VALUES ('Статья. 3');

CREATE TABLE IF NOT EXISTS accident_type
(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

INSERT INTO accident_type(name) VALUES ('Две машины');
INSERT INTO accident_type(name) VALUES ('Машина и человек');
INSERT INTO accident_type(name) VALUES ('Машина и велосипед');

CREATE TABLE IF NOT EXISTS accident
(
    id SERIAL PRIMARY KEY,
    name VARCHAR (2000),
    text TEXT,
    address VARCHAR (2000),
    accident_type_id INT NOT NULL REFERENCES accident_type(id)
);

CREATE TABLE IF NOT EXISTS accident_rule
(
    id SERIAL PRIMARY KEY,
    accident_id INT REFERENCES accident(id),
    rule_id INT REFERENCES rule(id)
);

CREATE TABLE users
(
    username VARCHAR(50) NOT NULL,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    PRIMARY KEY (username)
);

CREATE TABLE IF NOT EXISTS  authorities
(
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);