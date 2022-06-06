CREATE TABLE `restaurant_db`.`table_user` (
  `idUser` INT NOT NULL AUTO_INCREMENT,
  `userName` VARCHAR(100) NOT NULL,
  `email` VARCHAR(100) NOT NULL,
  `password` VARCHAR(100) NOT NULL,
  `rol` VARCHAR(100) NOT NULL DEFAULT 'User',
  PRIMARY KEY (`idUser`));