-- Database: User

-- DROP DATABASE "User";

CREATE DATABASE "User"
    WITH 
    OWNER = postgres
    ENCODING = 'UTF8'
    LC_COLLATE = 'Kazakh_Kazakhstan.utf8'
    LC_CTYPE = 'Kazakh_Kazakhstan.utf8'
    TABLESPACE = pg_default
    CONNECTION LIMIT = -1;
	
CREATE TABLE Users (
	id INTEGER PRIMARY KEY,
	userName VARCHAR(45),
	lastName VARCHAR(45),
	iin INTEGER,
	login VARCHAR(225),
	password VARCHAR(30)
 );