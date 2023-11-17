DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'point_transaction') THEN
    CREATE DATABASE point_transaction;
END IF;
END $$;

CREATE TABLE customers (
    account_id BIGINT PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY,
    account_id BIGINT,
    transaction_date DATE,
    description VARCHAR(255),
    debit_credit_status VARCHAR(255),
    amount NUMERIC,
    balance NUMERIC
);

CREATE SEQUENCE IF NOT EXISTS customers_seq INCREMENT 1 START 1 OWNED BY customers.account_id;
CREATE SEQUENCE IF NOT EXISTS transactions_seq INCREMENT 1 START 1 OWNED BY transactions.id;

ALTER TABLE transactions ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES users(account_id);

