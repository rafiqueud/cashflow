--liquibase formatted sql
--changeset argenta:create-template-cash-flow-table
CREATE TABLE IF NOT EXISTS cash
(
    id          uuid      NOT null PRIMARY KEY,
    description varchar   NULL,
    type        varchar   NULL,
    amount      NUMERIC(10, 2),
    date        TIMESTAMP NOT null DEFAULT now()
);
--rollback DROP TABLE cash