USE mysql_db_borrowers;
ALTER TABLE borrower
MODIFY checked VARCHAR(1) DEFAULT 'F';

