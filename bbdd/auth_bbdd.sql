
USE authbd;

CREATE TABLE t_roles
(
	id			INT(8)			NOT NULL AUTO_INCREMENT,
	role 		VARCHAR(32)		NOT NULL,

	PRIMARY KEY(id)

)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE t_usuarios 
(
	id						INT(8)			NOT NULL AUTO_INCREMENT,
	username				VARCHAR(32)		NOT NULL,
	password				VARCHAR(128)	NOT NULL,
	enabled					TINYINT			NOT NULL,
	nombre					VARCHAR(64)		,
	apellidos				VARCHAR(128)	,
	email					VARCHAR(256)	,
	
	PRIMARY KEY (id)
	
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE tr_usuarios_roles(

	usuario_id			INT(8)			NOT NULL,
	role_id	 			INT(8)			NOT NULL


)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE t_aplicaciones 
(
	id						INT(8)			NOT NULL AUTO_INCREMENT,
	client_id				VARCHAR(32)		NOT NULL,
	client_secret			VARCHAR(128)	NOT NULL,
	client_short			VARCHAR(4)		NOT NULL,
	
	PRIMARY KEY(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO t_aplicaciones VALUES (null,'TESTAPP','$2a$10$sEvh4AVbaNnSDokcOI7tDecWWoutEL/ULITe9xzwGVylC/h87dIiW','TEST');

INSERT INTO t_usuarios VALUES
(null, 'admin', '$2y$12$nXa42EI76ZZluJ.bIhzOMedyMiPJwr9huhvbnTropzgB97WL7aae6',1,'admin',null,'admin@vir2al.es');

INSERT INTO t_usuarios (username,password,enabled,nombre, apellidos, email) VALUES 
('david','$2y$12$PNrcXMyeSoVqyubU6Ahvyug/Ap5cKDASRpsi6JcAb8hoK8WWQeVQW',1,'david','alonso sanchez','davidalonso79@gmail.com');

INSERT INTO t_usuarios (username,password,enabled,nombre, apellidos, email) VALUES 
('maira','$2y$12$boRX5w2fSPPcMcieVjehfO.g777Zt9TB5A2Ocor6tYOU7A7NpBq8a',1,'Maira Ebelina','Escalante Reyes','mairaescalante1912@gmail.com');

INSERT INTO t_roles VALUES (null,'ROLE_ADMIN');
INSERT INTO t_roles  VALUES (null,'ROLE_USER');


INSERT INTO tr_usuarios_roles VALUES 
(1,1);

INSERT INTO tr_usuarios_roles VALUES 
(1,2);

INSERT INTO tr_usuarios_roles VALUES 
(2,2);

INSERT INTO tr_usuarios_roles VALUES 
(3,2);

