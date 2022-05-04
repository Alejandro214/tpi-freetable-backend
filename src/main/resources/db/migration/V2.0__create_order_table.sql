DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `products` varchar(45) NOT NULL,
  PRIMARY KEY (`idOrder`)
)

