CREATE TABLE compra(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    idcliente BIGINT(20),
    pratos VARCHAR(100),
    result DOUBLE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;