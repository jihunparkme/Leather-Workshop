
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema leather_workshop
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `leather_workshop` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;
USE `leather_workshop` ;

-- -----------------------------------------------------
-- Table `leather_workshop`.`contact_us`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`contact_us` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`contact_us` (
                                                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                               `name` VARCHAR(30) NOT NULL,
                                                               `email` VARCHAR(50) NULL DEFAULT NULL,
                                                               `phone_number` VARCHAR(20) NOT NULL,
                                                               `title` VARCHAR(100) NOT NULL,
                                                               `contents` VARCHAR(20000) NOT NULL,
                                                               `create_date_time` DATETIME NOT NULL,
                                                               `update_date_time` DATETIME NULL DEFAULT NULL,
                                                               PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`guest_book`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`guest_book` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`guest_book` (
                                                               `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                               `name` VARCHAR(30) NOT NULL,
                                                               `password` VARCHAR(100) NOT NULL,
                                                               `contents` VARCHAR(5000) NOT NULL,
                                                               `create_date_time` DATETIME NOT NULL,
                                                               `update_date_time` DATETIME NULL DEFAULT NULL,
                                                               PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`member`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`member` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`member` (
                                                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `user_id` VARCHAR(30) NOT NULL,
                                                           `password` VARCHAR(100) NOT NULL,
                                                           `name` VARCHAR(30) NOT NULL,
                                                           `email` VARCHAR(50) NOT NULL,
                                                           `auth` VARCHAR(10) NOT NULL DEFAULT 'MEMBER',
                                                           `create_date_time` DATETIME NOT NULL,
                                                           `update_date_time` DATETIME NULL,
                                                           PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`notice`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`notice` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`notice` (
                                                           `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                           `member_id` BIGINT NOT NULL,
                                                           `title` VARCHAR(100) NOT NULL,
                                                           `contents` VARCHAR(20000) NOT NULL,
                                                           `hits` BIGINT NOT NULL DEFAULT 0,
                                                           `create_date_time` DATETIME NOT NULL,
                                                           `update_date_time` DATETIME NULL,
                                                           PRIMARY KEY (`id`),
                                                           INDEX `fk_notice_member1_idx` (`member_id` ASC) VISIBLE,
                                                           CONSTRAINT `fk_notice_member1`
                                                               FOREIGN KEY (`member_id`)
                                                                   REFERENCES `leather_workshop`.`member` (`id`)
                                                                   ON DELETE NO ACTION
                                                                   ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`product_category`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`product_category` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`product_category` (
                                                                     `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                     `title` VARCHAR(20) NOT NULL,
                                                                     `order_no` INT NULL DEFAULT NULL,
                                                                     `category_use_yn` CHAR(1) NULL DEFAULT 'N',
                                                                     PRIMARY KEY (`id`))
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`product`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`product` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`product` (
                                                            `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                            `product_category_id` BIGINT NOT NULL,
                                                            `name` VARCHAR(100) NOT NULL,
                                                            `contents` VARCHAR(20000) NULL DEFAULT NULL,
                                                            `hits` BIGINT NOT NULL DEFAULT 0,
                                                            `delete_yn` CHAR(1) NOT NULL DEFAULT 'N',
                                                            `delete_date_time` DATETIME NULL DEFAULT NULL,
                                                            `create_date_time` DATETIME NOT NULL,
                                                            `member_id` BIGINT NOT NULL,
                                                            `update_date_time` DATETIME NULL DEFAULT NULL,
                                                            `update_member_name` VARCHAR(30) NULL DEFAULT NULL,
                                                            PRIMARY KEY (`id`),
                                                            INDEX `fk_product_product_category_idx` (`product_category_id` ASC) VISIBLE,
                                                            INDEX `fk_product_member1_idx` (`member_id` ASC) VISIBLE,
                                                            CONSTRAINT `fk_product_product_category`
                                                                FOREIGN KEY (`product_category_id`)
                                                                    REFERENCES `leather_workshop`.`product_category` (`id`)
                                                                    ON DELETE NO ACTION
                                                                    ON UPDATE NO ACTION,
                                                            CONSTRAINT `fk_product_member1`
                                                                FOREIGN KEY (`member_id`)
                                                                    REFERENCES `leather_workshop`.`member` (`id`)
                                                                    ON DELETE NO ACTION
                                                                    ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


-- -----------------------------------------------------
-- Table `leather_workshop`.`product_upload_file`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `leather_workshop`.`product_upload_file` ;

CREATE TABLE IF NOT EXISTS `leather_workshop`.`product_upload_file` (
                                                                        `id` BIGINT NOT NULL AUTO_INCREMENT,
                                                                        `product_id` BIGINT NOT NULL,
                                                                        `upload_file_name` VARCHAR(100) NOT NULL,
                                                                        `store_file_name` VARCHAR(100) NOT NULL,
                                                                        `thumbnail_yn` CHAR(1) NOT NULL DEFAULT 'N',
                                                                        `create_date_time` DATETIME NOT NULL,
                                                                        `update_date_time` DATETIME NULL DEFAULT NULL,
                                                                        INDEX `fk_product_upload_file_product1_idx` (`product_id` ASC) VISIBLE,
                                                                        PRIMARY KEY (`id`),
                                                                        CONSTRAINT `fk_product_upload_file_product1`
                                                                            FOREIGN KEY (`product_id`)
                                                                                REFERENCES `leather_workshop`.`product` (`id`)
                                                                                ON DELETE NO ACTION
                                                                                ON UPDATE NO ACTION)
    ENGINE = InnoDB
    DEFAULT CHARACTER SET = utf8
    COLLATE = utf8_bin;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
