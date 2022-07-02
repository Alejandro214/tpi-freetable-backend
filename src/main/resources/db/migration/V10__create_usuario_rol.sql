
DROP TABLE IF EXISTS `rol`;

CREATE TABLE `rol` (
                       `id` int NOT NULL AUTO_INCREMENT,
                       `rol_nombre` varchar(45) NOT NULL,
                       PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `rol` VALUES (3,'ROLE_USER'),(4,'ROLE_ADMIN'),(5,'ROLE_SOPORTE');


DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
                           `id` int NOT NULL AUTO_INCREMENT,
                           `email` varchar(45) NOT NULL,
                           `nombre` varchar(45) NOT NULL,
                           `nombre_usuario` varchar(45) NOT NULL,
                           `password` varchar(500) NOT NULL,
                           PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `usuario` VALUES (1,'ale@gmail.com','user','user','$2a$10$00RTbqj5jMBRZcoH4iyw2OVJGYz.0d.JTUlviepR1Twly0BTq0McW'),(2,'pepe@gmail.com','pepe','pepe','$2a$10$0NVIp9z8npm79adaTea0POopNY475GanjY47DO2PWLDpbgPKjqyPK'),(3,'feo@gmail.com','Luis Alejandro','feo','$2a$10$1/QV4HMpSIG5j/bRtdo4CeZukxNB3KqIOxH/YNJm61yfWWttA7OQ6'),(4,'aleale822@gmail.com','Luis Alejandro','kkk','$2a$10$J37WO/FJJVMJ4MvRO/FiJeZW5FAnovi272dqzZrJOckl8CZVtHPsC'),(5,'fede@gmail.com','Luis Alejandro','r','$2a$10$FZ8kgiJTu9V0.JtkJEgP5.Q/t.36XMdk/AIp2Ge832r3Gn3fkOen.');



DROP TABLE IF EXISTS `usuario_rol`;

CREATE TABLE `usuario_rol` (
                               `usuario_id` int NOT NULL,
                               `rol_id` int NOT NULL,
                               PRIMARY KEY (`usuario_id`,`rol_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--


INSERT INTO `usuario_rol` VALUES (1,3),(2,3),(3,3),(4,3),(5,3);


