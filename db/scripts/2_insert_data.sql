INSERT INTO rule(name) VALUES ('Статья. 1');
INSERT INTO rule(name) VALUES ('Статья. 2');
INSERT INTO rule(name) VALUES ('Статья. 3');

INSERT INTO accident_type(name) VALUES ('Две машины');
INSERT INTO accident_type(name) VALUES ('Машина и человек');
INSERT INTO accident_type(name) VALUES ('Машина и велосипед');

insert into authorities (authority) values ('ROLE_USER');
insert into authorities (authority) values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$vUkP6wmpkhgmz4UV7aFdPeZ9fZAqwMrfEgeckhSju6PjykFQCweBq',
        (select id from authorities where authority = 'ROLE_ADMIN'));