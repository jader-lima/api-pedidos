use pedidos;

create table Produtos(
	Sk_Produto int NOT NULL AUTO_INCREMENT,
	Descricao varchar(100) NOT NULL,
	Valor float not null,
	Dt_Alteracao datetime NULL,
	Dt_Inclusao datetime NULL,
	primary key (Sk_Produto)
);


create table Movimento(
	Sk_Movimento int NOT NULL AUTO_INCREMENT,
	Quantidade int not null,
	Tipo char not null,
	Dt_Inclusao datetime NULL,
	Sk_Produto int NOT NULL,
	primary key (Sk_Movimento)
);

ALTER TABLE Movimento  ADD  CONSTRAINT FK_Movimento_Produto FOREIGN KEY(Sk_Movimento) REFERENCES Produtos (Sk_Produto);


create table Estoque(
	Sk_Estoque int NOT NULL AUTO_INCREMENT,
	Quantidade int not null,
	Dt_Inclusao [datetime] NULL,
	Sk_Produto int NOT NULL,
	primary key (Sk_Estoque)
)

ALTER TABLE Estoque  ADD  CONSTRAINT FK_Estoques_Produto FOREIGN KEY(Sk_Produto) REFERENCES Produtos (Sk_Produto);



create table Cliente(
	Sk_Cliente int NOT NULL AUTO_INCREMENT,
	Nome varchar(100) NOT NULL,	
	Dt_Alteracao datetime NULL,
	Dt_Inclusao datetime  NULL,
	primary key (Sk_Cliente)
);


create table Pedidos(
	Sk_Pedido int NOT NULL AUTO_INCREMENT,	
	Dt_Pedido datetime NULL,	
	Dt_Alteracao datetime NULL,
	Dt_Inclusao datetime NULL,
	Sk_Cliente int NOT NULL,
	primary key (Sk_Pedido)
);

ALTER TABLE Pedidos  ADD CONSTRAINT FK_Pedidos_Cliente FOREIGN KEY(Sk_Cliente) REFERENCES Cliente (Sk_Cliente);


create table ItensPedidos(
	Sk_ItensPedido int NOT NULL AUTO_INCREMENT,	
	Qtd int not null,
	Valor float not null,
	Dt_Alteracao datetime NULL,
	Dt_Inclusao datetime NULL,
	Sk_Produto int  NOT NULL,	
	Sk_Pedido int  NOT NULL,	
	primary key (Sk_ItensPedido)
);


ALTER TABLE ItensPedidos  ADD  CONSTRAINT FK_ItensPedidos_Pedido FOREIGN KEY(Sk_Pedido) REFERENCES Pedidos (Sk_Pedido);
ALTER TABLE ItensPedidos  ADD  CONSTRAINT FK_ItensPedidos_Produto FOREIGN KEY(Sk_Produto) REFERENCES Produtos (Sk_Produto);



create table Usuarios(
	Sk_Usuario int NOT NULL AUTO_INCREMENT,	
	Nome varchar(100) NOT NULL,
	Email varchar(100) NOT NULL,
	Senha varchar(100) NOT NULL,
	primary key (Sk_Usuario)	
);



create table Perfil(
	Sk_Perfil int NOT NULL AUTO_INCREMENT,	
	Nome varchar(100) NOT NULL,
	primary key (Sk_Perfil) 
);


create table UsuarioPerfil(
	Sk_Usuario int  NOT NULL,	
	Sk_Perfil int  NOT NULL	
);


ALTER TABLE UsuarioPerfil  ADD  CONSTRAINT FK_UsuarioPerfil_Usuario FOREIGN KEY(Sk_Usuario) REFERENCES Usuarios (Sk_Usuario);
ALTER TABLE UsuarioPerfil  ADD  CONSTRAINT FK_UsuarioPerfil_Perfil FOREIGN KEY(Sk_Perfil) REFERENCES Perfil (Sk_Perfil);
ALTER TABLE UsuarioPerfil  ADD  CONSTRAINT PK_UsuarioPerfil_Usuario PRIMARY KEY(Sk_Usuario, Sk_Perfil);


SET character_set_client = utf8;
SET character_set_connection = utf8;
SET character_set_results = utf8;
SET collation_connection = utf8_general_ci;

insert into Usuarios (Nome, Email, Senha) values('aluno','aluno@email.com.br','$2a$10$r.NrbV1zBmoieaxbrkmDxuT9qR9o7saxMiijoa1n1F8Lpbp/ExJyG');
insert into Usuarios (Nome, Email, Senha) values('administrador','administrador@email.com.br','$2a$10$NHN9leObWMMponKkuOBj9uYkVLqauIQ0o2I9jwBembKssTtzVN3ai');

#inserts

insert into Perfil (Nome) values('ROLE_LEITURA');
insert into Perfil (Nome) values('ROLE_ADMINISTRACAO');

insert into UsuarioPerfil (Sk_Usuario, Sk_Perfil) values(2,2);


insert into Produtos (Descricao , Valor , Dt_Alteracao , Dt_Inclusao) values('PS4 Slin',2500.00,NULL,NOW());
insert into Produtos (Descricao , Valor , Dt_Alteracao , Dt_Inclusao) values('Batata',5.00,NULL,NOW());




