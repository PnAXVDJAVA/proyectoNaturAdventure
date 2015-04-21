
CREATE TABLE SPECIALIZED (

	codActivity INTEGER NOT NULL,
	instructorNif VARCHAR( 10 ) NOT NULL,

	CONSTRAINT cp_specialized PRIMARY KEY ( codActivity, instructorNif ),
	CONSTRAINT ca_specialized_activity FOREIGN KEY ( codActivity )
										REFERENCES activity( codActivity )
										ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT ca_specialized_instructor FOREIGN KEY ( instructorNif )
											REFERENCES instructor( nif )
											ON UPDATE CASCADE ON DELETE CASCADE
);