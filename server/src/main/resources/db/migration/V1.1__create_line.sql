CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE OR REPLACE FUNCTION trigger_set_timestamp()
    RETURNS trigger AS
$$
BEGIN
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE line
(
    id         UUID      NOT NULL DEFAULT uuid_generate_v4(),
    slug       VARCHAR   NOT NULL,
    name       VARCHAR   NOT NULL,
    number     INTEGER   NOT NULL,
    company_id UUID      NOT NULL REFERENCES company (id) ON DELETE CASCADE,
    created_at timestamp NOT NULL DEFAULT NOW(),
    updated_at timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE INDEX line_slug_idx ON line (slug);
CREATE INDEX line_company_idx ON line (company_id);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON line
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
