CREATE TABLE `imovel` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bairro` varchar(100) NOT NULL,
  `cep` varchar(11) NOT NULL,
  `cidade` varchar(100) NOT NULL,
  `descricao` varchar(256) NOT NULL,
  `logradouro` varchar(100) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `numero` int NOT NULL,
  `url_foto` varchar(100) NOT NULL,
  `valor` float NOT NULL,
  `imobiliaria_id` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKgrfo1vhexdm6he6aaq60958nw` (`imobiliaria_id`),
  CONSTRAINT `FKgrfo1vhexdm6he6aaq60958nw` FOREIGN KEY (`imobiliaria_id`) REFERENCES `imobiliaria` (`id`)
);