CREATE TABLE task
(
    id       BIGSERIAL PRIMARY KEY,
    name     VARCHAR(255) NOT NULL,
    duration INT          NOT NULL,
    status   varchar(255) NOT NULL
);