
CREATE TYPE StartHour AS ENUM ( 'morning', 'afternoon', 'night' );
CREATE TYPE BookingStatus AS ENUM ( 'pending', 'accepted', 'denied' );

CREATE TABLE BOOKING (

	codBooking INTEGER NOT NULL,
	proposalPerformingDate DATE NOT NULL,
	numPartakers INTEGER NOT NULL,
	bookingDate DATE NOT NULL,
	customerNif VARCHAR( 10 ) NOT NULL,
	codActivity INTEGER NOT NULL,
	startHour StartHour NOT NULL,
	status BookingStatus NOT NULL,

	CONSTRAINT cp_booking PRIMARY KEY ( codBooking ),
	CONSTRAINT ca_booking_customer FOREIGN KEY ( customerNif )
									REFERENCES customer( nif )
									ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT ca_booking_activity FOREIGN KEY ( codActivity )
									REFERENCES activity( codActivity )
									ON UPDATE CASCADE ON DELETE RESTRICT,
	CONSTRAINT ri_numPartakers CHECK ( numPartakers > 0 )

);