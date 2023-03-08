create table plano_saude
(
    id                serial primary key,
    id_associado      integer not null references associado (id),
    codigo_tipo_plano integer not null,
    data_inicio       date    not null,
    data_fim          date
);