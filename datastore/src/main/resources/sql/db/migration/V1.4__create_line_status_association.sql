CREATE EXTENSION IF NOT EXISTS "uuid-ossp";

CREATE TABLE line_status_history
(
    id                  UUID      NOT NULL DEFAULT uuid_generate_v4(),
    verification_id     UUID      NOT NULL REFERENCES verification (id),
    company_line_id     VARCHAR   NOT NULL,
    company_slug        VARCHAR   NOT NULL,
    status_slug         VARCHAR   NOT NULL REFERENCES status (slug),
    created_at          timestamp NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    CONSTRAINT LINE_FK FOREIGN KEY (company_line_id, company_slug) REFERENCES line (company_line_id, company_slug)
);

CREATE INDEX line_status_history_verification_id_idx ON line_status_history (verification_id);
CREATE INDEX line_status_line_company_line_id_idx ON line_status_history (company_line_id);
CREATE INDEX line_status_line_company_slug_idx ON line_status_history (company_slug);
CREATE INDEX line_status_status_slug_idx ON line_status_history (status_slug);
