-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.6.17


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema august
--

CREATE DATABASE IF NOT EXISTS august;
USE august;

--
-- Definition of table `entregador`
--

DROP TABLE IF EXISTS `entregador`;
CREATE TABLE `entregador` (
  `ID_ent` int(11) NOT NULL,
  `Nome_ent` varchar(50) NOT NULL,
  `Veiculo_ent` varchar(20) NOT NULL,
  `CPF_ent` varchar(20) NOT NULL,
  `Salario_ent` float NOT NULL,
  `DataNasc_ent` varchar(10) NOT NULL,
  `FotoURL_ent` varchar(200) NOT NULL,
  PRIMARY KEY (`ID_ent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `entregador`
--

/*!40000 ALTER TABLE `entregador` DISABLE KEYS */;
INSERT INTO `entregador` (`ID_ent`,`Nome_ent`,`Veiculo_ent`,`CPF_ent`,`Salario_ent`,`DataNasc_ent`,`FotoURL_ent`) VALUES 
 (1,'Tom Baker','Bike','123.456.789-00',1500,'12/06/1970','H:/ENTIMG/4th.jpg'),
 (2,'David Tennant','Carro','121.233.232-32',1200,'09/06/2013','H:/ENTIMG/10th.jpg'),
 (3,'Strax','Bike','120.212.232-32',800,'06/12/1994','H:/ENTIMG/strax.jpg'),
 (4,'Adipose','Carro','120.965.557-57',4000,'12/03/1950','H:/ENTIMG/adp.jpg'),
 (5,'Matt Smith','Bike','000.000.000-00',1200,'05/05/2000','H:/ENTIMG/11th.jpg'),
 (6,'Silence','Moto','000.000.000-00',3000,'01/01/1970','H:/ENTIMG/Silent.jpg'),
 (7,'Leela','Bike','120.666.123-33',760,'12/04/2001','H:/ENTIMG/Leela.jpg');
/*!40000 ALTER TABLE `entregador` ENABLE KEYS */;


--
-- Definition of table `ingrediente`
--

DROP TABLE IF EXISTS `ingrediente`;
CREATE TABLE `ingrediente` (
  `ID_ing` int(10) unsigned NOT NULL,
  `Nome_ing` varchar(45) NOT NULL,
  `Unidade_ing` varchar(3) NOT NULL,
  `Categoria_ing` varchar(45) NOT NULL,
  `PrecoUnitario_ing` float NOT NULL,
  `EstoqueAtual_ing` float NOT NULL DEFAULT '0',
  `EstoqueMinimo_ing` int(10) unsigned NOT NULL,
  `EstoqueMaximo_ing` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID_ing`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabela que guarda os ingredintes dos pratos';

--
-- Dumping data for table `ingrediente`
--

/*!40000 ALTER TABLE `ingrediente` DISABLE KEYS */;
INSERT INTO `ingrediente` (`ID_ing`,`Nome_ing`,`Unidade_ing`,`Categoria_ing`,`PrecoUnitario_ing`,`EstoqueAtual_ing`,`EstoqueMinimo_ing`,`EstoqueMaximo_ing`) VALUES 
 (1,'Carne de Porco','KG','Carne',25,10.2,5,40),
 (2,'Limão','UNI','Tempero',0.75,5,15,40),
 (3,'Oléo','L','Molho',7.8,2.8,20,70),
 (4,'Cebola','KG','Tempero',19.6,11.5,12,32),
 (5,'Acem','KG','Carne',17,12,5,25),
 (6,'Massa','KG','Massa',23,8,6,16),
 (7,'Asa de galinha','KG','Carne',21,6,5,25),
 (8,'Tomate','KG','Tempero',6.7,11.6,5,20),
 (9,'Caldo Kinnor','UNI','Tempero',0.95,72,20,100),
 (10,'Fígado','KG','Carne',12.6,18,5,9),
 (11,'Alface','KG','Outros',1.2,8.4,12,21),
 (12,'Arroz','KG','Outros',7.65,48,10,25),
 (13,'Feijão','KG','Outros',8.5,35.2,10,15),
 (14,'Farinha de Trigo','KG','Molho',4.6,16.61,10,20),
 (15,'Farinha de Rosca','KG','Outros',3.5,29.3,7,15),
 (16,'Peixe','KG','Carne',20,14,7,20),
 (17,'Queijo','KG','Tempero',12,25,20,50),
 (18,'Batata','KG','Outros',5.7,17,6,25),
 (19,'Leite','L','Outros',4.5,6.4,15,30);
/*!40000 ALTER TABLE `ingrediente` ENABLE KEYS */;


--
-- Definition of table `lote`
--

DROP TABLE IF EXISTS `lote`;
CREATE TABLE `lote` (
  `Numero_lot` int(10) unsigned NOT NULL,
  `QuantidadeInicial_lot` float NOT NULL DEFAULT '0',
  `QuantidadeAtual_lot` float NOT NULL DEFAULT '0',
  `Validade_lot` varchar(10) NOT NULL,
  `Produto_lot` int(10) unsigned NOT NULL,
  PRIMARY KEY (`Numero_lot`),
  KEY `FK_Lote_1` (`Produto_lot`),
  CONSTRAINT `FK_Lote_1` FOREIGN KEY (`Produto_lot`) REFERENCES `ingrediente` (`ID_ing`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='Tabela que guarda os lotes dos ing';

--
-- Dumping data for table `lote`
--

/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` (`Numero_lot`,`QuantidadeInicial_lot`,`QuantidadeAtual_lot`,`Validade_lot`,`Produto_lot`) VALUES 
 (11,10,2.2,'2015-11-23',1),
 (12,8,8,'2016-06-06',1),
 (23,50,5,'2016-02-12',2),
 (31,5,2.8,'2016-06-06',3),
 (41,6,1.5,'2015-12-21',4),
 (42,10,10,'2015-11-21',4),
 (51,10,10,'2015-05-12',5),
 (52,9,2,'2016-01-06',5),
 (61,11,8,'2016-06-19',6),
 (71,12,6,'2015-12-05',7),
 (81,5,5,'2015-03-12',8),
 (82,10,6.6,'2015-12-25',8),
 (91,100,72,'2015-11-28',9),
 (101,8,8,'2015-06-07',10),
 (102,12,10,'2016-03-03',10),
 (111,9,8.4,'2015-12-21',11),
 (121,50,45,'2016-01-04',12),
 (122,3,3,'2015-09-08',12),
 (131,20,15.2,'2015-11-30',13),
 (132,20,20,'2016-01-04',13),
 (141,20,10.61,'2016-06-21',14),
 (142,6,6,'2015-12-02',14),
 (151,9.7,8.3,'2015-11-25',15),
 (152,21,21,'2016-12-21',15),
 (161,9,9,'2015-06-13',16),
 (162,20,5,'2015-12-20',16),
 (171,32,25,'2016-01-07',17),
 (181,21,17,'2015-12-05',18),
 (191,10,6.4,'2015-12-08',19);
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;


--
-- Definition of table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `ID_ped` int(11) NOT NULL DEFAULT '0',
  `NomeCliente_ped` varchar(45) NOT NULL,
  `FormaPagamento_ped` varchar(45) NOT NULL,
  `Data_ped` varchar(20) NOT NULL DEFAULT '0',
  `Telefone_ped` varchar(9) NOT NULL,
  `Senha_ped` varchar(45) NOT NULL,
  `ValorTotal_ped` float NOT NULL,
  `Entregador_ped` int(11) NOT NULL DEFAULT '0',
  `TempoEntrega_ped` varchar(10) NOT NULL DEFAULT '0',
  `E_Municipio_ped` varchar(45) NOT NULL,
  `E_Bairro_ped` varchar(45) NOT NULL,
  `E_Rua_ped` varchar(45) NOT NULL,
  `E_CasaNumero_ped` int(10) unsigned NOT NULL,
  `Status_ped` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_ped`),
  KEY `FK_pedido_1` (`Entregador_ped`),
  CONSTRAINT `FK_pedido_1` FOREIGN KEY (`Entregador_ped`) REFERENCES `entregador` (`ID_ent`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COMMENT='pedido dos cara';

--
-- Dumping data for table `pedido`
--

/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` (`ID_ped`,`NomeCliente_ped`,`FormaPagamento_ped`,`Data_ped`,`Telefone_ped`,`Senha_ped`,`ValorTotal_ped`,`Entregador_ped`,`TempoEntrega_ped`,`E_Municipio_ped`,`E_Bairro_ped`,`E_Rua_ped`,`E_CasaNumero_ped`,`Status_ped`) VALUES 
 (1,'The Doctor','Allons-y!','08/11/2015 07:29:41','4221-1052','628279',69.5,2,'0','Zona Norte','Valloran','Rua das Moscas',34,'Em andamento'),
 (2,'Lalla Ward','Dinheiro','08/11/2015 07:39:07','9876-5434','6420843',65.7,5,'0','Belford Roxo','Gallifrey','Alto Comando de Gallifrey',56,'Em andamento'),
 (3,'John Lennon','Dinheiro','08/11/2015 07:42:20','4242-3535','473755',110.89,1,'0','Nova Iguaçu','Strawberry Fields','Forever',21,'Em andamento'),
 (4,'Paul McCartney','Allons-y!','08/11/2015 07:54:37','1213-9548','2800620',154.95,5,'0','São João','Penny Lane','Martha',56,'Em andamento'),
 (5,'Ringo Starr','Cartão','08/11/2015 08:01:24','2121-2122','8608218',91.78,4,'0','Belford Roxo','Blue Jay Way','Good Day Sunshine',54,'Em andamento'),
 (6,'George Harrison','Dinheiro','08/11/2015 08:03:30','1205-4345','3511841',44.56,7,'0','Queimados','Revolver','Tomorrow Never Knows',23,'Em andamento'),
 (7,'Yoko Ono','Allons-y!','08/11/2015 08:08:23','1223-2232','21650',120.06,6,'0','Belford Roxo','Bungalow Bill','What did you kill?',56,'Em andamento');
INSERT INTO `pedido` (`ID_ped`,`NomeCliente_ped`,`FormaPagamento_ped`,`Data_ped`,`Telefone_ped`,`Senha_ped`,`ValorTotal_ped`,`Entregador_ped`,`TempoEntrega_ped`,`E_Municipio_ped`,`E_Bairro_ped`,`E_Rua_ped`,`E_CasaNumero_ped`,`Status_ped`) VALUES 
 (8,'Harry Potter','Ticket','08/11/2015 08:09:02','2435-4445','1848719',83.22,4,'0','São João','Hogwarts','Câmara Secreta',57,'Em andamento'),
 (9,'Hermione Granger','Allons-y!','08/11/2015 08:11:38','2323-2444','4626776',45.89,3,'02:43:27','Belford Roxo','Londres','Sangue Ruim',34,'Concluído'),
 (10,'Ronald Weasley','Ticket','08/11/2015 08:14:13','2323-1444','9623062',55.5,6,'0','Zona Norte','Acre','Lugar Nenhum',0,'Em andamento'),
 (11,'Henry VIII','Cartão','08/11/2015 09:54:25','1212-2332','3760709',98.8,1,'0','Zona Norte','Inglaterra 1500','Tudor',21,'Em andamento'),
 (12,'Edward I','Allons-y!','08/11/2015 09:56:30','1223-2323','274425',51.5,1,'01:00:53','Belford Roxo','Alga Azul','Coral',900,'Concluído'),
 (13,'Mary I','Dinheiro','08/11/2015 10:00:10','1237-5999','7334070',30.7,3,'0','Belford Roxo','Plutão','Terra',76,'Em andamento'),
 (14,'Elizabeth I','Ticket','08/11/2015 10:06:42','4545-5444','8623245',47.8,6,'0','Belford Roxo','Gallifrey Falls','No More',5,'Em andamento');
INSERT INTO `pedido` (`ID_ped`,`NomeCliente_ped`,`FormaPagamento_ped`,`Data_ped`,`Telefone_ped`,`Senha_ped`,`ValorTotal_ped`,`Entregador_ped`,`TempoEntrega_ped`,`E_Municipio_ped`,`E_Bairro_ped`,`E_Rua_ped`,`E_CasaNumero_ped`,`Status_ped`) VALUES 
 (15,'Romana','Ticket','08/11/2015 10:09:44','2324-2555','4184428',79.66,7,'0','Zona Norte','O verso do inverso','Triens ben assemble',12,'Em andamento'),
 (16,'Sandro','Dinheiro','08/11/2015 10:11:12','8680-3777','6745569',172.19,5,'0','Zona Norte','Méier','Capitão Rezende',408,'Em andamento'),
 (17,'George Harrison','Dinheiro','08/11/2015 10:29:48','2122-1212','3184943',67.68,2,'0','Belford Roxo','Savoy Truflle','Cream Tangerine',43,'Em andamento');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;


--
-- Definition of table `pedido_prato`
--

DROP TABLE IF EXISTS `pedido_prato`;
CREATE TABLE `pedido_prato` (
  `ID_ppr` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `Pedido_ppr` int(11) NOT NULL DEFAULT '0',
  `Prato_ppr` int(10) unsigned NOT NULL,
  PRIMARY KEY (`ID_ppr`),
  KEY `FK_pedido_pratos_1` (`Pedido_ppr`),
  KEY `FK_pedido_pratos_2` (`Prato_ppr`),
  CONSTRAINT `FK_pedido_pratos_2` FOREIGN KEY (`Prato_ppr`) REFERENCES `prato` (`ID_pra`),
  CONSTRAINT `FK_pedido_pratos_1` FOREIGN KEY (`Pedido_ppr`) REFERENCES `pedido` (`ID_ped`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedido_prato`
--

/*!40000 ALTER TABLE `pedido_prato` DISABLE KEYS */;
INSERT INTO `pedido_prato` (`ID_ppr`,`Pedido_ppr`,`Prato_ppr`) VALUES 
 (16,1,11),
 (17,1,5),
 (18,2,5),
 (19,2,3),
 (20,2,12),
 (21,3,4),
 (22,3,7),
 (23,4,1),
 (24,4,3),
 (25,4,11),
 (26,4,7),
 (27,5,7),
 (28,5,7),
 (29,6,5),
 (30,6,8),
 (31,7,10),
 (32,7,3),
 (33,7,1),
 (34,8,8),
 (35,8,2),
 (36,8,1),
 (37,9,7),
 (38,10,9),
 (39,10,11),
 (40,11,12),
 (41,11,9),
 (42,11,4),
 (43,12,11),
 (44,12,2),
 (45,13,3),
 (46,13,12),
 (47,14,12),
 (48,14,5),
 (49,15,6),
 (50,15,1),
 (51,16,5),
 (52,16,11),
 (53,16,9),
 (54,16,6),
 (55,16,7),
 (56,16,12),
 (57,17,7),
 (58,17,13);
/*!40000 ALTER TABLE `pedido_prato` ENABLE KEYS */;


--
-- Definition of table `prato`
--

DROP TABLE IF EXISTS `prato`;
CREATE TABLE `prato` (
  `ID_pra` int(10) unsigned NOT NULL,
  `Nome_pra` varchar(60) NOT NULL,
  `FotoURL_pra` varchar(150) NOT NULL DEFAULT '0',
  `Preco_pra` float NOT NULL,
  `ModoPreparo_pra` varchar(300) NOT NULL,
  `Secao_pra` varchar(45) NOT NULL,
  `Calorias_pra` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_pra`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prato`
--

/*!40000 ALTER TABLE `prato` DISABLE KEYS */;
INSERT INTO `prato` (`ID_pra`,`Nome_pra`,`FotoURL_pra`,`Preco_pra`,`ModoPreparo_pra`,`Secao_pra`,`Calorias_pra`) VALUES 
 (1,'Carré','0',56.66,'Coloque a carne de porco na frigideira e espera ela ficar frita.','Carne',0),
 (2,'Frango Frito','0',17,'Fritar as asas de frango.','Entrada',0),
 (3,'Espaguete','0',17.9,'Pega o macarrão coloca em uma panela com água fervendo e em teroria vira uma macarronada deliciosa.','Massa',0),
 (4,'Carne Assada','0',65,'Assar a carne no forno com os tempero.','Carne',0),
 (5,'Peixe Frito','0',35,'Pega o peixe e frita.','Carne',0),
 (6,'Figado Assado','0',23,'Pega o figado e coloca ele no forno espera um tempo que ele fica comestivel.','Entrada',0),
 (7,'Bolo de Limão','0',45.89,'Pega a farinha de trigo e joga ela junto com o limão no forno caso tudo dê certo vc tera bolo de limão;','Sobremesa',0),
 (8,'Aneis de Cebola','0',9.56,'Corte a cebola em partes, então frite-as.','Sobremesa',0),
 (9,'Feijão','0',21,'Um feijão com coisas sortidas dentro.','Entrada',0),
 (10,'Pizza de Frango','0',45.5,'Coloque a pizza dentro do forno com o frango, depois de alguns minituos ela deve virar uma pizza.','Massa',0);
INSERT INTO `prato` (`ID_pra`,`Nome_pra`,`FotoURL_pra`,`Preco_pra`,`ModoPreparo_pra`,`Secao_pra`,`Calorias_pra`) VALUES 
 (11,'Arroz Premium','0',34.5,'Coloque todo o arroz em uma panela folhada a ouro e diga que é premium.','Entrada',0),
 (12,'Batata Frita','0',12.8,'Fritar batata -','Entrada',0),
 (13,'Sorvete de Limão','0',21.79,'Pega o leite e o limão coloca no congelador e observa se vira sorvete.','Sobremesa',0);
/*!40000 ALTER TABLE `prato` ENABLE KEYS */;


--
-- Definition of table `prato_ingrediente`
--

DROP TABLE IF EXISTS `prato_ingrediente`;
CREATE TABLE `prato_ingrediente` (
  `Prato_pin` int(10) unsigned NOT NULL,
  `Ingrediente_pin` int(10) unsigned NOT NULL,
  `Quantidade_pin` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`Prato_pin`,`Ingrediente_pin`),
  KEY `FK_Prato_Ingrediente_2` (`Ingrediente_pin`),
  CONSTRAINT `FK_Prato_Ingrediente_1` FOREIGN KEY (`Prato_pin`) REFERENCES `prato` (`ID_pra`),
  CONSTRAINT `FK_Prato_Ingrediente_2` FOREIGN KEY (`Ingrediente_pin`) REFERENCES `ingrediente` (`ID_ing`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prato_ingrediente`
--

/*!40000 ALTER TABLE `prato_ingrediente` DISABLE KEYS */;
INSERT INTO `prato_ingrediente` (`Prato_pin`,`Ingrediente_pin`,`Quantidade_pin`) VALUES 
 (1,1,1.2),
 (1,2,5),
 (1,3,0.4),
 (2,2,4),
 (2,3,0.5),
 (2,7,3),
 (3,6,3),
 (3,8,0.7),
 (3,9,2),
 (4,4,0.3),
 (4,5,2),
 (4,9,2),
 (5,3,0.3),
 (5,11,0.12),
 (5,16,3),
 (6,8,0.3),
 (6,9,3),
 (6,10,1),
 (7,2,4),
 (7,14,1.2),
 (8,3,0.4),
 (8,4,0.7),
 (8,15,0.7),
 (9,1,1),
 (9,5,1),
 (9,13,1.6),
 (10,4,1),
 (10,6,1),
 (10,14,0.99),
 (10,17,3),
 (11,4,0.3),
 (11,9,2),
 (11,12,1),
 (11,17,0.8),
 (12,3,0.4),
 (12,18,0.8),
 (13,2,10),
 (13,19,3.6);
/*!40000 ALTER TABLE `prato_ingrediente` ENABLE KEYS */;


--
-- Definition of table `venda`
--

DROP TABLE IF EXISTS `venda`;
CREATE TABLE `venda` (
  `Numero_ven` int(5) NOT NULL AUTO_INCREMENT,
  `Data_ven` varchar(20) DEFAULT NULL,
  `Preco_ven` float DEFAULT NULL,
  `Entregador_ven` int(11) DEFAULT NULL,
  PRIMARY KEY (`Numero_ven`),
  KEY `Entregador_ven` (`Entregador_ven`),
  CONSTRAINT `venda_ibfk_1` FOREIGN KEY (`Entregador_ven`) REFERENCES `entregador` (`ID_ent`)
) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `venda`
--

/*!40000 ALTER TABLE `venda` DISABLE KEYS */;
INSERT INTO `venda` (`Numero_ven`,`Data_ven`,`Preco_ven`,`Entregador_ven`) VALUES 
 (13,'08/11/2015 10:55:05',45.89,3),
 (14,'08/11/2015 10:57:23',51.5,1),
 (15,'08/11/2015 12:58:30',122.75,2),
 (16,'08/12/2015 09:23:21',23.59,2),
 (17,'08/04/2015 13:00:42',56,5),
 (18,'08/02/2015 18:30:12',56,1),
 (19,'12/03/2015 20:45:31',76.89,6),
 (20,'21/04/2015 07:43:23',120.89,4),
 (21,'27/03/2015 10:12:32',79.9,5),
 (22,'07/05/2015 15:45:34',64.7,2),
 (23,'07/07/2015 10:45:34',30.7,3),
 (24,'07/08/2015 11:45:34',66.7,2),
 (25,'17/11/2015 07:25:37',70.7,5),
 (26,'07/05/2015 17:54:45',90,3),
 (27,'08/01/2015 22:05:34',40.7,1),
 (28,'21/08/2015 08:00:34',166.7,5),
 (29,'19/06/2015 18:31:04',90.5,6),
 (30,'21/05/2015 12:55:34',89,3),
 (31,'31/07/2015 19:25:25',56.7,4),
 (32,'11/04/2015 12:53:53',78,6),
 (33,'17/01/2015 11:55:14',53,3),
 (34,'02/09/2015 18:23:43',78,5),
 (35,'28/10/2015 08:25:37',23.7,6),
 (36,'23/10/2015 13:54:45',98,4),
 (37,'12/10/2015 23:05:34',76,2),
 (38,'06/04/2015 21:06:23',73.8,5),
 (39,'16/12/2015 12:09:12',98,1);
INSERT INTO `venda` (`Numero_ven`,`Data_ven`,`Preco_ven`,`Entregador_ven`) VALUES 
 (40,'01/01/2015 15:12:34',66.8,2),
 (41,'06/03/2015 09:12:21 ',104.5,3),
 (42,'21/04/2015 10:11:23',65,4),
 (43,'30/03/2015 11:23:11',37.7,5);
/*!40000 ALTER TABLE `venda` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
