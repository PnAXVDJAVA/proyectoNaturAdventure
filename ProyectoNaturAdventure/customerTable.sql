CREATE TABLE CUSTOMER ( 
	
	nif VARCHAR( 10 ) NOT NULL,
	name VARCHAR( 20 ) NOT NULL,
	firstSurname VARCHAR( 20 ) NOT NULL,
	secondSurname VARCHAR( 20 ),
	email VARCHAR( 50 ) NOT NULL,
	telephone INTEGER NOT NULL,
	userID VARCHAR( 20 ) NOT NULL,

	CONSTRAINT cp_customer PRIMARY KEY ( nif ),
	CONSTRAINT ca_customer_user_details FOREIGN KEY( userID )
										REFERENCES user_details( userID )
										ON UPDATE CASCADE ON DELETE CASCADE

);