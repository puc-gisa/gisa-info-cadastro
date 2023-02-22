create table associado
(
    id               serial primary key,
    nome             varchar(255) not null,
    data_nascimento  date         not null,
    email            varchar(255) not null unique,
    data_criacao     timestamp    not null,
    data_atualizacao timestamp
);

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


