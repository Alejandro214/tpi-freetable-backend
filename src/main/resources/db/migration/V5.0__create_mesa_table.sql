DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `idMesa` int NOT NULL AUTO_INCREMENT,
  `estadoMesa` varchar(500) DEFAULT 'disponible',
  `positionMesa` INT NOT NULL,
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `mesa` VALUES (1,'Disponible',1),(2,'Disponible',2),(3,'Disponible',3),(4,'Disponible',4),(5,'Disponible',5),(6,'Disponible',6);
