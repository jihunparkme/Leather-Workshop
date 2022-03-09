-- 공지사항
DROP TABLE IF EXISTS `NOTICE` RESTRICT;

-- 공지사항
CREATE TABLE `NOTICE` (
                          `id`                 BIGINT         NOT NULL, -- 공지사항 ID
                          `title`              VARCHAR(100)   NOT NULL, -- 제목
                          `contents`           VARCHAR(20000) NOT NULL, -- 내용
                          `hits`               BIGINT         NOT NULL DEFAULT 0, -- 조회수
                          `user_id`            BIGINT         NOT NULL, -- 사용자 ID
                          `created_date_time`  DATETIME       NOT NULL, -- 등록일
                          `modified_date_time` DATETIME       NULL      -- 수정일
);

-- 공지사항
ALTER TABLE `NOTICE`
    ADD CONSTRAINT `PK_NOTICE` -- 공지사항 기본키
        PRIMARY KEY (
                     `id` -- 공지사항 ID
            );

ALTER TABLE `NOTICE`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `NOTICE`
    AUTO_INCREMENT = 1;







-- 후기
DROP TABLE IF EXISTS `REVIEW` RESTRICT;

-- 후기
CREATE TABLE `REVIEW` (
                          `id`                 BIGINT        NOT NULL, -- 후기 ID
                          `user_id`            BIGINT       NULL, -- 사용자 ID
                          `nickname`           VARCHAR(30)  NULL, -- 이름
                          `contents`           VARCHAR(5000) NOT NULL, -- 내용
                          `created_date_time`  DATETIME      NOT NULL, -- 등록일
                          `modified_date_time` DATETIME      NULL      -- 수정일
);

-- 후기
ALTER TABLE `REVIEW`
    ADD CONSTRAINT `PK_REVIEW` -- 후기 기본키
        PRIMARY KEY (
                     `id` -- 후기 ID
            );

ALTER TABLE `REVIEW`
    MODIFY COLUMN `id` BIGINT NOT NULL AUTO_INCREMENT;

ALTER TABLE `REVIEW`
    AUTO_INCREMENT = 1;







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


ALTER TABLE `REVIEW`
    ADD CONSTRAINT `FK_USER_TO_REVIEW` -- 사용자 -> 상품
        FOREIGN KEY (
                     `user_id` -- 사용자 ID
            )
            REFERENCES `USER` ( -- 사용자
                               `id` -- 사용자 ID
                )
            ON DELETE SET NULL ON UPDATE NO ACTION;








-- 상품 첨부 파일
DROP TABLE IF EXISTS `PRODUCT_UPLOAD_FILE` RESTRICT;

-- 상품 첨부 파일
CREATE TABLE `PRODUCT_UPLOAD_FILE` (
                                       `id`                 BIGINT       NOT NULL, -- 파일 ID
                                       `product_id`         BIGINT       NOT NULL, -- 상품 ID
                                       `upload_file_name`   VARCHAR(100) NOT NULL, -- 업로드 파일명
                                       `store_file_name`    VARCHAR(100) NOT NULL, -- 저장 파일명
                                       `thumbnail_yn`       char(1)      NOT NULL DEFAULT 'N', -- 썸네일 여부
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




-- 상품
DROP TABLE IF EXISTS `PRODUCT` RESTRICT;

-- 상품 카테고리
DROP TABLE IF EXISTS `PRODUCT_CATEGORY` RESTRICT;

-- 상품 카테고리
CREATE TABLE `PRODUCT_CATEGORY` (
                                    `id`              BIGINT      NOT NULL, -- 카테고리 ID
                                    `title`           VARCHAR(20) NOT NULL, -- 카테고리명
                                    `order_no`        INT         NULL,     -- 우선순위
                                    `category_use_yn` CHAR(1)     NULL     DEFAULT 'Y' -- 사용여부
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






-- 상품
CREATE TABLE `PRODUCT` (
                           `id`                  BIGINT         NOT NULL, -- 상품 ID
                           `product_category_id` BIGINT         NOT NULL, -- 카테고리 ID
                           `name`                VARCHAR(100)   NOT NULL, -- 상품명
                           `contents`            VARCHAR(20000) NULL,     -- 상품설명
                           `hits`                BIGINT         NOT NULL DEFAULT 0, -- 조회수
                           `delete_yn`           CHAR(1)        NOT NULL DEFAULT 'N', -- 삭제여부
                           `deleted_date_time`   DATETIME       NULL,     -- 삭제일
                           `created_date_time`   DATETIME       NOT NULL, -- 등록일
                           `user_id`             BIGINT         NOT NULL, -- 사용자 ID
                           `modified_date_time`  DATETIME       NULL     -- 수정일
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
                )
            ON DELETE NO ACTION ON UPDATE NO ACTION;


-- 상품 첨부 파일
ALTER TABLE `PRODUCT_UPLOAD_FILE`
    ADD CONSTRAINT `FK_PRODUCT_TO_PRODUCT_UPLOAD_FILE` -- 상품 -> 상품 첨부 파일
        FOREIGN KEY (
                     `product_id` -- 상품 ID
            )
            REFERENCES `PRODUCT` ( -- 상품
                                  `id` -- 상품 ID
                )
            ON DELETE SET NULL ON UPDATE NO ACTION;