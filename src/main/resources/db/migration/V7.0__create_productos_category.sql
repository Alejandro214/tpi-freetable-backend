DROP TABLE IF EXISTS `productos_category`;
CREATE TABLE `productos_category` (
  `id_product` int NOT NULL,
  `id_category` int NOT NULL,
  PRIMARY KEY (`id_product`,`id_category`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `productos_category` VALUES (18,1),(18,8),(19,1),(19,8),(20,2),(20,5),(21,2),(21,5),(22,2),(22,4),(23,1),(23,8),(24,3),(25,3),(25,6),(26,1),(26,8),(27,3),(38,2),(39,2);


