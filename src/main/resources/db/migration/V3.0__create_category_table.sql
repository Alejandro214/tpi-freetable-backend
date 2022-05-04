CREATE TABLE `category` (
  `idCategory` INT NOT NULL AUTO_INCREMENT,
  `category` INT NOT NULL,
  `nameCategory` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCategory`));


INSERT INTO `restaurant_db`.`category` (`category`, `nameCategory`) VALUES ('1', 'Comidas'),  ('2', 'Bebidas'), ('3', 'Postres');
