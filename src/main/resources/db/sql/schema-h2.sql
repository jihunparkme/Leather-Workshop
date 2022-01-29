DROP TABLE `notice` IF EXISTS;

CREATE TABLE `notice`
(
    `id`               BIGINT         NOT NULL AUTO_INCREMENT,
    `member_id`        BIGINT         NOT NULL,
    `title`            VARCHAR(100)   NOT NULL,
    `contents`         VARCHAR(20000) NOT NULL,
    `hits`             BIGINT         NOT NULL DEFAULT 0,
    `create_date_time` DATETIME       NOT NULL,
    `update_date_time` DATETIME       NULL,
    PRIMARY KEY (`id`)
)
