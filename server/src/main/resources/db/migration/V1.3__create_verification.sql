CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE verification
(
    id         UUID      NOT NULL DEFAULT uuid_generate_v4(),
    created_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);
