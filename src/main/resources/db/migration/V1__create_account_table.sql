CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE account (
    id UUID PRIMARY KEY DEFAULT uuid_generate_v4(),
    account VARCHAR(255) NOT NULL
);