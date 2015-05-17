
CREATE TYPE ActivityLevel AS ENUM ( 'easy', 'medium', 'hard' );

CREATE TABLE ACTIVITY (

	codActivity INTEGER NOT NULL,
	name VARCHAR( 50 ) NOT NULL,
	description VARCHAR( 500 ),
	pricePerPerson REAL NOT NULL,
	level ActivityLevel NOT NULL,
	duration INTEGER NOT NULL,
	maxPartakers INTEGER,
	minPartakers INTEGER NOT NULL,
	picture BYTEA NOT NULL,

	CONSTRAINT cp_activity PRIMARY KEY ( codActivity ),
	CONSTRAINT calt_activity UNIQUE ( name ),
	CONSTRAINT ri_positiveNumbers CHECK ( pricePerPerson >= 0.0 AND 
										  duration > 0 AND
										  maxPartakers > 0 AND
										  minPartakers > 0 )

);