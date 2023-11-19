DO $$
BEGIN
  IF NOT EXISTS (SELECT 1 FROM pg_database WHERE datname = 'point_transaction') THEN
    CREATE DATABASE point_transaction;
END IF;
END $$;

CREATE TABLE customers (
    id BIGINT PRIMARY KEY,
    account_id BIGINT NOT NUll,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE transactions (
    id BIGINT PRIMARY KEY,
    account_id BIGINT NOT NULL,
    transaction_date DATE NOT NULL,
    description VARCHAR(255) NOT NULL,
    debit_credit_status VARCHAR(255) NOT NULL,
    amount FLOAT NOT NULL,
    balance FLOAT NOT NULL
);

CREATE TABLE points (
    id BIGINT PRIMARY KEY,
    points BIGINT NOT NULL,
    account_id BIGINT NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS customers_seq INCREMENT 1 START 1 OWNED BY customers.account_id;
CREATE SEQUENCE IF NOT EXISTS transactions_seq INCREMENT 1 START 1 OWNED BY transactions.id;
CREATE SEQUENCE IF NOT EXISTS points_seq INCREMENT 1 START 1 OWNED BY points.id;

ALTER TABLE transactions ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES customers(id);
ALTER TABLE points ADD CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES customers(id);

INSERT INTO
    customers(id, account_id, name)
    VALUES (1, 1, 'Agun');

INSERT INTO
    transactions(id, account_id, transaction_date, description, debit_credit_status, amount, balance)
    VALUES (1, 1, CURRENT_DATE, 'Setor Tunai', 'C', 1000000, 1000000);

INSERT INTO points(id, account_id, points) VALUES (1, 1, 1000);
