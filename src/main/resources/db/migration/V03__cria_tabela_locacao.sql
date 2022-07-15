create table locacao {
    id integer not null auto_increment,
    imovel_id integer not null,
    cliente_id integer not null,
    ativo boolean,
    dataInicio date not null,
    dataFim date not null,
    diaVencimento integer not null,
    percentuaMulta float not null,
    valorAluguel float not null,
    observacao varchar(255),
    primary key (id)
};


after table locacao add contraint fk_locacao_cliente
    foreign key (cliente_id) references cliente (id);

after table locacao add contraint fk_locacao_imovel
    foreign key (imovel_id) references imovel (id);