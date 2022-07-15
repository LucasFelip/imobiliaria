create table aluguel{
    id integer not null auto_increment,
    locacao_id integer not null,
    dataVencimento date not null,
    valorPago float not null,
    observacao varchar(255),
    primary key (id)
};

after table aluguel add contraint fk_aluquel_locacao
    foreign key (locacao_id) references locacao (id);