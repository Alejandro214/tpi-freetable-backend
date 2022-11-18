
DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
                       `id` int NOT NULL AUTO_INCREMENT,
                       `rol_nombre` varchar(45) NOT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `rol` VALUES (1,'ROLE_USER'),(2,'ROLE_ADMIN'),(3,'ROLE_SOPORTE');


DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `email` varchar(45) NOT NULL,
                           `nombre` varchar(45) NOT NULL,
                           `nombre_usuario` varchar(45) NOT NULL,
                           `password` varchar(500) NOT NULL,
                           `rol_id` varchar(45) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
