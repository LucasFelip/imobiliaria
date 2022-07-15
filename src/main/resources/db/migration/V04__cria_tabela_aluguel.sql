create table aluguel(
    id integer not null auto_increment,
    locacao_id integer not null,
    dataVencimento date not null,
    valorPago float not null,
    observacao varchar(255),
    primary key (id)
);