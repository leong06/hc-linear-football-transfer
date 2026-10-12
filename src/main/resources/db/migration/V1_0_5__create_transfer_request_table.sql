CREATE TABLE transfer_requests (
                         id BIGSERIAL PRIMARY KEY ,
                         created_at DATE NOT NULL,
                         expires_at DATE NOT NULL,
                         player_id BIGINT NOT NULL REFERENCES players(id),
                         source_team_id BIGINT REFERENCES teams(id),
                         target_team_id BIGINT NOT NULL REFERENCES teams(id),
                         amount NUMERIC(10,2) NOT NULL,
                         status VARCHAR(20) NOT NULL,
                         CONSTRAINT ck_transfer_amount CHECK (amount >= 0),
                         CONSTRAINT ck_status_text CHECK (status in ('OPEN', 'ACCEPTED', 'REJECTED')),
                         CONSTRAINT ck_expires_after_created CHECK (expires_at >= created_at),
                         CONSTRAINT ck_source_team CHECK (source_team_id IS NULL OR source_team_id <> target_team_id)

);

CREATE INDEX idx_transfer_player ON transfer_requests(player_id);
CREATE INDEX idx_transfer_target_team ON transfer_requests(target_team_id);
CREATE INDEX idx_transfer_source ON transfer_requests(source_team_id);