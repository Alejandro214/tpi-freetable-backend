DROP TABLE IF EXISTS `productos_pedidos`;

CREATE TABLE `productos_pedidos` (
  `product` int NOT NULL,
  `table_order` int NOT NULL,
  PRIMARY KEY (`product`,`table_order`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



LOCK TABLES `productos_pedidos` WRITE;
INSERT INTO `productos_pedidos` VALUES (18,1),(18,2),(18,3),(18,4),(18,5),(18,6),(18,7),(18,8),(18,11),(18,18),(18,19),(18,21),(18,23),(18,24),(18,26),(18,27),(18,33),(19,1),(19,2),(19,8),(19,11),(19,14),(19,18),(19,19),(19,21),(19,23),(19,24),(19,26),(19,33),(19,34),(19,37),(20,1),(20,2),(20,14),(20,33),(20,34),(20,37),(21,1),(21,4),(21,5),(21,6),(21,7),(21,8),(21,9),(22,4),(22,5),(22,6),(22,7),(22,8),(22,9),(23,4),(23,5),(23,6),(23,7),(23,8),(23,9),(23,37),(24,4),(24,5),(24,6),(24,7),(24,8),(24,9),(25,4),(25,5),(25,6),(25,7),(25,8),(25,9);
UNLOCK TABLES;