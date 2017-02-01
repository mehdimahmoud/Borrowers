
# Database is created on Docker engine from a mysql image:
# CREATE DATABASE mysql_db_borrowers;

USE mysql_db_borrowers;

DROP TABLE IF EXISTS borrower;

CREATE TABLE borrower (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  memberid VARCHAR(255),
  fullname VARCHAR(255),
  barcode VARCHAR(20),
  type VARCHAR(3),
  checked CHAR DEFAULT 'F',
  PRIMARY KEY (`id`)
);

CREATE TABLE email_address (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  email_address VARCHAR(255),
  type VARCHAR(50),
  borrower_id BIGINT(20),
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_borrower_emailaddress` (`borrower_id`,`email_address`),
  CONSTRAINT `fk_borrower_emailaddress` FOREIGN KEY (`borrower_id`) REFERENCES `borrower` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE phone (
  id BIGINT(20) NOT NULL AUTO_INCREMENT,
  phone_number VARCHAR(255),
  type VARCHAR(50),
  borrower_id BIGINT(20),
  PRIMARY KEY (`id`),
  UNIQUE KEY `fk_borrower_phonenumber` (`borrower_id`,`phone_number`),
  CONSTRAINT `fk_borrower_phonenumber` FOREIGN KEY (`borrower_id`) REFERENCES `borrower` (`id`) ON DELETE CASCADE ON UPDATE CASCADE

);

CREATE TRIGGER BEFORE_INSERT_BORROWER
BEFORE INSERT ON borrower
FOR EACH ROW
  SET new.memberid = uuid_short();