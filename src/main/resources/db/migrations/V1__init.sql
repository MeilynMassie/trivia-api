CREATE TABLE IF NOT EXISTS players (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255),
    score INTEGER NOT NULL DEFAULT 0
);

CREATE TABLE IF NOT EXISTS trivia_question (
    id BIGINT PRIMARY KEY,
    category VARCHAR(100),
    question TEXT,
    answer VARCHAR(255),
    choices JSONB
);

CREATE SEQUENCE IF NOT EXISTS trivia_question_id_seq START WITH 1 INCREMENT BY 1;

ALTER TABLE trivia_question
    ALTER COLUMN id SET DEFAULT nextval('trivia_question_id_seq');
