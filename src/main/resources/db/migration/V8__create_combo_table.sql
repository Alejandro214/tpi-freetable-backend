

DROP TABLE IF EXISTS `combo`;

CREATE TABLE `combo` (
  `idProduct` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(500) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(500) NOT NULL,
  `cantProduct` INT NOT NULL DEFAULT 1,
  `from` varchar(500) NOT NULL,
  `to` varchar(500) NOT NULL,

  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `combo` VALUES (40,'Papas fritas mas Hamburguesa','https://us.123rf.com/450wm/maxsheb/maxsheb1909/maxsheb190900134/129462448-hamburguesa-con-papas-fritas-en-el-pergamino.jpg?ver=6',500,'Combo de papas fritas con hamburguesa',1,'30/05/2022','05/06/2022'),
(41,'Pizza familiar mas gaseosa','https://genovesapizza.cl/wp-content/uploads/2018/10/GRAND_BE.jpg',800,'Pizza grande con aceitunas negras mas gaseosa',1,'30/05/2022','05/06/2022'),(
42,'Milanga mas papas','https://images.rappi.com.ar/products/1456810-1596043085858.jpg',400,'Milanesa napolitana mas papa fritas',1,'30/05/2022','05/06/2022');
