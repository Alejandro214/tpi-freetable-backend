DROP TABLE IF EXISTS table_order;
CREATE TABLE table_order (
  idOrder int NOT NULL AUTO_INCREMENT,
  totalPrice double NOT NULL,
  idMesa int,
  dateOrder VARCHAR(45) NOT NULL,
  statusOrder VARCHAR(45) NOT NULL,
  PRIMARY KEY (idOrder)
);

INSERT INTO table_order (totalPrice,idMesa,dateOrder,statusOrder) VALUES (300.0,1,'18-11-2022 14:00','CONFIRMADO');
INSERT INTO table_order (totalPrice,idMesa,dateOrder,statusOrder) VALUES (300.0,1,'18-11-2022 14:00','PAGADO');


