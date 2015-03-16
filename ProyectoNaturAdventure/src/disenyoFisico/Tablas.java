package disenyoFisico;

import java.util.LinkedList;
import java.util.List;

public class Tablas {

	private static String getTablaActivity() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaInstructor() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaSpecialized() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaCustomer() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaBooking() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaBookingAssigns() {
		String createTable = "";
		
		return createTable;
	}
	
	private static String getTablaInstructorDegrees() {
		String createTable = "";
		
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
