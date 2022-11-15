CREATE TABLE `restaurant_db`.`settingUser` (
  `idSettingUser` INT NOT NULL AUTO_INCREMENT,
  `nombreUsuario` VARCHAR(45) NOT NULL,
  `cantMesas` INT NOT NULL DEFAULT 10,
  PRIMARY KEY (`idSettingUser`));