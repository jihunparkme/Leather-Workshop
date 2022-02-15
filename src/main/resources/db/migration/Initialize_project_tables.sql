-- 사용자
ALTER TABLE `USER`
DROP PRIMARY KEY; -- 사용자 기본키

-- 사용자
DROP TABLE IF EXISTS `USER` RESTRICT;

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


-----------------------------------------------------------------------


-- 공지사항
ALTER TABLE `Notice`
DROP FOREIGN KEY `FK_USER_TO_Notice`; -- 사용자 -> 공지사항

-- 공지사항
ALTER TABLE `Notice`
DROP PRIMARY KEY; -- 공지사항 기본키

-- 공지사항
DROP TABLE IF EXISTS `Notice` RESTRICT;

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

-- 공지사항
ALTER TABLE `Notice`
    ADD CONSTRAINT `FK_USER_TO_Notice` -- 사용자 -> 공지사항
        FOREIGN KEY (
                     `user_id` -- 사용자 ID
            )
            REFERENCES `USER` ( -- 사용자
                               `id` -- 사용자 ID
                );


-----------------------------------------------------------------------
-- 상품 첨부 파일
ALTER TABLE `PRODUCT_UPLOAD_FILE`
DROP FOREIGN KEY `FK_PRODUCT_TO_PRODUCT_UPLOAD_FILE`; -- 상품 -> 상품 첨부 파일

-- 상품 첨부 파일
ALTER TABLE `PRODUCT_UPLOAD_FILE`
DROP PRIMARY KEY; -- 상품 첨부 파일 기본키

-- 상품 첨부 파일
DROP TABLE IF EXISTS `PRODUCT_UPLOAD_FILE` RESTRICT;

-- 상품 첨부 파일
CREATE TABLE `PRODUCT_UPLOAD_FILE` (
                                       `id`                 BIGINT       NOT NULL, -- 파일 ID
                                       `product_id`         BIGINT       NOT NULL, -- 상품 ID
                                       `upload_file_name`   VARCHAR(100) NOT NULL, -- 업로드 파일명
                                       `store_file_name`    VARCHAR(100) NOT NULL, -- 저장 파일명
                                       `thumbnail_yn`       char(1)      NOT NULL DEFAULT N, -- 썸네일 여부
                                       `created_date_time`  DATETIME     NOT NULL, -- 등록일
                                       `modified_date_time` DATETIME     NULL      -- 수정일
);

-- 상품 첨부 파일
ALTER TABLE `PRODUCT_UPLOAD_FILE`
    ADD CONSTRAINT `PK_PRODUCT_UPLOAD_FILE` -- 상품 첨부 파일 기본키
        PRIMARY KEY (
                     `id` -- 파일 ID
            );

ALTER TABLE `PRODUCT_UPLOAD_FILE`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `PRODUCT_UPLOAD_FILE`
    AUTO_INCREMENT = 1;

-- 상품 첨부 파일
ALTER TABLE `PRODUCT_UPLOAD_FILE`
    ADD CONSTRAINT `FK_PRODUCT_TO_PRODUCT_UPLOAD_FILE` -- 상품 -> 상품 첨부 파일
        FOREIGN KEY (
                     `product_id` -- 상품 ID
            )
            REFERENCES `PRODUCT` ( -- 상품
                                  `id` -- 상품 ID
                );
-----------------------------------------------------------------------
-- 상품 카테고리
ALTER TABLE `PRODUCT_CATEGORY`
DROP PRIMARY KEY; -- 상품 카테고리 기본키

-- 상품 카테고리
DROP TABLE IF EXISTS `PRODUCT_CATEGORY` RESTRICT;

-- 상품 카테고리
CREATE TABLE `PRODUCT_CATEGORY` (
                                    `id`              BIGINT      NOT NULL, -- 카테고리 ID
                                    `title`           VARCHAR(20) NOT NULL, -- 카테고리명
                                    `order_no`        INT         NULL,     -- 우선순위
                                    `category_use_yn` CHAR(1)     NULL     DEFAULT Y -- 사용여부
);

-- 상품 카테고리
ALTER TABLE `PRODUCT_CATEGORY`
    ADD CONSTRAINT `PK_PRODUCT_CATEGORY` -- 상품 카테고리 기본키
        PRIMARY KEY (
                     `id` -- 카테고리 ID
            );

ALTER TABLE `PRODUCT_CATEGORY`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `PRODUCT_CATEGORY`
    AUTO_INCREMENT = 1;
-----------------------------------------------------------------------
-- 상품
ALTER TABLE `PRODUCT`
DROP FOREIGN KEY `FK_PRODUCT_CATEGORY_TO_PRODUCT`; -- 상품 카테고리 -> 상품

-- 상품
ALTER TABLE `PRODUCT`
DROP FOREIGN KEY `FK_USER_TO_PRODUCT`; -- 사용자 -> 상품

-- 상품
ALTER TABLE `PRODUCT`
DROP PRIMARY KEY; -- 상품 기본키

-- 상품
DROP TABLE IF EXISTS `PRODUCT` RESTRICT;

-- 상품
CREATE TABLE `PRODUCT` (
                           `id`                  BIGINT         NOT NULL, -- 상품 ID
                           `product_category_id` BIGINT         NOT NULL, -- 카테고리 ID
                           `name`                VARCHAR(100)   NOT NULL, -- 상품명
                           `contents`            VARCHAR(20000) NULL,     -- 상품설명
                           `hits`                INT            NOT NULL DEFAULT 0, -- 조회수
                           `delete_yn`           CHAR(1)        NOT NULL DEFAULT N, -- 삭제여부
                           `deleted_date_time`   DATETIME       NULL,     -- 삭제일
                           `created_date_time`   DATETIME       NOT NULL, -- 등록일
                           `user_id`             BIGINT         NOT NULL, -- 사용자 ID
                           `modified_date_time`  DATETIME       NULL,     -- 수정일
                           `modified_user_name`  VARCHAR(30)    NULL      -- 수정자
);

-- 상품
ALTER TABLE `PRODUCT`
    ADD CONSTRAINT `PK_PRODUCT` -- 상품 기본키
        PRIMARY KEY (
                     `id` -- 상품 ID
            );

ALTER TABLE `PRODUCT`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `PRODUCT`
    AUTO_INCREMENT = 1;

-- 상품
ALTER TABLE `PRODUCT`
    ADD CONSTRAINT `FK_PRODUCT_CATEGORY_TO_PRODUCT` -- 상품 카테고리 -> 상품
        FOREIGN KEY (
                     `product_category_id` -- 카테고리 ID
            )
            REFERENCES `PRODUCT_CATEGORY` ( -- 상품 카테고리
                                           `id` -- 카테고리 ID
                );

-- 상품
ALTER TABLE `PRODUCT`
    ADD CONSTRAINT `FK_USER_TO_PRODUCT` -- 사용자 -> 상품
        FOREIGN KEY (
                     `user_id` -- 사용자 ID
            )
            REFERENCES `USER` ( -- 사용자
                               `id` -- 사용자 ID
                );
-----------------------------------------------------------------------
-- 후기
ALTER TABLE `GUEST_BOOK`
DROP PRIMARY KEY; -- 후기 기본키

-- 후기
DROP TABLE IF EXISTS `GUEST_BOOK` RESTRICT;

-- 후기
CREATE TABLE `GUEST_BOOK` (
                              `id`                 BIGINT        NOT NULL, -- 후기 ID
                              `name`               VARCHAR(30)   NOT NULL, -- 이름
                              `password`           VARCHAR(100)  NOT NULL, -- 비밀번호
                              `contents`           VARCHAR(5000) NOT NULL, -- 내용
                              `created_date_time`  DATETIME      NOT NULL, -- 등록일
                              `modified_date_time` DATETIME      NULL      -- 수정일
);

-- 후기
ALTER TABLE `GUEST_BOOK`
    ADD CONSTRAINT `PK_GUEST_BOOK` -- 후기 기본키
        PRIMARY KEY (
                     `id` -- 후기 ID
            );

ALTER TABLE `GUEST_BOOK`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `GUEST_BOOK`
    AUTO_INCREMENT = 1;
-----------------------------------------------------------------------
-- 문의하기
ALTER TABLE `CONTACT_US`
DROP PRIMARY KEY; -- 문의하기 기본키

-- 문의하기
DROP TABLE IF EXISTS `CONTACT_US` RESTRICT;

-- 문의하기
CREATE TABLE `CONTACT_US` (
                              `id`                 BIGINT         NOT NULL, -- 문의하기 ID
                              `name`               VARCHAR(30)    NOT NULL, -- 이름
                              `email`              VARCHAR(50)    NULL,     -- 이메일
                              `phone_number`       VARCHAR(20)    NOT NULL, -- 전화번호
                              `title`              VARCHAR(100)   NOT NULL, -- 제목
                              `contents`           VARCHAR(20000) NOT NULL, -- 내용
                              `created_date_time`  DATETIME       NOT NULL, -- 등록일
                              `modified_date_time` DATETIME       NULL      -- 수정일
);

-- 문의하기
ALTER TABLE `CONTACT_US`
    ADD CONSTRAINT `PK_CONTACT_US` -- 문의하기 기본키
        PRIMARY KEY (
                     `id` -- 문의하기 ID
            );

ALTER TABLE `CONTACT_US`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `CONTACT_US`
    AUTO_INCREMENT = 1;
-----------------------------------------------------------------------