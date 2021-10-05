CREATE SCHEMA api_desafioAPB_loja;
USE api_desafioAPB_loja;

CREATE TABLE `endereco` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `rua` VARCHAR(255) NOT NULL,
  `numero` VARCHAR(8) DEFAULT NULL,
  `bairro` varchar(50) DEFAULT NULL,
  `cep` VARCHAR(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `cliente` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(200) DEFAULT NULL,
  `cpf` VARCHAR(14) DEFAULT NULL,
  `data_nascimento` DATE DEFAULT NULL,
  `id_endereco_fk` INT DEFAULT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_endereco_fk`) REFERENCES `endereco` (`id`) ON UPDATE CASCADE ON DELETE SET NULL
);

CREATE TABLE `pedido` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `valor_total` DOUBLE DEFAULT NULL,
  `data_pedido` TIMESTAMP DEFAULT NULL,
  `id_cliente_fk` INT NOT NULL,
  PRIMARY KEY (`id`),
  FOREIGN KEY (`id_cliente_fk`) REFERENCES `cliente` (`id`) ON UPDATE CASCADE ON DELETE CASCADE
);

INSERT INTO `endereco` (`id`, `rua`, `numero`, `bairro`, `cep`) VALUES
(1, 'Rua 1', '123', 'Bairro 1', '62.951-847'),
(2, 'Rua 2', '95', 'Bairro 2', '78.456-320'),
(3, 'Rua 3', '75A', 'Bairro 3', '34.842-123');

INSERT INTO `cliente` (`id`, `nome`, `cpf`, `data_nascimento`, `id_endereco_fk`) VALUES
(1, 'Cliente 1', '123.456.789-00', '2000-05-13', 1),
(2, 'Cliente 2', '987.654.321-00', '1995-12-29', 2),
(3, 'Cliente 3', '123.789.456-52', '1984-07-20', 2);

INSERT INTO `pedido` (`id`, `valor_total`, `data_pedido`, `id_cliente_fk`) VALUES
(1, '120.56', '2021-05-13', 1),
(2, '546.52', '2021-12-29', 2);

