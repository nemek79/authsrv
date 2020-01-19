DROP DATABASE IF EXISTS authbd;

CREATE DATABASE authbd
  CHARACTER SET utf8
        COLLATE utf8_general_ci;

USE authbd;

CREATE TABLE t_roles
(
	id			INT(8)			NOT NULL AUTO_INCREMENT,
	role 		VARCHAR(32)		NOT NULL,

	PRIMARY KEY(id)

)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO roles  VALUES (null,'ROLE_USER');
INSERT INTO roles VALUES (null,'ROLE_ADMIN');

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

INSERT INTO usuarios (username,password,enabled,nombre, apellidos, email) VALUES ('david','$2a$10$sEvh4AVbaNnSDokcOI7tDecWWoutEL/ULITe9xzwGVylC/h87dIiW',1,'david','alonso sanchez','davidalonso79@gmail.com');

CREATE TABLE tr_usuarios_roles(

	usuario_id			INT(8)			NOT NULL,
	role_id	 			INT(8)			NOT NULL


)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO tr_usuarios_roles VALUES (1,1);
INSERT INTO tr_usuarios_roles VALUES (1,2);