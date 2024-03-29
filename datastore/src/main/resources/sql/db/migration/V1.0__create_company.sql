CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
    RETURNS trigger AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE company
(
    slug       VARCHAR   NOT NULL,
    name       VARCHAR   NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    updated_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (slug)
);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON company
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
