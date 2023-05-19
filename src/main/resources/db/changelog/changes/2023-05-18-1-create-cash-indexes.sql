--liquibase formatted sql
--changeset argenta:create-cash-indexes
CREATE INDEX idx_cash_type ON cash (type);
CREATE INDEX idx_cash_date ON cash (date);
--rollback DROP TABLE cash