CREATE TABLE transactions (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    account_id VARCHAR(255) NOT NULL,
    amount DOUBLE PRECISION NOT NULL,
    merchant VARCHAR(255) NOT NULL,
    mcc VARCHAR(255) NOT NULL
);