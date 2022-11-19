DROP TABLE IF EXISTS mesa;

CREATE TABLE mesa (
  idMesa int NOT NULL AUTO_INCREMENT,
  estadoMesa varchar(500) DEFAULT 'disponible',
  positionMesa INT NOT NULL,
  numeroMesa INT NOT NULL,
  PRIMARY KEY (idMesa)
) ;

INSERT INTO mesa (estadoMesa,positionMesa,numeroMesa) VALUES ('No Disponible', 1, 1);
INSERT INTO mesa (estadoMesa,positionMesa,numeroMesa) VALUES ('Reservada', 2, 2);
INSERT INTO mesa (estadoMesa,positionMesa,numeroMesa) VALUES ('Disponible', 3, 3);
INSERT INTO mesa (estadoMesa,positionMesa,numeroMesa) VALUES ('Ocupada', 4, 4);
