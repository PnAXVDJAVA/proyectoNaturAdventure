package conexion;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import dao.Activity;
import dao.ActivityDao;
import dao.Booking;
import dao.BookingDao;
import dao.Customer;
import dao.CustomerDao;
import dao.Instructor;
import dao.InstructorDao;

public class TestDelete {
	
	final static String JDBC_PROPERTIES = "./jdbc.properties";

	public static void main(String[] argv) {
		System.out.println("-------- PostgreSQL "
				+ "JDBC Connection Testing ------------");
		try {
			Class.forName("org.postgresql.Driver");
 
		} catch (ClassNotFoundException e) {
			System.out.println("Where is your PostgreSQL JDBC Driver? "
					+ "Include in your library path!");
			e.printStackTrace();
			return;
		}
		System.out.println("PostgreSQL JDBC Driver Registered!");
		Connection connection = null;
		try {
			String url = "jdbc:postgresql://db-aules.uji.es/xvd_pna";
			Properties props = new Properties();
			ClassLoader loader = Thread.currentThread().getContextClassLoader();           
			InputStream stream = loader.getResourceAsStream(JDBC_PROPERTIES);
			if (stream == null)
				System.out.println("Fitxer " + JDBC_PROPERTIES + " no encontrado");
			else {
				try {
					props.load(stream);
				}
				catch(IOException e) {
					System.out.println("No se puede leer el fichero JDBC.properties" + JDBC_PROPERTIES + " " + e.getMessage());
					e.printStackTrace();
				}
			}
			connection = DriverManager.getConnection(url, props);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
		}
		
		//A partir de aqui el codigo de prueba
		
		
		/* DELETE */
		
		/***** ACTIVIDADES *****/
		
		
		ActivityDao activityDao = new ActivityDao();
		Activity activity = null;
		
		for( int i = 1; i <= 2; i++ ) {
			activity = new Activity();
			activity.setCodActivity( i );
			activityDao.deleteActivity( activity );
			System.out.println( "Borrado activity" + i );
		}
		
		
		/***** MONITORES *****/
		
		InstructorDao instructorDao = new InstructorDao();
		String [] instructorNifs = { "20488603L", "111111111" };
		Instructor instructor = null;
		
		for( int i = 1; i <= 2; i++ ) {
			instructor = new Instructor();
			instructor.setNif( instructorNifs[i-1] );
			instructorDao.deleteInstructor( instructor );
			System.out.println( "Borrado instructor " + i );
		}
		
		/***** CUSTOMER *****/
		
		CustomerDao customerDao = new CustomerDao();
		String[] customerNifs = {"x123456789", "x987654321"};
		Customer customer = null;
		
		for( int i = 1; i <= 2; i++) {
			customer = new Customer();
			customer.setNIF(customerNifs[i-1]);
			customerDao.deleteCustomer(customer);
			System.out.println("Borrado cliente " + i);
		}
		
		/***** BOOKING *****/
		
		BookingDao bookingDao = new BookingDao();
		Booking booking = null;
		
		for( int i = 1; i <= 2; i++) {
			booking = new Booking();
			booking.setCodBooking(i);
			bookingDao.deleteBooking(booking);
			System.out.println("Borrado reserva " + i);
		}
	}
}
