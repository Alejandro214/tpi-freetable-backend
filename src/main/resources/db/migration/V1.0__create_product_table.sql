
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `idProduct` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `image` varchar(500) NOT NULL,
  `price` double NOT NULL,
  `description` varchar(500) NOT NULL,
  `category` INT NOT NULL default 0,
  `idOrder` INT ,
  PRIMARY KEY (`idProduct`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

INSERT INTO `restaurant_db`.`product` (`name`, `image`, `price`, `description`) values
('Papas Fritas','https://cukme.com/wp-content/uploads/2021/05/Origen-de-las-papas-fitas-1.jpg',300,'Plato de papas fritas'),
('Pizza','https://media.lascalientesdelsur.co/upload/2020/11/Buen-apetito-Superintendencia-de-Electricidad-compra-429-mil-pesos-de-pizzas.jpg',600,'Pizza grande con pepperoni'),
('Fanta','https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg',200,'Gaseosa fanta mediana'),
('Coca-Cola','https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/3080006_f.jpg',300,'Gaseosa Coca-cola mediana'),
('Vino Catena Malbec','https://www.deliargentina.com/image/cache/catalog/product/vino/vino-catena-malbec-argentina/vino-catena-malbec-argentina-335x335.png',400,'Vino Catena Malbec'),
('Albóndigas con puré','https://img-global.cpcdn.com/recipes/a65a244dd444de3e/680x482cq70/albondigas-de-carne-picada-con-pure-foto-principal.webp',250,'Albóndigas de carne picada con puré'),
('Pastel de Zanahoria','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoMPgp2ZL8_xEiiU-de2CuhF5Rpsanym9sL-sea2W5lWipK7E2b8zR4BtceV1PCV5g0mg&usqp=CAU',150,'Pastel de Zanahoria'),
('Helado de Chocolate','https://estag.fimagenes.com/img/2/1/S/V/K/1SVK_900.jpg',220,'Tres bochas de helado de chocolate'),
('Milansea napolitana','https://resizer.glanacion.com/resizer/DX1-dyjtqe3efPEahil_dwkYeuQ=/768x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/VLWFAANIWBGPFO4CSUHS7RYVVQ.jpg',320,'Milanesa napolitana con papas fritas'),
('Flan','https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/07/flan-de-vainilla-con-leche-de-avena.jpg',180,'Delicioso postre sin lactosa, flan de vainilla ¡con leche de avena');
