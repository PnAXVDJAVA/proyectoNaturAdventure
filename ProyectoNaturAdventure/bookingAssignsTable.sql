
CREATE TABLE BOOKING_ASSIGNS ( 

	codBooking INTEGER NOT NULL,
	instructorNif VARCHAR( 10 ) NOT NULL,

	CONSTRAINT cp_booking_assigns PRIMARY KEY ( codBooking, instructorNif ),
	CONSTRAINT ca_booking_assigns_booking FOREIGN KEY ( codBooking )
											REFERENCES booking( codBooking )
											ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT ca_booking_assigns_instructor FOREIGN KEY ( instructorNif )
											REFERENCES instructor( nif )
											ON UPDATE CASCADE ON DELETE RESTRICT

);