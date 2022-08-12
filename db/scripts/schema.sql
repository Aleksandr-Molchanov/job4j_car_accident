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