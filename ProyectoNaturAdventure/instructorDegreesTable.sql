
CREATE TABLE INSTRUCTOR_DEGREES ( 

	instructorNif VARCHAR( 10 ) NOT NULL,
	codDegree INTEGER NOT NULL,

	CONSTRAINT cp_instructor_degrees PRIMARY KEY ( instructorNif, codDegree ),
	CONSTRAINT ca_instructor_degrees_instructor FOREIGN KEY ( instructorNif )
												REFERENCES instructor( nif )
												ON UPDATE CASCADE ON DELETE CASCADE,
	CONSTRAINT ca_instructor_degrees_degree FOREIGN KEY ( codDegree )
											REFERENCES degree( codDegree )
											ON UPDATE CASCADE ON DELETE CASCADE

);