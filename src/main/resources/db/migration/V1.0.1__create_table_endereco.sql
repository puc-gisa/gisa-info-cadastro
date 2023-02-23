create table endereco
(
    id           serial primary key,
    logradouro   varchar(255) not null,
    numero       integer      not null,
    complemento  varchar(255),
    bairro       varchar(255) not null,
    cidade       varchar(255) not null,
    estado       char(2)      not null,
    cep          char(8)      not null,
    id_associado integer      not null unique references associado (id)
);


