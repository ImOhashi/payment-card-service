CREATE TABLE amount (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    type VARCHAR(255) NOT NULL,
    value DOUBLE PRECISION NOT NULL,
    account_id UUID NOT NULL,
    CONSTRAINT fk_account
        FOREIGN KEY (account_id)
        REFERENCES account(id)
        ON DELETE CASCADE
);