DROP TABLE IF EXISTS productos_pedidos;

CREATE TABLE productos_pedidos (
  id_product int NOT NULL,
  id_order int NOT NULL,
  cantProduct INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_product,id_order)
);
