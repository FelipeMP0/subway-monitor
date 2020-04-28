CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE line_status_history
(
    id                  UUID      NOT NULL DEFAULT uuid_generate_v4,
    line_id             UUID      NOT NULL REFERENCES line (id),
    status_id           UUID      NOT NULL REFERENCES status (id),
    verification_number INTEGER   NOT NULL,
    created_at          timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE INDEX line_status_line_id_idx ON line_status_history (line_id);
CREATE INDEX line_status_status_id_idx ON line_status_history (status_id);
