CREATE TABLE players (
                       id BIGSERIAL PRIMARY KEY ,
                       team_id BIGINT NOT NULL REFERENCES teams (id),
                       first_name VARCHAR(150) NOT NULL,
                       last_name VARCHAR(150) NOT NULL,
                       position VARCHAR(2) NOT NULL,
                       shirt_number INTEGER NOT NULL,
                       birth_date date NOT NULL,
                       market_value INTEGER NOT NULL,
                       CONSTRAINT ck_player_position CHECK (position IN ('GK', 'DF', 'MF', 'FW'))

);
