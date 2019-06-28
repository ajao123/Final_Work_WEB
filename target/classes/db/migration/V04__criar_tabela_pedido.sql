CREATE TABLE pedido(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    idcliente BIGINT(20),
    idprato BIGINT(20),
    name VARCHAR(50),
    price DOUBLE,
    selecionado BOOLEAN
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
