
DROP TABLE IF EXISTS `combo`;
CREATE TABLE `combo` (
  `idCombo` int NOT NULL AUTO_INCREMENT,
  `priceCombo` double NOT NULL,
  `image` varchar(500) NOT NULL,
  `description` varchar(500) NOT NULL,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`idCombo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `combo` WRITE;
INSERT INTO `combo` VALUES (1,500,'https://us.123rf.com/450wm/maxsheb/maxsheb1909/maxsheb190900134/129462448-hamburguesa-con-papas-fritas-en-el-pergamino.jpg?ver=6','Combo de papas fritas con hambuerguesa','Papas fritas mas Hambuerguesa'),(2,800,'https://genovesapizza.cl/wp-content/uploads/2018/10/GRAND_BE.jpg','Pizza grandra con aceitunas negras mas gaseosa','Pizza familiar mas gaseosa'),(3,400,'https://images.rappi.com.ar/products/1456810-1596043085858.jpg','Milanesa napolitana mas papa fritas','Milanga mas papas');
UNLOCK TABLES;

