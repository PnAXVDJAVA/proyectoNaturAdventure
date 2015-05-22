
CREATE TABLE INSTRUCTOR (

	nif VARCHAR( 10 ) NOT NULL,
	name VARCHAR( 20 ) NOT NULL,
	firstSurname VARCHAR( 20 ) NOT NULL,
	secondSurname VARCHAR( 20 ),
	address VARCHAR( 50 ) NOT NULL,
	telephone VARCHAR( 19 ) NOT NULL,
	dateOfBirth DATE NOT NULL,
	email VARCHAR( 50 ) NOT NULL,
	bankAccount VARCHAR( 300 ) NOT NULL,
	userID VARCHAR( 20 ) NOT NULL,

	CONSTRAINT cp_instructor PRIMARY KEY( nif ),
	CONSTRAINT ca_instructor_user_details FOREIGN KEY( userID )
											REFERENCES user_details( userID )
											ON UPDATE CASCADE ON DELETE CASCADE					

);