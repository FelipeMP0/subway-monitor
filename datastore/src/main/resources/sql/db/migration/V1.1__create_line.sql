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
    company_line_id VARCHAR   NOT NULL,
    company_slug    VARCHAR   NOT NULL REFERENCES company (slug) ON DELETE CASCADE,
    name            VARCHAR   NOT NULL,
    created_at      timestamp NOT NULL DEFAULT NOW(),
    updated_at      timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (company_line_id, company_slug)
);

CREATE INDEX line_company_idx ON line (company_slug);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON line
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
