CREATE TABLE transfer_documents(
            id BIGSERIAL PRIMARY KEY,
            document_number VARCHAR(20) UNIQUE NOT NULL,
            transfer_request_id BIGINT UNIQUE NOT NULL REFERENCES transfer_requests(id),
            created_at TIMESTAMP NOT NULL,
            player_id BIGINT NOT NULL REFERENCES players(id),
            source_team_id BIGINT REFERENCES teams(id),
            target_team_id BIGINT NOT NULL REFERENCES teams(id),
            amount NUMERIC(10,2) NOT NULL,
            CONSTRAINT ck_document_amount CHECK (amount >= 0)
);

CREATE SEQUENCE seq_document_number START 1;