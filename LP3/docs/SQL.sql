-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.1.54-community-log


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema sabordorio
--

CREATE DATABASE IF NOT EXISTS sabordorio;
USE sabordorio;

--
-- Definition of table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
CREATE TABLE `cliente` (
  `codigo_cli` int(10) unsigned NOT NULL,
  `nome_cli` varchar(200) CHARACTER SET latin1 NOT NULL,
  `tel_cli` varchar(9) CHARACTER SET latin1 NOT NULL,
  `cel_cli` varchar(15) CHARACTER SET latin1 NOT NULL,
  `email_cli` varchar(90) CHARACTER SET latin1 DEFAULT NULL,
  `endereco_cli` varchar(200) CHARACTER SET latin1 DEFAULT NULL,
  `empresa_cli` int(10) unsigned DEFAULT NULL,
  `diapagamento_cli` varchar(15) CHARACTER SET latin1 DEFAULT NULL,
  `estadoconta_cli` varchar(45) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`codigo_cli`),
  KEY `empresa_cliente` (`empresa_cli`),
  CONSTRAINT `empresa_cliente` FOREIGN KEY (`empresa_cli`) REFERENCES `empresa` (`codigo_emp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `cliente`
--

/*!40000 ALTER TABLE `cliente` DISABLE KEYS */;
INSERT INTO `cliente` (`codigo_cli`,`nome_cli`,`tel_cli`,`cel_cli`,`email_cli`,`endereco_cli`,`empresa_cli`,`diapagamento_cli`,`estadoconta_cli`) VALUES 
 (0,'NO CLIENT','00000000','00000000','0000000','000000',0,'0000','0000'),
 (1,'CARLOS ALBERTO','2445-7910','(21) 99247-2325','CAFSGRAPHS@HOTMAIL.COM','R. CARLOS PALUT, 359, BLOCO 8, AP 208',0,'01','ok'),
 (2,'MARLY ZARZAR','2445-7910','(21) 99207-1853','MARLYZARZAR@HOTMAIL.COM','R. CARLOS PALUT, 359, BL 8, AP 208',0,'10-4-2017','pendente'),
 (3,'PEDRO AUGUSTO TRICKSTER','1111-1111','(21) 11111-1111','N LEMBRO','NEW YORK',0,'11','ok'),
 (4,'HAYLEY N. WILLIAMS','3123-1312','(  )      -    ','HAYLEY FROM PMORE','BEM QUE EU QUERIA SABER!',0,'10','pendente'),
 (5,'THALES ZARZAR','2445-7910','(21) 99120-2325','THAZARS@HOTMAIL.COM','R. CARLOS PALUT, 359, BL 8, AP 308',0,'01','ok'),
 (889,'Pedro Silva','21895099','37643525','mine@gmail.com','Nova York, Brooklin',9,'10','ok'),
 (891,'amanda zaza','2222','2222','12424','22222',16,'','ok'),
 (892,'asdfgh','sd','22','asdfgh','sas',14,'','ok');
/*!40000 ALTER TABLE `cliente` ENABLE KEYS */;


--
-- Definition of table `config`
--

DROP TABLE IF EXISTS `config`;
CREATE TABLE `config` (
  `nclipmesa_cfg` int(10) unsigned NOT NULL DEFAULT '10',
  `nmesas_cfg` int(10) unsigned NOT NULL DEFAULT '10',
  `codseguro_cfg` tinyint(1) NOT NULL DEFAULT '0',
  `animacoes_cfg` tinyint(1) NOT NULL DEFAULT '0',
  `cod_cfg` int(10) unsigned NOT NULL DEFAULT '1',
  PRIMARY KEY (`cod_cfg`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `config`
--

/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` (`nclipmesa_cfg`,`nmesas_cfg`,`codseguro_cfg`,`animacoes_cfg`,`cod_cfg`) VALUES 
 (10,12,0,0,1);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;


--
-- Definition of table `empresa`
--

DROP TABLE IF EXISTS `empresa`;
CREATE TABLE `empresa` (
  `codigo_emp` int(10) unsigned NOT NULL,
  `razaosocial_emp` varchar(45) CHARACTER SET latin1 NOT NULL,
  `tel1_emp` varchar(9) CHARACTER SET latin1 NOT NULL,
  `tel2_emp` varchar(9) CHARACTER SET latin1 DEFAULT NULL,
  PRIMARY KEY (`codigo_emp`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `empresa`
--

/*!40000 ALTER TABLE `empresa` DISABLE KEYS */;
INSERT INTO `empresa` (`codigo_emp`,`razaosocial_emp`,`tel1_emp`,`tel2_emp`) VALUES 
 (0,'0 SEM EMPRESA','0000-0000','0000-0000'),
 (1,'TEKNIC','8080-8080','8080-8080'),
 (6,'THYSSEN KRUPP','1111-1111','2222-2222'),
 (9,'GLASSFER','3333-3333','2222-2222'),
 (12,'VIVA COR','1111-1111','2222-2222'),
 (14,'SALÃO BELLA QUEEN','2222-2222','    -    '),
 (15,'PEPSI','3221-3231','1312-3123'),
 (16,'BATATAS FRITA DA DIDI','911919191','');
/*!40000 ALTER TABLE `empresa` ENABLE KEYS */;


--
-- Definition of table `estoque`
--

DROP TABLE IF EXISTS `estoque`;
CREATE TABLE `estoque` (
  `id_est` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `quantmin_est` float NOT NULL DEFAULT '0',
  `quantmax_est` float NOT NULL DEFAULT '0',
  `quantatual_est` float DEFAULT NULL,
  `unidademed_est` varchar(45) CHARACTER SET latin1 NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_est`) USING BTREE,
  CONSTRAINT `FK_estoque_1` FOREIGN KEY (`id_est`) REFERENCES `monitorado` (`codigo_mon`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `estoque`
--

/*!40000 ALTER TABLE `estoque` DISABLE KEYS */;
INSERT INTO `estoque` (`id_est`,`quantmin_est`,`quantmax_est`,`quantatual_est`,`unidademed_est`) VALUES 
 (1,300,800,109,'Unidade'),
 (6,50,150,32,'Unidade'),
 (9,200,400,-5,'Unidade'),
 (10,7,40,-7,'Unidade'),
 (33,140,203,58,'Unidade'),
 (64,70,9.6,95,'Metro'),
 (77,30,100,120,'Kilograma'),
 (78,30,100,-2,'Unidade'),
 (88,100,300,27,'Kilograma'),
 (99,10.5,100,57,'Kilograma'),
 (100,100,90,12,'Unidade'),
 (102,0,99,0,'Unidade'),
 (103,0,50,-2,'Unidade'),
 (104,0,33,-4,'Unidade');
/*!40000 ALTER TABLE `estoque` ENABLE KEYS */;


--
-- Definition of table `lote`
--

DROP TABLE IF EXISTS `lote`;
CREATE TABLE `lote` (
  `id_lote` int(10) unsigned NOT NULL,
  `monitorado_lote` int(10) unsigned NOT NULL DEFAULT '0',
  `validade_lote` varchar(15) CHARACTER SET latin1 NOT NULL DEFAULT '0',
  `qntatual_lote` float NOT NULL DEFAULT '0',
  `qntinicial_lote` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_lote`) USING BTREE,
  KEY `produto_lote` (`monitorado_lote`) USING BTREE,
  CONSTRAINT `monitorado_lote` FOREIGN KEY (`monitorado_lote`) REFERENCES `monitorado` (`codigo_mon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `lote`
--

/*!40000 ALTER TABLE `lote` DISABLE KEYS */;
INSERT INTO `lote` (`id_lote`,`monitorado_lote`,`validade_lote`,`qntatual_lote`,`qntinicial_lote`) VALUES 
 (1,1,'19/05/2018',100,100),
 (2,6,'31/09/2017',34,34),
 (3,64,'05/01/2017',100,50),
 (4,100,'05/05/2017',13,40),
 (5,99,'09/04/2001',57,60),
 (6,88,'01/03/2017',29,30),
 (7,77,'01/04/2017',80,80),
 (8,33,'04/04/2018',58,60),
 (38,100,'04/05/2015',0,30),
 (65,1,'09/01/2001',10,50),
 (99,77,'20/02/2018',40,40);
/*!40000 ALTER TABLE `lote` ENABLE KEYS */;


--
-- Definition of table `monitorado`
--

DROP TABLE IF EXISTS `monitorado`;
CREATE TABLE `monitorado` (
  `codigo_mon` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `avenda_mon` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo_mon`) USING BTREE,
  CONSTRAINT `FK_monitorado_1` FOREIGN KEY (`codigo_mon`) REFERENCES `produto` (`codigo_pro`)
) ENGINE=InnoDB AUTO_INCREMENT=106 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `monitorado`
--

/*!40000 ALTER TABLE `monitorado` DISABLE KEYS */;
INSERT INTO `monitorado` (`codigo_mon`,`avenda_mon`) VALUES 
 (1,0),
 (6,0),
 (9,0),
 (10,0),
 (33,1),
 (64,0),
 (77,1),
 (78,0),
 (88,1),
 (99,1),
 (100,1),
 (102,1),
 (103,1),
 (104,1);
/*!40000 ALTER TABLE `monitorado` ENABLE KEYS */;


--
-- Definition of table `notafiscal`
--

DROP TABLE IF EXISTS `notafiscal`;
CREATE TABLE `notafiscal` (
  `id_nf` int(10) unsigned NOT NULL DEFAULT '0',
  `cliente_nf` int(10) unsigned DEFAULT NULL,
  `pagamento_nf` int(10) unsigned NOT NULL,
  `pedido_nf` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_nf`) USING BTREE,
  KEY `FK_pagamento` (`pagamento_nf`),
  KEY `FK_cliente` (`cliente_nf`),
  KEY `FK_pedido1` (`pedido_nf`),
  CONSTRAINT `FK_cliente` FOREIGN KEY (`cliente_nf`) REFERENCES `cliente` (`codigo_cli`),
  CONSTRAINT `FK_pagamento` FOREIGN KEY (`pagamento_nf`) REFERENCES `pagamento` (`id_pag`),
  CONSTRAINT `FK_pedido1` FOREIGN KEY (`pedido_nf`) REFERENCES `pedido` (`codigo_ped`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `notafiscal`
--

/*!40000 ALTER TABLE `notafiscal` DISABLE KEYS */;
INSERT INTO `notafiscal` (`id_nf`,`cliente_nf`,`pagamento_nf`,`pedido_nf`) VALUES 
 (18,4,18,18),
 (19,0,19,19),
 (20,889,20,20),
 (22,889,22,22),
 (23,0,23,23),
 (24,2,24,24),
 (25,0,25,25),
 (26,889,26,26),
 (27,889,27,27),
 (28,3,28,28),
 (29,0,29,29),
 (30,2,30,30),
 (31,2,31,31),
 (32,4,32,32);
/*!40000 ALTER TABLE `notafiscal` ENABLE KEYS */;


--
-- Definition of table `pagamento`
--

DROP TABLE IF EXISTS `pagamento`;
CREATE TABLE `pagamento` (
  `id_pag` int(10) unsigned NOT NULL,
  `pago_pag` tinyint(1) NOT NULL,
  `data_pag` varchar(15) CHARACTER SET latin1 NOT NULL,
  `valor_pag` float NOT NULL,
  `realizacaoDoPagamento_pag` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`id_pag`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pagamento`
--

/*!40000 ALTER TABLE `pagamento` DISABLE KEYS */;
INSERT INTO `pagamento` (`id_pag`,`pago_pag`,`data_pag`,`valor_pag`,`realizacaoDoPagamento_pag`) VALUES 
 (18,1,'20-01-2017',133,'22-01-2017'),
 (19,1,'03-03-2017',190.3,NULL),
 (20,0,'20-01-2017',242.8,NULL),
 (22,0,'21-01-2017',59.7,NULL),
 (23,1,'20-01-2017',67.5,NULL),
 (24,0,'03-03-2017',105.3,NULL),
 (25,1,'04-03-2017',69.1,NULL),
 (26,1,'19-12-2016',88.6,'22-01-2017'),
 (27,1,'01-01-2017',29.5,'22-01-2017'),
 (28,1,'03-02-2017',25.5,'22-01-2017'),
 (29,1,'03-03-2017',12.9,NULL),
 (30,0,'04-03-2017',34,NULL),
 (31,0,'04-03-2017',36.8,NULL),
 (32,0,'04-03-2017',24,NULL);
/*!40000 ALTER TABLE `pagamento` ENABLE KEYS */;


--
-- Definition of table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
CREATE TABLE `pedido` (
  `codigo_ped` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `mesa_ped` int(10) unsigned NOT NULL,
  `cliente_ped` varchar(90) CHARACTER SET latin1 NOT NULL,
  `aberto_ped` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo_ped`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8;

--
-- Dumping data for table `pedido`
--

/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` (`codigo_ped`,`mesa_ped`,`cliente_ped`,`aberto_ped`) VALUES 
 (18,1,'Desconhecido 1',0),
 (19,1,'Pedro Augusto',0),
 (20,8,'Pedro João Filho',0),
 (22,8,'Hello',0),
 (23,1,'Pedro io',0),
 (24,8,'Pedro',0),
 (25,2,'Desconhecido',0),
 (26,2,'Desconhecido 1',0),
 (27,2,'Desconhecido 1',0),
 (28,8,'lek',0),
 (29,8,'Cool',0),
 (30,4,'Didi',0),
 (31,4,'Didi mocó',0),
 (32,1,'Desconhecido',0),
 (33,2,'Desconhecido',1);
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;


--
-- Definition of table `pedidoproduto`
--

DROP TABLE IF EXISTS `pedidoproduto`;
CREATE TABLE `pedidoproduto` (
  `codigo_pp` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `pedido_pp` int(10) unsigned NOT NULL,
  `produto_pp` int(10) unsigned NOT NULL,
  `quant_pp` int(10) unsigned NOT NULL,
  `comentario_pp` varchar(100) NOT NULL,
  `numeracao_pp` int(10) unsigned NOT NULL,
  PRIMARY KEY (`codigo_pp`),
  KEY `FK_pedido` (`pedido_pp`),
  KEY `FK_produto` (`produto_pp`),
  CONSTRAINT `FK_pedido` FOREIGN KEY (`pedido_pp`) REFERENCES `pedido` (`codigo_ped`),
  CONSTRAINT `FK_produto` FOREIGN KEY (`produto_pp`) REFERENCES `produto` (`codigo_pro`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pedidoproduto`
--

/*!40000 ALTER TABLE `pedidoproduto` DISABLE KEYS */;
INSERT INTO `pedidoproduto` (`codigo_pp`,`pedido_pp`,`produto_pp`,`quant_pp`,`comentario_pp`,`numeracao_pp`) VALUES 
 (15,18,5,1,'Fuck off',1),
 (16,18,4,10,'',2),
 (20,19,8,5,'Sem orelha',1),
 (21,19,6,2,'Com gelo',2),
 (22,19,5,1,'Cool',3),
 (24,20,5,10,'OK',2),
 (25,20,10,5,'Nice',3),
 (26,18,10,5,'cool',3),
 (27,20,64,5,'Chill',4),
 (29,22,8,1,'',1),
 (30,22,4,1,'',2),
 (31,22,64,1,'',3),
 (32,22,10,1,'',4),
 (34,19,78,1,'',4),
 (37,20,8,1,'',5),
 (39,23,9,1,'',1),
 (40,23,4,1,'',2),
 (41,23,7,3,'',3),
 (42,24,9,1,'',1),
 (43,24,10,5,'',2),
 (44,24,9,3,'',3),
 (45,24,64,3,'',4),
 (46,25,9,1,'',1),
 (47,25,5,4,'',2),
 (48,25,64,1,'',3),
 (49,26,8,1,'',1),
 (50,26,5,4,'',2),
 (51,26,10,1,'',3),
 (52,27,1,1,'',1),
 (53,27,5,1,'',2),
 (54,27,5,1,'',3),
 (55,28,1,1,'',1),
 (56,28,5,1,'',2),
 (57,28,9,1,'',3),
 (58,29,64,1,'Kid Boo',1),
 (59,29,78,1,'fake news',2),
 (60,30,100,1,'',1),
 (61,30,88,1,'',2),
 (62,30,1,1,'',3),
 (63,30,10,2,'',4),
 (64,31,8,1,'',1),
 (65,31,88,1,'',2),
 (66,25,104,1,'',4),
 (67,32,103,1,'',1);
INSERT INTO `pedidoproduto` (`codigo_pp`,`pedido_pp`,`produto_pp`,`quant_pp`,`comentario_pp`,`numeracao_pp`) VALUES 
 (68,32,103,1,'',2),
 (69,32,104,3,'',3),
 (70,33,100,1,'',1);
/*!40000 ALTER TABLE `pedidoproduto` ENABLE KEYS */;


--
-- Definition of table `produto`
--

DROP TABLE IF EXISTS `produto`;
CREATE TABLE `produto` (
  `codigo_pro` int(10) unsigned NOT NULL,
  `nome_pro` varchar(45) CHARACTER SET latin1 NOT NULL,
  `preco_pro` float NOT NULL,
  `monitorado_pro` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`codigo_pro`),
  KEY `estoque_produto` (`monitorado_pro`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Dumping data for table `produto`
--

/*!40000 ALTER TABLE `produto` DISABLE KEYS */;
INSERT INTO `produto` (`codigo_pro`,`nome_pro`,`preco_pro`,`monitorado_pro`) VALUES 
 (1,'Coca-zero',6.5,1),
 (4,'Porção de Arroz',7.5,0),
 (5,'Porção de batata frita',11.5,0),
 (6,'Gurana 300 mlk Kuat',4.5,1),
 (7,'Lasanha com arroz',17.5,0),
 (8,'Feijoada',33.3,0),
 (9,'Cerveja Itaipava 1L',7.5,1),
 (10,'Sorvete Magnum',9.3,1),
 (33,'Dirty Water',3.5,1),
 (41,'Mr. Green',8.64,0),
 (64,'SAVE ME',9.6,1),
 (77,'Cenoura',90.5,1),
 (78,'ICECREAM',3.3,1),
 (88,'Batata Inglesa',3.5,1),
 (99,'Salcisa',4.5,1),
 (100,'Alface',5.4,1),
 (101,'Be Boop a lula',64.6,0),
 (102,'aeee',2,1),
 (103,'Coca KS',3,1),
 (104,'Guaraná 600',6,1);
/*!40000 ALTER TABLE `produto` ENABLE KEYS */;


--
-- Definition of table `produto_rotina`
--

DROP TABLE IF EXISTS `produto_rotina`;
CREATE TABLE `produto_rotina` (
  `ID_prr` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `produto_prr` int(10) unsigned NOT NULL,
  `rotina_prr` int(10) unsigned NOT NULL DEFAULT '0',
  `quantidade_ppr` float NOT NULL DEFAULT '0',
  PRIMARY KEY (`ID_prr`),
  KEY `FK_Produto_Rotina_1` (`produto_prr`),
  KEY `FK_Produto_Rotina_2` (`rotina_prr`),
  CONSTRAINT `FK_Produto_Rotina_1` FOREIGN KEY (`produto_prr`) REFERENCES `produto` (`codigo_pro`),
  CONSTRAINT `FK_Produto_Rotina_2` FOREIGN KEY (`rotina_prr`) REFERENCES `rotinas` (`ID_rot`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `produto_rotina`
--

/*!40000 ALTER TABLE `produto_rotina` DISABLE KEYS */;
INSERT INTO `produto_rotina` (`ID_prr`,`produto_prr`,`rotina_prr`,`quantidade_ppr`) VALUES 
 (1,33,5,3),
 (2,99,5,3),
 (3,33,6,2),
 (4,99,6,3),
 (5,88,6,1);
/*!40000 ALTER TABLE `produto_rotina` ENABLE KEYS */;


--
-- Definition of table `relatorioproduto`
--

DROP TABLE IF EXISTS `relatorioproduto`;
CREATE TABLE `relatorioproduto` (
  `produto_relp` int(10) unsigned NOT NULL,
  `quantidade_relp` float NOT NULL,
  `datainicio_relp` varchar(45) NOT NULL,
  PRIMARY KEY (`produto_relp`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `relatorioproduto`
--

/*!40000 ALTER TABLE `relatorioproduto` DISABLE KEYS */;
INSERT INTO `relatorioproduto` (`produto_relp`,`quantidade_relp`,`datainicio_relp`) VALUES 
 (103,2,'04/03/2017'),
 (104,3,'04/03/2017');
/*!40000 ALTER TABLE `relatorioproduto` ENABLE KEYS */;


--
-- Definition of table `rotinas`
--

DROP TABLE IF EXISTS `rotinas`;
CREATE TABLE `rotinas` (
  `ID_rot` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `nome_rot` varchar(45) NOT NULL,
  `descricao_rot` varchar(45) NOT NULL,
  PRIMARY KEY (`ID_rot`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1 COMMENT='auxilia na hora de dar baixa no estoque';

--
-- Dumping data for table `rotinas`
--

/*!40000 ALTER TABLE `rotinas` DISABLE KEYS */;
INSERT INTO `rotinas` (`ID_rot`,`nome_rot`,`descricao_rot`) VALUES 
 (5,'Main','_/|\\_'),
 (6,'Boogie','_/|\\_');
/*!40000 ALTER TABLE `rotinas` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
