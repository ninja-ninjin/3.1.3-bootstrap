USE spring_security;

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');
INSERT INTO users (age, email, first_name, last_name, password)
        VALUES ('25', 'admin@mail.ru', 'admin', 'admin', '$2a$12$weus0YHHIW2YiRKibcz/kOfNeK9W7BwNF1szXWgDmk.0qh.BHdfTO');
INSERT INTO users_roles (user_id, role_id) VALUES ('1', '1');
INSERT INTO users_roles (user_id, role_id) VALUES ('1', '2');
INSERT INTO users (age, email, first_name, last_name, password)
        VALUES ('20', 'user@mail.ru', 'user', 'user', '$2a$12$yW/7C46ShlKaOdlC7Lvjnea7GpCpkXFnmTqm75prsm/OpCFnSh/zS');
INSERT INTO users_roles (user_id, role_id) VALUES ('2', '2');