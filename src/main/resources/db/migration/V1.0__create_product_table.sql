-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: restaurant_db
-- ------------------------------------------------------
-- Server version	8.0.28

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `product`
--

DROP TABLE IF EXISTS `product`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `product` (
  `idProduct` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(500) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(500) NOT NULL,
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `product`
--

LOCK TABLES `product` WRITE;
/*!40000 ALTER TABLE `product` DISABLE KEYS */;
INSERT INTO `product` VALUES (8,'Papas Fritas','https://cukme.com/wp-content/uploads/2021/05/Origen-de-las-papas-fitas-1.jpg',300,'Plato de papas fritas'),(9,'Pizza','https://media.lascalientesdelsur.co/upload/2020/11/Buen-apetito-Superintendencia-de-Electricidad-compra-429-mil-pesos-de-pizzas.jpg',600,'Pizza grande con pepperoni'),(10,'Fanta','https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg',200,'Gaseosa fanta mediana'),(11,'Coca-Cola','https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/3080006_f.jpg',300,'Gaseosa Coca-cola mediana'),(12,'Vino Catena Malbec','https://www.deliargentina.com/image/cache/catalog/product/vino/vino-catena-malbec-argentina/vino-catena-malbec-argentina-335x335.png',400,'Vino Catena Malbec'),(13,'Albóndigas con puré','https://img-global.cpcdn.com/recipes/a65a244dd444de3e/680x482cq70/albondigas-de-carne-picada-con-pure-foto-principal.webp',250,'Albóndigas de carne picada con puré'),(14,'Pastel de Zanahoria','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoMPgp2ZL8_xEiiU-de2CuhF5Rpsanym9sL-sea2W5lWipK7E2b8zR4BtceV1PCV5g0mg&usqp=CAU',150,'Pastel de Zanahoria'),(15,'Helado de Chocolate','https://estag.fimagenes.com/img/2/1/S/V/K/1SVK_900.jpg',220,'Tres bochas de helado de chocolate'),(16,'Milansea napolitana','https://resizer.glanacion.com/resizer/DX1-dyjtqe3efPEahil_dwkYeuQ=/768x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/VLWFAANIWBGPFO4CSUHS7RYVVQ.jpg',320,'Milanesa napolitana con papas fritas'),(17,'Flan','https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/07/flan-de-vainilla-con-leche-de-avena.jpg',180,'Delicioso postre sin lactosa, flan de vainilla ¡con leche de avena');
/*!40000 ALTER TABLE `product` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'restaurant_db'
--

--
-- Dumping routines for database 'restaurant_db'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-04-24 19:07:20
