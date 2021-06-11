CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE status
(
    slug       VARCHAR   NOT NULL,
    name       VARCHAR   NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (slug)
);
