
CREATE TABLE INSTRUCTOR (

	nif VARCHAR( 10 ) NOT NULL,
	name VARCHAR( 20 ) NOT NULL,
	firstSurname VARCHAR( 20 ) NOT NULL,
	secondSurname VARCHAR( 20 ),
	address VARCHAR( 50 ) NOT NULL,
	telephone INTEGER NOT NULL,
	dateOfBirth DATE NOT NULL,
	email VARCHAR( 50 ) NOT NULL,
	bankAccount VARCHAR( 300 ) NOT NULL,
	userID VARCHAR( 20 ) NOT NULL,
	password VARCHAR( 100 ) NOT NULL,

	CONSTRAINT cp_instructor PRIMARY KEY( nif )

);