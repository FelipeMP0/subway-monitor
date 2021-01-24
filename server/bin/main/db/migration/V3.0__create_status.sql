CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE status
(
    id UUID NOT NULL DEFAULT uuid_generate_v4(),
    slug VARCHAR NOT NULL,
    name VARCHAR NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE INDEX status_slug_idx ON status (slug);
