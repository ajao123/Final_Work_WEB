CREATE TABLE gerente(
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    login VARCHAR(20),
    senha VARCHAR(20)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

insert into gerente(login, senha) values('admin', 'admin'); 