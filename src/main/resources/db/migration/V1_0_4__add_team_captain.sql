ALTER TABLE players ADD COLUMN captain BOOLEAN NOT NULL DEFAULT FALSE;
ALTER TABLE players ADD CONSTRAINT ck_captain_player CHECK (NOT captain OR team_id IS NOT NULL);
CREATE UNIQUE INDEX uq_one_captain_per_team ON players (team_id) WHERE captain = TRUE;