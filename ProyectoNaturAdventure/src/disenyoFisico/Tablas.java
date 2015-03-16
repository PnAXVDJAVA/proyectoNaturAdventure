package disenyoFisico;

import java.util.LinkedList;
import java.util.List;

public class Tablas {

	private static String getTablaActivity() {
		String createTable = 
		"CREATE TYPE ActivityLevel AS ENUM ( 'easy', 'medium', 'hard' );" +
		"CREATE TABLE ACTIVITY (" +
		"codActivity INTEGER," +
		"name VARCHAR( 50 )," +
		"description VARCHAR( 500 )," +
		"pricePerPerson REAL," +
		"level ActivityLevel," +
		"duration INTEGER," +
		"maxPartakers INTEGER," +
		"minPartakers INTEGER," +
	
		"CONSTRAINT cp_activity PRIMARY KEY ( codActivity )," +
		"CONSTRAINT calt_activity UNIQUE ( name )," +
		"CONSTRAINT ri_positiveNumbers CHECK ( pricePerPerson >= 0.0 AND " +
		"									  duration > 0 AND" +
		"									  maxPartakers > 0 AND" +
		"									  minPartakers > 0 )" +

		");";
		
		return createTable;
	}
	
	private static String getTablaInstructor() {
		String createTable = 
		"CREATE TABLE INSTRUCTOR (" +

		"		nif VARCHAR( 10 )," +
		"		name VARCHAR( 20 )," +
		"		firstSurname VARCHAR( 20 )," +
		"		secondSurname VARCHAR( 20 )," +
		"		address VARCHAR( 50 )," +
		"		telephone INTEGER," +
		"		dateOfBirth DATE," +
		"		email VARCHAR( 50 )," +
		"		bankAccount VARCHAR( 30 )," +
		"		userID VARCHAR( 20 )," +
		"		password VARCHAR( 20 )," +

		"		CONSTRAINT cp_instructor PRIMARY KEY( nif )" +

		"	);";
		
		return createTable;
	}
	
	private static String getTablaSpecialized() {
		String createTable = 
		
		"CREATE TABLE SPECIALIZED (" +

		"		codActivity INTEGER," +
		"		instructorNif VARCHAR( 10 )," +

		"		CONSTRAINT cp_specialized PRIMARY KEY ( codActivity, instructorNif )," +
		"		CONSTRAINT ca_specialized_activity FOREIGN KEY ( codActivity )" +
		"											REFERENCES activity( codActivity )" +
		"											ON UPDATE CASCADE ON DELETE CASCADE," +
		"		CONSTRAINT ca_specialized_instructor FOREIGN KEY ( instructorNif )" +
		"												REFERENCES instructor( nif )" +
		"												ON UPDATE CASCADE ON DELETE CASCADE" +
		"	);";
		
		return createTable;
	}
	
	private static String getTablaCustomer() {
		String createTable = 
				
		"CREATE TABLE CUSTOMER ( " +
				
		"		nif VARCHAR( 10 )," +
		"		name VARCHAR( 20 )," +
		"		firstSurname VARCHAR( 20 )," +
		"		secondSurname VARCHAR( 20 )," +
		"		email VARCHAR( 50 )," +
		"		telephone INTEGER," +

		"		CONSTRAINT cp_customer PRIMARY KEY ( nif )" +

		"	);";
		
		return createTable;
	}
	
	private static String getTablaBooking() {
		String createTable =
		
		"CREATE TYPE StartHour AS ENUM ( 'morning', 'afternoon', 'night' );" +
		"CREATE TYPE BookingStatus AS ENUM ( 'pending', 'accepted', 'denied' );" +

		"CREATE TABLE BOOKING (" +

		"	codBooking INTEGER," +
		"	proposalPerformingDate DATE," +
		"	numPartakers INTEGER," +
		"	bookingDate DATE," +
		"	customerNif VARCHAR( 10 )," +
		"	codActivity INTEGER," +
		"	startHour StartHour," +
		"	status BookingStatus," +

		"	CONSTRAINT cp_booking PRIMARY KEY ( codBooking )," +
		"	CONSTRAINT ca_booking_customer FOREIGN KEY ( customerNif )" +
		"									REFERENCES customer( nif )" +
		"									ON UPDATE CASCADE ON DELETE RESTRICT," +
		"	CONSTRAINT ca_booking_activity FOREIGN KEY ( codActivity )" +
		"									REFERENCES activity( codActivity )" +
		"									ON UPDATE CASCADE ON DELETE RESTRICT," +
		"	CONSTRAINT ri_numPartakers CHECK ( numPartakers > 0 )" +

		");";
		
		return createTable;
	}
	
	private static String getTablaBookingAssigns() {
		String createTable =
		"CREATE TABLE BOOKING_ASSIGNS ( " +

		"		codBooking INTEGER," +
		"		instructorNif VARCHAR( 10 )," +

		"		CONSTRAINT cp_booking_assigns PRIMARY KEY ( codBooking, instructorNif )," +
		"		CONSTRAINT ca_booking_assigns_booking FOREIGN KEY ( codBooking )" +
		"												REFERENCES booking( codBooking )" +
		"												ON UPDATE CASCADE ON DELETE RESTRICT," +
		"		CONSTRAINT ca_booking_assigns_instructor FOREIGN KEY ( instructorNif )" +
		"												REFERENCES instructor( nif )" +
		"												ON UPDATE CASCADE ON DELETE RESTRICT" +

		");";
		
		return createTable;
	}
	
	private static String getTablaInstructorDegrees() {
		String createTable =
		
		"CREATE TABLE INSTRUCTOR_DEGREES ( " +

		"		instructorNif VARCHAR( 10 )," +
		"		codDegree INTEGER," +
		"		description VARCHAR( 200 )," +

		"		CONSTRAINT cp_instructor_degrees PRIMARY KEY ( instructorNif, codDegree )," +
		"		CONSTRAINT ca_instructor_degrees_instructor FOREIGN KEY ( instructorNif )" +
		"													REFERENCES instructor( nif )" +
		"													ON UPDATE CASCADE ON DELETE CASCADE" +

		"	);";
		
		return createTable;
	}
	
	public static List<String> getTablas() {
		List<String> listaTablas = new LinkedList<>();
		
		listaTablas.add( Tablas.getTablaActivity() );
		listaTablas.add( Tablas.getTablaInstructor() );
		listaTablas.add( Tablas.getTablaSpecialized() );
		listaTablas.add( Tablas.getTablaCustomer() );
		listaTablas.add( Tablas.getTablaBooking() );
		listaTablas.add( Tablas.getTablaBookingAssigns() );
		listaTablas.add( Tablas.getTablaInstructorDegrees() );
		
		return listaTablas;
	}

}
