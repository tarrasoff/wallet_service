CREATE TABLE wallets
(
    id   UUID PRIMARY KEY,
    operation_type      VARCHAR(50)  NOT NULL,
    amount    double precision NOT NULL
);