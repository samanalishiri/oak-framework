-- CREATE SCHEMA IF NOT EXISTS security;

create table USER_INFO (
  ID                   bigint       not null,
  NON_EXPIRED          BOOLEAN,
  NON_LOCKED           BOOLEAN,
  PASSWORD_NON_EXPIRED BOOLEAN,
  EMAIL                varchar(255) not null,
  ENABLED              BOOLEAN,
  IMAGE                blob,
  PASSWD               varchar(255) not null,
  BIRTH_DATE           date         not null,
  BIRTH_PLACE          varchar(255) not null,
  ID_CARD_NO           varchar(255) not null,
  ID_CARD_SERIAL       varchar(255) not null,
  NAME                 varchar(255) not null,
  NATIONAL_CODE        varchar(255) not null,
  SURNAME              varchar(255) not null,
  USERNAME             varchar(255) not null,
  primary key (ID)
);