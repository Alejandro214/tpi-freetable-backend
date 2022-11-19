DROP TABLE IF EXISTS product;
CREATE TABLE product (
  idProduct int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  image varchar(500) NOT NULL,
  price double NOT NULL,
  cantProduct INT NOT NULL DEFAULT 1,
  PRIMARY KEY (idProduct)
);



INSERT INTO product VALUES (18,'Papas Fritas','https://cukme.com/wp-content/uploads/2021/05/Origen-de-las-papas-fitas-1.jpg',300,1),
(19,'Pizza','https://media.lascalientesdelsur.co/upload/2020/11/Buen-apetito-Superintendencia-de-Electricidad-compra-429-mil-pesos-de-pizzas.jpg',600,1),
(20,'Fanta','https://superlago.com.ar/wp-content/uploads/2021/01/7790895000454.jpg',200,1),
(21,'Coca-Cola','https://statics.dinoonline.com.ar/imagenes/full_600x600_ma/3080006_f.jpg',300,1),
(22,'Vino Catena Malbec','https://www.deliargentina.com/image/cache/catalog/product/vino/vino-catena-malbec-argentina/vino-catena-malbec-argentina-335x335.png',400,1),
(23,'Albóndigas con puré','https://img-global.cpcdn.com/recipes/a65a244dd444de3e/680x482cq70/albondigas-de-carne-picada-con-pure-foto-principal.webp',250,1),
(24,'Pastel de Zanahoria','https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoMPgp2ZL8_xEiiU-de2CuhF5Rpsanym9sL-sea2W5lWipK7E2b8zR4BtceV1PCV5g0mg&usqp=CAU',150,1),
(25,'Helado de Chocolate','https://estag.fimagenes.com/img/2/1/S/V/K/1SVK_900.jpg',220,1),
(26,'Milansea napolitana','https://resizer.glanacion.com/resizer/DX1-dyjtqe3efPEahil_dwkYeuQ=/768x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/VLWFAANIWBGPFO4CSUHS7RYVVQ.jpg',320,1),(
27,'Flan','https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/07/flan-de-vainilla-con-leche-de-avena.jpg',180,1),
(38,'Cerveza Corona','http://d3ugyf2ht6aenh.cloudfront.net/stores/001/211/660/products/corona-7101-9a2faa7ea9b4adc38d16196380770669-640-0.png',300,1),
(39,'Cerveza Brahma','https://supermaxsa.com.ar/wp-content/uploads/2020/04/2484.png',250,1),
(40,'Empanada de carne','https://comidasparaguayas.com/wp-content/uploads/2019/11/empanada-de-carne-paraguaya_700x465.jpg',100,1),
(41,'Empanada de pollo','https://www.frizata.com/files/products/622b8e5ababe6-34-4.jpg',100,1),
(42,'Guiso de lentejas','https://static-sevilla.abc.es/media/gurmesevilla/2014/01/estofado-lentejas.jpg',700,1),
(43,'BONDIOLA DE CERDO TONKATSU','https://onkitchen.com.ar/wp-content/uploads/2021/05/OK_Web_Bondiola-1.jpg',875,1),
(44,'Pollo a la mostaza','https://t2.rg.ltmcdn.com/es/posts/8/6/0/pollo_a_la_mostaza_59068_600.jpg',850,1),
(45,'Ñoquis','https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2019/10/como-hacer-noquis.jpg',380,1),
(46,'Filete de merluza a la romana','https://placeralplato.com/files/2015/07/merluza-a-la-romana-640x480.jpg?width=1200&enable=upscale',500,1),
(47,'Ensalada completa','https://i.blogs.es/0930e9/img_20200814_171003/450_1000.jpg',1100,1),
(48,'Chorizo a la pomarola','https://resizer.glanacion.com/resizer/vHCMjXEJl0pztbVMGwd3rRWp_bo=/768x0/filters:format(webp):quality(80)/cloudfront-us-east-1.images.arcpublishing.com/lanacionar/H3CNX7FWGBGWHAMGR3CYHNCMHM.jpg',600,1),
(49,'Pastel de papas','https://www.cucinare.tv/wp-content/uploads/2018/11/Pastel-de-papas.jpg',400,1),
(50,'Pastel de carne','https://t2.rg.ltmcdn.com/es/posts/5/1/9/pastel_de_carne_al_horno_32915_orig.jpg',1000,1),
(60,'Salmon','https://www.comedera.com/wp-content/uploads/2022/04/salmon-con-salsa-de-champin%CC%83ones-500x500.jpg',700,1),
(61,'Vino valentin','https://d3ugyf2ht6aenh.cloudfront.net/stores/001/170/454/products/don-valentin1-06aa70a3e033925bbe15930119294499-480-0.png',400,1),
(66,'Ensalada rusa','https://cuk-it.com/wp-content/uploads/2020/11/ensalada-rusa-ig02.jpg',300,1),
(67,'Extasiante cheesecake de chocolate','https://assets.kraftfoods.com/assets/recipe_images/Chocolate-Bliss-Cheesecake-4345.jpg',500,1),
(68,'Quesadillas de pavo y salsa de arandanos','https://www.vvsupremo.com/wp-content/uploads/2016/02/Quesadillas-de-Pavo-y-Salsa-de-Ar%C3%A1ndanos.jpg',400,1),
(69,'Sándwich tostado de queso favorito de América','http://assets.kraftfoods.com/recipe_images/opendeploy/106866_MXM_K2526V16S_OR1_H_640x428.jpg',250,1),
(70,'Medallones de cerdo con salsa de Jamaica','https://ranchera.com.co/wp-content/uploads/2022/04/medallones-de-cerdo.jpg',900,1),
(72,'Costillas con salsa chimichurri','https://estag.fimagenes.com/imagenesred/2240021_0.jpg?1?1',550,1),
(73,'Champiñones rellenos con espinacas','https://cdn7.kiwilimon.com/recetaimagen/16021/8025.jpg',1250,1),
(74,'Cintas de pasta con goulash y champiñones','https://images.hola.com/imagenes/cocina/recetas/20190830148394/cintas-goulash-champinones/0-714-176/tallarines-goulash-m.jpg',350,1),
(75,'Solomillo wellington de cerdo con champiñones','https://images.hola.com/imagenes/cocina/recetas/20191111153728/solomillo-wellington-cerdo-champinones/0-744-395/wellington-cerdo-age-t.jpg',750,1),
(76,'Sopa de Mariscos','https://www.mexicoenmicocina.com/wp-content/uploads/2019/05/caldo-de-mariscos-receta-8_1.jpg',650,1),
(77,'Atún a la Plancha','https://t2.rg.ltmcdn.com/es/posts/6/6/5/atun_a_la_plancha_con_ajo_y_perejil_59566_orig.jpg',200,1);
