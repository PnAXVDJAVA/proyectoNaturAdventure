
CREATE TABLE DEGREE (

	codDegree INTEGER NOT NULL,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(200),

	CONSTRAINT cp_degree PRIMARY KEY ( codDegree )

);