-- 공지사항
CREATE TABLE `Notice` (
                          `id`                 BIGINT         NOT NULL, -- 공지사항 ID
                          `title`              VARCHAR(100)   NOT NULL, -- 제목
                          `contents`           VARCHAR(20000) NOT NULL, -- 내용
                          `hits`               INT            NOT NULL DEFAULT 0, -- 조회수
                          `user_id`            BIGINT         NOT NULL, -- 사용자 ID
                          `created_date_time`  DATETIME       NOT NULL, -- 등록일
                          `modified_date_time` DATETIME       NULL      -- 수정일
);

-- 공지사항
ALTER TABLE `Notice`
    ADD CONSTRAINT `PK_Notice` -- 공지사항 기본키
        PRIMARY KEY (
                     `id` -- 공지사항 ID
            );

ALTER TABLE `Notice`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `Notice`
    AUTO_INCREMENT = 1;


-- 사용자
CREATE TABLE `USER` (
                        `id`                 BIGINT       NOT NULL, -- 사용자 ID
                        `name`               VARCHAR(30)  NOT NULL, -- 이름
                        `email`              VARCHAR(50)  NOT NULL, -- email
                        `picture`            VARCHAR(255)  NOT NULL, -- picture
                        `role`               VARCHAR(10)  NOT NULL, -- 권한
                        `created_date_time`  DATETIME     NOT NULL, -- 등록일
                        `modified_date_time` DATETIME     NULL      -- 수정일
);

-- 사용자
ALTER TABLE `USER`
    ADD CONSTRAINT `PK_USER` -- 사용자 기본키
        PRIMARY KEY (
                     `id` -- 사용자 ID
            );

ALTER TABLE `USER`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `USER`
    AUTO_INCREMENT = 1;