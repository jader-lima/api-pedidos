
create database Pedidos
use Pedido

create table Produtos(
	[Sk_Produto] [int] IDENTITY(1,1) NOT NULL,
	[Descricao] [varchar](100) NOT NULL,
	[Valor] float not null,
	[Dt_Alteracao] [datetime] NULL,
	[Dt_Inclusao] [datetime] NULL
)

alter table Produtos add constraint PK_Produto  primary key  (Sk_Produto)

create table Movimento(
	[Sk_Movimento] [int] IDENTITY(1,1) NOT NULL,
	[Quantidade] int not null,
	[Tipo] char not null,
	[Dt_Inclusao] [datetime] NULL,
	[Sk_Produto] int NOT NULL
)

alter table Movimento add constraint PK_Movimento  primary key  (Sk_Movimento)
ALTER TABLE Movimento  WITH CHECK ADD  CONSTRAINT FK_Movimento_Produto FOREIGN KEY(Sk_Produto) REFERENCES Produtos (Sk_Produto)

create table Estoque(
	Sk_Estoque [int] IDENTITY(1,1) NOT NULL,
	Quantidade int not null,
	Dt_Inclusao [datetime] NULL,
	Sk_Produto int NOT NULL
)


alter table Estoque add constraint PK_Estoques  primary key  (Sk_Estoque)
ALTER TABLE Estoque  WITH CHECK ADD  CONSTRAINT FK_Estoques_Produto FOREIGN KEY(Sk_Produto) REFERENCES Produtos (Sk_Produto)



create table Cliente(
	[Sk_Cliente] [int] IDENTITY(1,1) NOT NULL,
	[Nome] [varchar](100) NOT NULL,	
	[Dt_Alteracao] [datetime] NULL,
	[Dt_Inclusao] [datetime]  NULL
)

alter table Cliente add constraint PK_Cliente  primary key  (Sk_Cliente)


create table Pedidos(
	[Sk_Pedido] [int] IDENTITY(1,1) NOT NULL,	
	[Dt_Pedido] [datetime] NULL,	
	[Dt_Alteracao] [datetime] NULL,
	[Dt_Inclusao] [datetime] NULL,
	[Sk_Cliente] [int] NOT NULL
)


alter table Pedidos add constraint PK_Pedidos  primary key  (Sk_Pedido)
ALTER TABLE Pedidos  WITH CHECK ADD  CONSTRAINT FK_Pedidos_Cliente FOREIGN KEY(Sk_Cliente) REFERENCES Cliente (Sk_Cliente)


create table ItensPedidos(
	[Sk_ItensPedido] [int] IDENTITY(1,1) NOT NULL,	
	Qtd int not null,
	Valor float not null,
	[Dt_Alteracao] [datetime] NULL,
	[Dt_Inclusao] [datetime] NULL,
	[Sk_Produto] [int]  NOT NULL,	
	[Sk_Pedido] [int]  NOT NULL,	
)

alter table ItensPedidos add constraint PK_ItensPedidos  primary key  (Sk_ItensPedido)
ALTER TABLE ItensPedidos  WITH CHECK ADD  CONSTRAINT FK_ItensPedidos_Pedido FOREIGN KEY(Sk_Pedido) REFERENCES Pedidos (Sk_Pedido)
ALTER TABLE ItensPedidos  WITH CHECK ADD  CONSTRAINT FK_ItensPedidos_Produto FOREIGN KEY(Sk_Produto) REFERENCES Produtos (Sk_Produto)



create table Usuarios(
	[Sk_Usuario] [int] IDENTITY(1,1) NOT NULL,	
	[Nome] [varchar](100) NOT NULL,
	[Email] [varchar](100) NOT NULL,
	[Senha] [varchar](100) NOT NULL	
)

alter table Usuarios add constraint PK_Usuarios  primary key  (Sk_Usuario)

create table Perfil(
	[Sk_Perfil] [int] IDENTITY(1,1) NOT NULL,	
	[Nome] [varchar](100) NOT NULL
)

alter table Perfil add constraint PK_Perfil  primary key  (Sk_Perfil)


create table UsuarioPerfil(
	[Sk_Usuario] [int]  NOT NULL,	
	[Sk_Perfil] [int]  NOT NULL	
)

ALTER TABLE UsuarioPerfil  WITH CHECK ADD  CONSTRAINT FK_UsuarioPerfil_Usuario FOREIGN KEY(Sk_Usuario) REFERENCES Usuarios (Sk_Usuario)
ALTER TABLE UsuarioPerfil  WITH CHECK ADD  CONSTRAINT FK_UsuarioPerfil_Perfil FOREIGN KEY(Sk_Perfil) REFERENCES Perfil (Sk_Perfil)
ALTER TABLE UsuarioPerfil  WITH CHECK ADD  CONSTRAINT PK_UsuarioPerfil_Usuario PRIMARY KEY(Sk_Usuario, Sk_Perfil) 




insert into Produtos values('PS4 Slin',2500.00,null,getdate())

insert into Produtos values('XBOX ONE',2600.00,null,getdate())

insert into Usuarios values('aluno','aluno@email.com.br','$2a$10$r.NrbV1zBmoieaxbrkmDxuT9qR9o7saxMiijoa1n1F8Lpbp/ExJyG')
insert into Usuarios values('administrador','administrador@email.com.br','$2a$10$NHN9leObWMMponKkuOBj9uYkVLqauIQ0o2I9jwBembKssTtzVN3ai')

insert into Perfil values('ROLE_LEITURA')
insert into Perfil values('ROLE_ADMINISTRACAO')

insert into UsuarioPerfil values(1,1)
insert into UsuarioPerfil values(2,2)


