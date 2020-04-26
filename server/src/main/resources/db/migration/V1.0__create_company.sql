CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

create or replace function trigger_set_timestamp()
    RETURNS trigger AS
$$
begin
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE company
(
    id         UUID      NOT NULL DEFAULT uuid_generate_v4(),
    name       VARCHAR   NOT NULL,
    created_at timestamp NOT NULL DEFAULT NOW(),
    updated_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON company
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
