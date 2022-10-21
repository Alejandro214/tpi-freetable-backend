DROP TABLE IF EXISTS `table_order`;
CREATE TABLE `table_order` (
  `idOrder` int NOT NULL AUTO_INCREMENT,
  `totalPrice` double NOT NULL,
  `idMesa` int,
  `dateOrder` VARCHAR(45) NOT NULL,
  `statusOrder` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idOrder`)
)

