DROP TABLE IF EXISTS `productos_category`;

CREATE TABLE `productos_category` (
  `product` int NOT NULL,
  `category` int NOT NULL,
  PRIMARY KEY (`product`,`category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


LOCK TABLES `productos_category` WRITE;
INSERT INTO `productos_category` VALUES (18,1),(18,2),(18,8),(19,8),(20,5),(21,5),(22,4),(23,8),(25,6),(26,8);
UNLOCK TABLES;
