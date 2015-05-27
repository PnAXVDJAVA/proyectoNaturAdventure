
CREATE TYPE MessageType AS ENUM ( 'Sugerencia', 'Queja', 'Duda', 'Otros' );

CREATE TABLE SUGGESTION (

	suggestion_code INTEGER NOT NULL,
	name VARCHAR( 50 ) NOT NULL,
	email VARCHAR( 50 ) NOT NULL,
	tipo MessageType NOT NULL,
	message TEXT NOT NULL,

	CONSTRAINT cp_suggestion PRIMARY KEY (suggestion_code)

);