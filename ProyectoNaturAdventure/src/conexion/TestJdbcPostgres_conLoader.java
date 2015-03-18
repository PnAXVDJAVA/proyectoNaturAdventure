/*
 * Llig les propietats de la connexiÃ³ d'un fitxer jdbc.properties
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
import dao.Degree;
import dao.Instructor;
import dao.InstructorDao;
import dao.Level;
 
public class TestJdbcPostgres_conLoader {

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
		System.out.println( "Añadida activity 1: " + activityDao.getActivity( activity1.getCodActivity() ).toString() );
		
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
		System.out.println( "Añadida activity 2: " + activityDao.getActivity( activity2.getCodActivity() ).toString() );
		
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
		instructor1.setFirstSurname( "López" );
		instructor1.setSecondSurname( "Castellote" );
		instructor1.setAddress( "C/ Picasso, 2, 2º, 6ª" );
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
		System.out.println( "Añadido instructor1: " + instructorDao.getInstructor( nif1 ).toString() );
		
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
		System.out.println( "Añadido instructor2: " + instructorDao.getInstructor( nif2 ).toString() );
		
		
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
		
	}
 
}


