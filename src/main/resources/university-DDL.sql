-- MySQL Script generated by MySQL Workbench
-- Mon Sep 12 15:18:28 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema universities
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `universities` ;

-- -----------------------------------------------------
-- Schema universities
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `universities` DEFAULT CHARACTER SET utf8 ;
USE `universities` ;

-- -----------------------------------------------------
-- Table `universities`.`addresses`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`addresses` ;

CREATE TABLE IF NOT EXISTS `universities`.`addresses` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `city` VARCHAR(45) NOT NULL,
  `street` VARCHAR(45) NOT NULL,
  `house` INT UNSIGNED NOT NULL,
  `flat` INT UNSIGNED NULL,
  `index` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `city_UNIQUE` (`city` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`rectors`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`rectors` ;

CREATE TABLE IF NOT EXISTS `universities`.`rectors` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_rectors_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_rectors_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`trade_union_chairmen`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`trade_union_chairmen` ;

CREATE TABLE IF NOT EXISTS `universities`.`trade_union_chairmen` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trade_union_chairmen_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_trade_union_chairmen_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`trade_union_committies`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`trade_union_committies` ;

CREATE TABLE IF NOT EXISTS `universities`.`trade_union_committies` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `trade_union_chairmen_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_trade_union_committies_trade_union_chairmen1_idx` (`trade_union_chairmen_id` ASC) VISIBLE,
  CONSTRAINT `fk_trade_union_committies_trade_union_chairmen1`
    FOREIGN KEY (`trade_union_chairmen_id`)
    REFERENCES `universities`.`trade_union_chairmen` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`libraries`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`libraries` ;

CREATE TABLE IF NOT EXISTS `universities`.`libraries` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `number_of_books` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`head_accountants`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`head_accountants` ;

CREATE TABLE IF NOT EXISTS `universities`.`head_accountants` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_head_accountants_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_head_accountants_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`accounts_departments`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`accounts_departments` ;

CREATE TABLE IF NOT EXISTS `universities`.`accounts_departments` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `head_accountants_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_accounts_departments_head_accountants1_idx` (`head_accountants_id` ASC) VISIBLE,
  CONSTRAINT `fk_accounts_departments_head_accountants1`
    FOREIGN KEY (`head_accountants_id`)
    REFERENCES `universities`.`head_accountants` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`universities`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`universities` ;

CREATE TABLE IF NOT EXISTS `universities`.`universities` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `established` TIMESTAMP NOT NULL,
  `rectors_id` BIGINT UNSIGNED ZEROFILL NOT NULL,
  `trade_union_committies_id` BIGINT UNSIGNED NOT NULL,
  `libraries_id` BIGINT UNSIGNED NOT NULL,
  `accounts_departments_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `name_UNIQUE` (`name` ASC) VISIBLE,
  INDEX `fk_universities_rectors1_idx` (`rectors_id` ASC) VISIBLE,
  INDEX `fk_universities_trade_union_committies1_idx` (`trade_union_committies_id` ASC) VISIBLE,
  INDEX `fk_universities_libraries1_idx` (`libraries_id` ASC) VISIBLE,
  INDEX `fk_universities_accounts_departments1_idx` (`accounts_departments_id` ASC) VISIBLE,
  CONSTRAINT `fk_universities_rectors1`
    FOREIGN KEY (`rectors_id`)
    REFERENCES `universities`.`rectors` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_universities_trade_union_committies1`
    FOREIGN KEY (`trade_union_committies_id`)
    REFERENCES `universities`.`trade_union_committies` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_universities_libraries1`
    FOREIGN KEY (`libraries_id`)
    REFERENCES `universities`.`libraries` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_universities_accounts_departments1`
    FOREIGN KEY (`accounts_departments_id`)
    REFERENCES `universities`.`accounts_departments` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`student_cards`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`student_cards` ;

CREATE TABLE IF NOT EXISTS `universities`.`student_cards` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `student_number` VARCHAR(45) NOT NULL,
  `issue_date` TIMESTAMP NOT NULL,
  `expire_date` TIMESTAMP NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `student_number_UNIQUE` (`student_number` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`accountants`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`accountants` ;

CREATE TABLE IF NOT EXISTS `universities`.`accountants` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  `accounts_departments_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_accountants_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  INDEX `fk_accountants_accounts_departments1_idx` (`accounts_departments_id` ASC) VISIBLE,
  CONSTRAINT `fk_accountants_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_accountants_accounts_departments1`
    FOREIGN KEY (`accounts_departments_id`)
    REFERENCES `universities`.`accounts_departments` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`deans`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`deans` ;

CREATE TABLE IF NOT EXISTS `universities`.`deans` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_deans_addresses_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_deans_addresses`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`librarians`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`librarians` ;

CREATE TABLE IF NOT EXISTS `universities`.`librarians` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  `libraries_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_librarians_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  INDEX `fk_librarians_libraries1_idx` (`libraries_id` ASC) VISIBLE,
  CONSTRAINT `fk_librarians_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_librarians_libraries1`
    FOREIGN KEY (`libraries_id`)
    REFERENCES `universities`.`libraries` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`faculties`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`faculties` ;

CREATE TABLE IF NOT EXISTS `universities`.`faculties` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `universities_id` BIGINT UNSIGNED NOT NULL,
  `deans_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_faculties_universities1_idx` (`universities_id` ASC) VISIBLE,
  INDEX `fk_faculties_deans1_idx` (`deans_id` ASC) VISIBLE,
  CONSTRAINT `fk_faculties_universities1`
    FOREIGN KEY (`universities_id`)
    REFERENCES `universities`.`universities` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_faculties_deans1`
    FOREIGN KEY (`deans_id`)
    REFERENCES `universities`.`deans` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`students`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`students` ;

CREATE TABLE IF NOT EXISTS `universities`.`students` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `scholarship` DECIMAL UNSIGNED NULL,
  `has_scholarship` TINYINT NOT NULL,
  `lives_in_hostel` TINYINT NOT NULL,
  `course` INT UNSIGNED NOT NULL,
  `student_cards_id` BIGINT UNSIGNED NOT NULL,
  `universities_id` BIGINT UNSIGNED NOT NULL,
  `faculties_id` BIGINT UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_students_student_cards1_idx` (`student_cards_id` ASC) VISIBLE,
  INDEX `fk_students_universities1_idx` (`universities_id` ASC) VISIBLE,
  INDEX `fk_students_faculties1_idx` (`faculties_id` ASC) VISIBLE,
  INDEX `fk_students_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  CONSTRAINT `fk_students_student_cards1`
    FOREIGN KEY (`student_cards_id`)
    REFERENCES `universities`.`student_cards` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_universities1`
    FOREIGN KEY (`universities_id`)
    REFERENCES `universities`.`universities` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_faculties1`
    FOREIGN KEY (`faculties_id`)
    REFERENCES `universities`.`faculties` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_students_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`chairs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`chairs` ;

CREATE TABLE IF NOT EXISTS `universities`.`chairs` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `universities_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_chairs_universities1_idx` (`universities_id` ASC) VISIBLE,
  CONSTRAINT `fk_chairs_universities1`
    FOREIGN KEY (`universities_id`)
    REFERENCES `universities`.`universities` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`teachers`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`teachers` ;

CREATE TABLE IF NOT EXISTS `universities`.`teachers` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` VARCHAR(45) NOT NULL,
  `last_name` VARCHAR(45) NOT NULL,
  `birthday` TIMESTAMP NOT NULL,
  `salary` DECIMAL UNSIGNED NOT NULL,
  `academic_status` ENUM("JUNIOR_RESEARCHER", "SENIOR_RESEARCHER", "DOCENT", "PROFESSOR", "ASSISTANT") NOT NULL,
  `chairs_id` BIGINT UNSIGNED NOT NULL,
  `addresses_id` BIGINT UNSIGNED NOT NULL,
  `universities_id` BIGINT UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_teachers_chairs1_idx` (`chairs_id` ASC) VISIBLE,
  INDEX `fk_teachers_addresses1_idx` (`addresses_id` ASC) VISIBLE,
  INDEX `fk_teachers_universities1_idx` (`universities_id` ASC) VISIBLE,
  CONSTRAINT `fk_teachers_chairs1`
    FOREIGN KEY (`chairs_id`)
    REFERENCES `universities`.`chairs` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teachers_addresses1`
    FOREIGN KEY (`addresses_id`)
    REFERENCES `universities`.`addresses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_teachers_universities1`
    FOREIGN KEY (`universities_id`)
    REFERENCES `universities`.`universities` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `universities`.`faculty_chairs`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `universities`.`faculty_chairs` ;

CREATE TABLE IF NOT EXISTS `universities`.`faculty_chairs` (
  `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
  `chair_id` BIGINT UNSIGNED NOT NULL,
  `faculty_id` BIGINT UNSIGNED NOT NULL,
  INDEX `fk_chairs_has_faculties_faculties1_idx` (`faculty_id` ASC) VISIBLE,
  INDEX `fk_chairs_has_faculties_chairs1_idx` (`chair_id` ASC) VISIBLE,
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_chairs_has_faculties_chairs1`
    FOREIGN KEY (`chair_id`)
    REFERENCES `universities`.`chairs` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_chairs_has_faculties_faculties1`
    FOREIGN KEY (`faculty_id`)
    REFERENCES `universities`.`faculties` (`id`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;