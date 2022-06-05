DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `idMesa` int NOT NULL AUTO_INCREMENT,
  `estadoMesa` varchar(500) DEFAULT 'disponible',
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `mesa` VALUES (1,'disponible'),(2,'disponible'),(3,'disponible'),(4,'disponible'),(5,'disponible'),(6,'disponible'),(7,'disponible'),(8,'disponible'),(9,'disponible'),(10,'disponible'),(11,'disponible'),(12,'disponible'),(13,'disponible'),(14,'disponible'),(15,'disponible'),(16,'disponible'),(18,'disponible'),(19,'disponible'),(66,'disponible'),(67,'disponible'),(68,'disponible'),(69,'disponible');
