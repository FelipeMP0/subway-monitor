create or replace function trigger_set_timestamp()
    RETURNS trigger AS
$$
begin
    NEW.updated_at = NOW();
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TABLE line
(
    id         UUID      NOT NULL DEFAULT uuid_generate_v4(),
    slug       VARCHAR   NOT NULL,
    name       VARCHAR   NOT NULL,
    company_id UUID      NOT NULL REFERENCES company (id) ON DELETE CASCADE,
    created_at timestampz NOT NULL DEFAULT NOW(),
    updated_at timestampz NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE INDEX line_slug_idx ON line (slug);
CREATE INDEX line_company_idx ON line (company_id);

CREATE TRIGGER set_timestamp
    BEFORE UPDATE
    ON line
    FOR EACH ROW
EXECUTE PROCEDURE trigger_set_timestamp();
