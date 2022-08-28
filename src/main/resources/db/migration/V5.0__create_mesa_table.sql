DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `idMesa` int NOT NULL AUTO_INCREMENT,
  `estadoMesa` varchar(500) DEFAULT 'disponible',
  `positionMesa` INT NOT NULL,
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `mesa` VALUES (1,'Disponible',0),(2,'Disponible',1),(3,'Disponible',2),(4,'Disponible',3),(5,'Disponible',4),(6,'Disponible',5);
