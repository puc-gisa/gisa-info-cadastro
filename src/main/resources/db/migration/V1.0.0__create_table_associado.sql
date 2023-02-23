create table associado
(
    id               serial primary key,
    nome             varchar(255) not null,
    data_nascimento  date         not null,
    email            varchar(255) not null unique,
    cpf              varchar(11)  not null unique,
    data_criacao     timestamp    not null,
    data_atualizacao timestamp
);


