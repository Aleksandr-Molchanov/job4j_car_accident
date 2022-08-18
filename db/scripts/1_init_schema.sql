CREATE TABLE IF NOT EXISTS rule
(
    id SERIAL PRIMARY KEY,
    name VARCHAR (2000)
);

CREATE TABLE IF NOT EXISTS accident_type
(
    id SERIAL PRIMARY KEY,
    name VARCHAR
);

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

CREATE TABLE authorities
(
    id serial primary key,
    authority VARCHAR(50) NOT NULL unique
);

CREATE TABLE users
(
    id serial primary key,
    username VARCHAR(50) NOT NULL unique,
    password VARCHAR(100) NOT NULL,
    enabled boolean default true,
    authority_id int not null references authorities(id)
);