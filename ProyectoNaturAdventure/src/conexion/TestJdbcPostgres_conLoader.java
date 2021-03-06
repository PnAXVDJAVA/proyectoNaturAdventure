/*
 * Llig les propietats de la connexió d'un fitxer jdbc.properties
 * situat a la base del classpath (directament baix el directori src a Eclipse) 
 */

package conexion;

import java.io.InputStream;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.io.IOException;
import dao.Activity;
import dao.ActivityDao;
import dao.Booking;
import dao.BookingDao;
import dao.BookingStatus;
import dao.Customer;
import dao.CustomerDao;
import dao.Degree;
import dao.Instructor;
import dao.InstructorDao;
import dao.Level;
import dao.StartHour;
 
public class TestJdbcPostgres_conLoader {

	final static String JDBC_PROPERTIES = "./jdbc.properties";

	@SuppressWarnings("deprecation")
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
		
		
		/***** ACTIVIDADES *****/
		
		System.out.println( "********* ACTIVIDADES *********" );
		
		/* ADD Y GET */
		
		ActivityDao activityDao = new ActivityDao();
		
		System.out.println( "-----------------------------" );
		System.out.println( "ADD Y GET" );
		System.out.println( "-----------------------------" );
		
		Activity activity1 = new Activity();
		activity1.setCodActivity( 1 );
		activity1.setName( "activity1" );
		activity1.setDescription( "esto es la primera actividad" );
		activity1.setDuration( 120 );
		activity1.setLevel( Level.easy );
		activity1.setMaxPartakers( 5 );
		activity1.setMinPartakers( 1 );
		activity1.setPricePerPerson( 50.5 );
		
		activityDao.addActivity( activity1 );
		System.out.println( "A�adida activity 1: " + activityDao.getActivity( activity1.getCodActivity() ).toString() );
		
		Activity activity2 = new Activity();
		activity2.setCodActivity( 2 );
		activity2.setName( "activity2" );
		activity2.setDescription( "esto es la segunda actividad" );
		activity2.setDuration( 120 );
		activity2.setLevel( Level.hard );
		activity2.setMaxPartakers( 10 );
		activity2.setMinPartakers( 10 );
		activity2.setPricePerPerson( 60.5 );
		
		activityDao.addActivity( activity2 );
		System.out.println( "A�adida activity 2: " + activityDao.getActivity( activity2.getCodActivity() ).toString() );
		
		/* UPDATE Y GET ALL */
		
		System.out.println( "-----------------------------" );
		System.out.println( "UPDATE Y GET ALL" );
		System.out.println( "-----------------------------" );
		
		activity1.setLevel( Level.medium );
		activity1.setMaxPartakers( 50 );
		
		activity2.setName( "activity2_mod" );
		activity2.setPricePerPerson( 0.0 );
		
		activityDao.updateActivity( activity1 );
		activityDao.updateActivity( activity2 );
		
		Set<Activity> activitySet = activityDao.getActivities();
		System.out.println( "Imprimiendo lista de actividades actualizada:" );
		for( Activity activity: activitySet ) {
			System.out.println( activity.toString() );
		}
		
		
		/***** MONITORES Y DEGREE *****/
		
		/* ADD Y GET */
		
		InstructorDao instructorDao = new InstructorDao();
		
		System.out.println( "-----------------------------" );
		System.out.println( "ADD Y GET" );
		System.out.println( "-----------------------------" );
		
		Instructor instructor1 = new Instructor();
		String nif1 = "20488603L";
		instructor1.setNif( nif1 );
		instructor1.setName( "David" );
		instructor1.setFirstSurname( "L�pez" );
		instructor1.setSecondSurname( "Castellote" );
		instructor1.setAddress( "C/ Picasso, 2, 2�, 6�" );
		instructor1.setEmail( "al259345@uji.es" );
		instructor1.setDateOfBirth( java.sql.Date.valueOf( "1994-08-30" ) );
		instructor1.setTelephone( 669597047 );
		
		Degree degree1_1 = new Degree();
		degree1_1.setCodDegree( 1 );
		degree1_1.setName( "Primeros auxilios" );
		
		Degree degree1_2 = new Degree();
		degree1_2.setCodDegree( 2 );
		degree1_2.setName( "Grado en CAFD" );
		degree1_2.setDescription( "Grado en Ciencias de la Actividad Fisica y el Deporte" );
		
		instructor1.addDegree( degree1_1 );
		instructor1.addDegree( degree1_2 );
		
		instructorDao.addInstructor( instructor1 );
		
		instructorDao.addInstructorDegrees( instructor1 );
		
		System.out.println( "A�adido instructor1: " + instructorDao.getInstructor( nif1 ).toString() );
		
		Instructor instructor2 = new Instructor();
		String nif2 = "111111111";
		instructor2.setNif( nif2 );
		instructor2.setName( "Xavi" );
		instructor2.setFirstSurname( "Garcia" );
		instructor2.setSecondSurname( "Mena" );
		instructor2.setAddress( "C/ Asensi" );
		instructor2.setEmail( "xavi@uji.es" );
		instructor2.setDateOfBirth( java.sql.Date.valueOf( "2000-01-01" ) );
		instructor2.setTelephone( 123456789 );
		
		Degree degree2_1 = new Degree();
		degree2_1.setCodDegree( 3 );
		degree2_1.setName( "Curso de profesor de ed. fisica" );
		
		instructor2.addDegree( degree2_1 );
		instructor2.addDegree( degree1_2 );
		
		instructorDao.addInstructor( instructor2 );
		
		instructorDao.addInstructorDegrees( instructor2 );
		
		System.out.println( "A�adido instructor2: " + instructorDao.getInstructor( nif2 ).toString() );
		
		
		/* UPDATE Y GET ALL */
		
		System.out.println( "-----------------------------" );
		System.out.println( "UPDATE Y GET ALL" );
		System.out.println( "-----------------------------" );
		
		instructor1.setAddress( "C/ Falsa" );
		
		instructor2.setUserID( "xavinho" );
		
		instructorDao.updateInstructor( instructor1 );
		instructorDao.updateInstructor( instructor2 );
		
		Set<Instructor> instructorsSet = instructorDao.getInstructors();
		System.out.println( "Imprimiendo lista de monitores actualizada:" );
		for( Instructor instructor: instructorsSet ) {
			System.out.println( instructor.toString() );
		}
		
		
		/***** MONITORES ESPECIALIZADOS EN ACTIVIDADES *****/
		
		/* ADD */
		
		activity1.addSpecializedInstructor( instructor1 );
		activity1.addSpecializedInstructor( instructor2 );
		
		activity2.addSpecializedInstructor( instructor1 );
		
		activityDao.addSpecializedInstructors( activity1 );
		activityDao.addSpecializedInstructors( activity2 );
		
		
		/***** CUSTOMER *****/
		System.out.println( "********* CUSTOMER *********" );
		
		/* ADD Y GET */
		CustomerDao cusDao = new CustomerDao();
		
		System.out.println( "-----------------------------" );
		System.out.println( "ADD Y GET" );
		System.out.println( "-----------------------------" );
		
		Customer customer1 = new Customer();
		nif1 = "x123456789";
		customer1.setNIF(nif1);
		customer1.setName("Valeriu");
		customer1.setFirstSurname("Gavriluta");
		customer1.setSecondSurname(" ");
		customer1.setEmail("al259410@uji.es");
		customer1.setTelephone(666457895);
		
		cusDao.addCustomer(customer1);
		System.out.println("Añadido customer1: " + cusDao.getCustomer(nif1).toString());
		
		Customer customer2 = new Customer();
		nif2 = "x987654321";
		customer2.setNIF(nif2);
		customer2.setName("Jorge");
		customer2.setFirstSurname("Vicente");
		customer2.setSecondSurname("Cantero");
		customer2.setEmail("al259547@uji.es");
		customer2.setTelephone(669457565);
		
		cusDao.addCustomer(customer2);
		System.out.println("Añadido customer2: " + cusDao.getCustomer(nif2).toString());
		
		/* UPDATE Y GET ALL */
		
		System.out.println( "-----------------------------" );
		System.out.println( "UPDATE Y GET ALL" );
		System.out.println( "-----------------------------" );
		
		customer1.setName("Accel");
		customer1.setEmail("accelsincro@gmail.com");
		
		customer2.setSecondSurname("Andres");
		customer2.setTelephone(666666123);
		
		cusDao.updateCustomer(customer1);
		cusDao.updateCustomer(customer2);
		
		Set<Customer> customerSet = cusDao.getCustomers();
		System.out.println( "Imprimiendo lista de clientes actualizada:" );
		for( Customer customer: customerSet ) {
			System.out.println( customer.toString() );
		}
		
		/***** BOOKING *****/
		System.out.println( "********* BOOKING *********" );
		
		/* ADD Y GET */
		BookingDao bookingDao = new BookingDao();
		List<Instructor> listaInstructors = new LinkedList<Instructor>();
		listaInstructors.add(instructor1);
		listaInstructors.add(instructor2);
		
		System.out.println( "-----------------------------" );
		System.out.println( "ADD Y GET" );
		System.out.println( "-----------------------------" );
		
		Booking booking1 = new Booking();
		booking1.setCodBooking(1);
		booking1.setCodActivity(1);
		booking1.setBookingDate(java.sql.Date.valueOf( "2015-05-15" ));
		booking1.setCustomerNif("x123456789");
		booking1.setNumPartakers(4);
		booking1.setProposalPerformingDate(java.sql.Date.valueOf( "2015-05-25" ));
		booking1.setStartHour(StartHour.morning);
		booking1.setStatus(BookingStatus.pending);
		booking1.setAssignedInstructors(listaInstructors);
		
		bookingDao.addBooking(booking1);
		bookingDao.assignInstructors(booking1);
		System.out.println("Añadido booking1: " + bookingDao.getBooking(1));
		
		Booking booking2 = new Booking();
		booking2.setCodBooking(2);
		booking2.setCodActivity(2);
		booking2.setBookingDate(java.sql.Date.valueOf( "2014-02-10" ));
		booking2.setCustomerNif("x987654321");
		booking2.setNumPartakers(2);
		booking2.setProposalPerformingDate(java.sql.Date.valueOf( "2015-01-01" ));
		booking2.setStartHour(StartHour.afternoon);
		booking2.setStatus(BookingStatus.pending);
		booking2.setAssignedInstructors(listaInstructors);
		
		bookingDao.addBooking(booking2);
		bookingDao.assignInstructors(booking2);
		System.out.println("Añadido booking2: " + bookingDao.getBooking(2));
		
		/* UPDATE Y GET ALL */
		
		System.out.println( "-----------------------------" );
		System.out.println( "UPDATE Y GET ALL" );
		System.out.println( "-----------------------------" );
		
		booking1.setNumPartakers(10);
		booking1.setProposalPerformingDate(new Date(2015, 06, 8));
		
		booking2.setStatus(BookingStatus.denied);
		booking2.setStartHour(StartHour.night);
		
		bookingDao.updateBooking(booking1);
		bookingDao.updateBooking(booking2);
		
		Set<Booking> bookingSet = bookingDao.getBookings();
		System.out.println( "Imprimiendo lista de reservas actualizada:" );
		for( Booking booking: bookingSet ) {
			System.out.println( booking.toString() );
		}
	}
 
}


