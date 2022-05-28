DROP TABLE IF EXISTS `category`;

CREATE TABLE `category` (
  `idCategory` int NOT NULL AUTO_INCREMENT,
  `category` int NOT NULL,
  `nameCategory` varchar(45) NOT NULL,
  PRIMARY KEY (`idCategory`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `category` WRITE;
INSERT INTO `category` VALUES (1,1,'Comidas'),(2,2,'Bebidas'),(3,3,'Postres'),(4,4,'Vinos'),(5,5,'Gaseosas'),(6,6,'Helado'),(7,7,'Dulces'),(8,10,'Clasicos'),(9,9,'Cervezas');
UNLOCK TABLES;