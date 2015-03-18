package disenyoFisico;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.ConnectionManager;

public class BorrarTablas {

	public static void main (String[] args) throws Exception {
		
		Connection conn = ConnectionManager.getConnection();
		int i = 1;
		try {
			System.out.println("Borrando las tablas...");
			Statement stmt = conn.createStatement();
			List<String> listaTablas = getTablas();
			
			for(String tabla: listaTablas) {
				stmt.execute(tabla);
				System.out.println("Tabla/enum borrada (" + i + ")");
				i++;
			}
			
		} catch (SQLException e) {
			System.out.println("No se ha podido borrar la tabla " + i );
			e.printStackTrace();
			return;
		} finally {
			if (conn != null)
				conn.close();
		}
	}
	
	public static List<String> getTablas(){
		
		String bookingAssigns = "DROP TABLE bookig_assigns";
		String activityLevel = "DROP TYPE activityLevel";
		String booking = "DROP TABLE booking";
		String startHour = "DROP TYPE startHour";
		String bookingStatus = "DROP TYPE bookingStatus";
		String instructorDegrees = "DROP TABLE instructor_degrees";
		String instructor = "DROP TABLE instructor";
		String specialized = "DROP TABLE specialized";
		String customer = "DROP TABLE customer";
		String activity = "DROP TABLE activity";
		
		List<String> lista = new LinkedList<String>();
		lista.add(instructorDegrees);
		lista.add(bookingAssigns);
		lista.add(startHour);
		lista.add(bookingStatus);
		lista.add(booking);
		lista.add(customer);
		lista.add(specialized);
		lista.add(instructor);
		lista.add(activityLevel);
		lista.add(activity);

		return lista;
	}
}