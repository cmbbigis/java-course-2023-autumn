-- Файл: chat.sql
-- Эта таблица содержит информацию о чатах.
CREATE TABLE Chat
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY, -- Уникальный идентификатор чата
    name            TEXT NOT NULL, -- Название чата
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL, -- Дата и время создания чата
    created_by      TEXT NOT NULL, -- Пользователь, создавший чат

    PRIMARY KEY (id),
    UNIQUE (name)
);
