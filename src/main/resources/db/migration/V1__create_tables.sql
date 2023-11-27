CREATE TABLE tbl_cliente(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL
);

CREATE TABLE tbl_pedido(
codigo BIGINT NOT NULL PRIMARY KEY,
data_cadastro DATE NOT NULL,
valor_total DECIMAL(8,2) NOT NULL,
valor_desconto DECIMAL(8,2) NOT NULL,
valor_pagamento DECIMAL(8,2) NOT NULL,
id_cliente BIGINT NOT NULL
);

CREATE TABLE tbl_produto(
id BIGINT AUTO_INCREMENT PRIMARY KEY,
nome VARCHAR(100) NOT NULL,
valor DECIMAL(8,2) NOT NULL,
quantidade BIGINT NOT NULL,
pedido_id BIGINT NULL
);
