create table cliente (
    id integer not null auto_increment,
    nome varchar(255) not null,
    cpf varchar(255)
    email varchar(255) not null,
    telefone varchar(20) not null,
    primary key (id)
)