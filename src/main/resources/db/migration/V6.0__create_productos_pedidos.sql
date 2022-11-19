DROP TABLE IF EXISTS productos_pedidos;

CREATE TABLE productos_pedidos (
  id_product int NOT NULL,
  id_order int NOT NULL,
  cantProduct INT NOT NULL DEFAULT 1,
  PRIMARY KEY (id_product,id_order)
);
INSERT INTO productos_pedidos (id_product,id_order,cantProduct) VALUES (18,1,1);
INSERT INTO productos_pedidos (id_product,id_order,cantProduct) VALUES (21,1,1);

INSERT INTO productos_pedidos (id_product,id_order,cantProduct) VALUES (18,2,1);
INSERT INTO productos_pedidos (id_product,id_order,cantProduct) VALUES (15,2,1);
