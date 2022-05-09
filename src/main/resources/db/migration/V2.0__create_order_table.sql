DROP TABLE IF EXISTS `table_order`;
CREATE TABLE `table_order` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `totalPrice` double NOT NULL,
  `idMesa` int,
  PRIMARY KEY (`idOrder`)
)

