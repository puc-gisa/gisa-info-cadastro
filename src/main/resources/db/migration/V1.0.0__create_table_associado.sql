CREATE TABLE ASSOCIADO
(
    ID         serial,
    FULL_NAME  varchar(300) not null,
    BIRTH_DATE date         not null,
    EMAIL      varchar(255) not null unique,
    CREATED_AT timestamp    not null,
    UPDATED_AT timestamp
);
