CREATE TABLE settingUser (
  idSettingUser INT NOT NULL AUTO_INCREMENT,
  nombreUsuario VARCHAR(45) NOT NULL,
  cantMesas INT NOT NULL DEFAULT 10,
  imagenData mediumblob ,
  PRIMARY KEY (idSettingUser));

  INSERT INTO settinguser (nombreUsuario,cantMesas) VALUES ('admin',10);
