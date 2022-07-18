CREATE TABLE `imobiliaria` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cnpj` varchar(18) NOT NULL,
  `descricao` varchar(256) NOT NULL,
  `email` varchar(45) NOT NULL,
  `nome` varchar(100) NOT NULL,
  `senha` varchar(16) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_88ewpplbqocvwbj25rtqcm4y6` (`cnpj`),
  UNIQUE KEY `UK_onsmje3udajti5fj6ji43gky1` (`email`)
);