-- Players
CREATE TABLE IF NOT EXISTS player (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    score INTEGER NOT NULL DEFAULT 0,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Questions
CREATE TABLE IF NOT EXISTS trivia_question (
    id BIGSERIAL PRIMARY KEY,
    category VARCHAR(100) NOT NULL,
    question TEXT NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- Choices
CREATE TABLE IF NOT EXISTS trivia_choice (
    id BIGSERIAL PRIMARY KEY,
    question_id BIGINT NOT NULL,
    choice_text TEXT NOT NULL,
    display_order SMALLINT NOT NULL,
    is_correct BOOLEAN NOT NULL DEFAULT FALSE,

    CONSTRAINT fk_trivia_choice_question
        FOREIGN KEY (question_id)
        REFERENCES trivia_question(id)
        ON DELETE CASCADE,

    CONSTRAINT uq_trivia_choice_order
        UNIQUE (question_id, display_order)
);

-- Admins
CREATE TABLE admin (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP
);

-- Insert test admin
insert into admin (username, password) VALUES ('me', '$2a$12$TkgmGwQEC0cVDNniG3B4PuDMsNjGde9rqZHRKhasQB8Ng9ddVG6xC');

-- Question Choice ID Seq
CREATE INDEX IF NOT EXISTS idx_trivia_choice_question_id
    ON trivia_choice(question_id);