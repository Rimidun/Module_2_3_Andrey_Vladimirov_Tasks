CREATE TABLE IF NOT EXISTS labels
(
    id   BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
    name varchar(50) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS writers
(
    id         BIGINT      NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
    first_name varchar(50) NOT NULL,
    last_name  varchar(50) NOT NULL,
    label_id  BIGINT      NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS posts
(
    id        BIGINT       NOT NULL GENERATED ALWAYS AS IDENTITY UNIQUE,
    content   varchar(255) NOT NULL,
    "created" TIMESTAMP    NOT NULL DEFAULT NOW(),
    "updated" TIMESTAMP DEFAULT NULL,
    writer_id BIGINT       NOT NULL
);