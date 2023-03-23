create table autorizacao_exame
(
    id                     serial primary key,
    id_associado           integer     not null references associado (id),
    codigo_exame           varchar(20) not null,
    data_exame             date        not null,
    data_solicitacao       timestamp   not null,
    crm_medico_solicitante varchar(20) not null,
    codigo_situacao        integer     not null,
    data_validade          date,
    justificativa          text
);