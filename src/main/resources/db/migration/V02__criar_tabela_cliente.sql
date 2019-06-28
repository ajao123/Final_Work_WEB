CREATE TABLE cliente(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    cpf BIGINT(20),
    name VARCHAR(50),
    datebirth DATE,
    endereco VARCHAR(50),
    email VARCHAR(50),
    senha VARCHAR(50)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;