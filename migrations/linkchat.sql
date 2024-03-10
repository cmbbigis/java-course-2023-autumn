-- Файл: linkchat.sql
-- Эта таблица связывает таблицы Link и Chat.
CREATE TABLE LinkChat
(
    link_id       BIGINT NOT NULL, -- Идентификатор ссылки
    chat_id          BIGINT NOT NULL, -- Идентификатор чата
    created_at      TIMESTAMP WITH TIME ZONE NOT NULL, -- Дата и время создания записи
    created_by      TEXT NOT NULL, -- Пользователь, создавший запись

    PRIMARY KEY (link_id, chat_id),
    FOREIGN KEY (link_id) REFERENCES link(id),
    FOREIGN KEY (chat_id) REFERENCES chat(id)
);
