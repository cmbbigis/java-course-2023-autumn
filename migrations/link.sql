-- Файл: link.sql
-- Эта таблица содержит информацию о ссылках.
CREATE TABLE Link
(
    id              BIGINT GENERATED ALWAYS AS IDENTITY, -- Уникальный идентификатор ссылки
    url             TEXT NOT NULL, -- URL ссылки
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL, -- Дата и время создания ссылки
    last_checked_at TIMESTAMP WITH TIME ZONE NOT NULL, -- Дата и время последней проверки
    created_by      TEXT NOT NULL, -- Пользователь, создавший ссылку

    PRIMARY KEY (id),
    UNIQUE (url)
);
