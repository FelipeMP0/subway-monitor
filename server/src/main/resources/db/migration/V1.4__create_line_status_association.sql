CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE line_status_history
(
    id                  UUID      NOT NULL DEFAULT uuid_generate_v4(),
    verification_id     UUID      NOT NULL REFERENCES verification (id),
    line_id             UUID      NOT NULL REFERENCES line (id),
    status_id           UUID      NOT NULL REFERENCES status (id),
    created_at          timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id)
);

CREATE INDEX line_status_history_verification_id_idx ON line_status_history (verification_id);
CREATE INDEX line_status_line_id_idx ON line_status_history (line_id);
CREATE INDEX line_status_status_id_idx ON line_status_history (status_id);
