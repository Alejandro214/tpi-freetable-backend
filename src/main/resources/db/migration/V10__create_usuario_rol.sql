
DROP TABLE IF EXISTS rol;

CREATE TABLE rol (
                       id int NOT NULL AUTO_INCREMENT,
                       rol_nombre varchar(45) NOT NULL,
                       PRIMARY KEY (id)
);


INSERT INTO rol VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_SOPORTE');


DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
                           id int NOT NULL AUTO_INCREMENT,
                           email varchar(45) NOT NULL,
                           nombre varchar(45) NOT NULL,
                           nombre_usuario varchar(45) NOT NULL,
                           password varchar(500) NOT NULL,
                           rol_id int NOT NULL,
                           PRIMARY KEY (id)
) ;
INSERT INTO usuario(email,nombre,nombre_usuario,password,rol_id) VALUES ('admin@gmail.com','admin','admin','$2a$10$pzWmmuCEDeE0Etwi1M7r4e6rlEwfbc9QoJhfHmKUu5QWLTWAJRaZ6',2);

