create table imovel{
    id integer not null auto_increment,
    tipoImovel varchar(255) not null,
    endereco varchar(255) not null,
    cep varchar(255) not null,
    dormitorio integer,
    banheiro integer,
    suites integer,
    metragem integer,
    valorAluguel float not null,
    observacao varchar(255),
    primary key (id)
}