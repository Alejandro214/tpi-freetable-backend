DROP TABLE IF EXISTS `mesa`;

CREATE TABLE `mesa` (
  `idMesa` int NOT NULL AUTO_INCREMENT,
  `estadoMesa` varchar(500) DEFAULT 'disponible',
  `positionMesa` INT NOT NULL,
  `numeroMesa` INT NOT NULL,
  PRIMARY KEY (`idMesa`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
