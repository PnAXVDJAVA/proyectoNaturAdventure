package disenyoFisico;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import conexion.ConnectionManager;

public class CreacionTablas {


	public static void main(String[] argv) throws Exception {
		//STEP 2: connectar amb la base de dades
		
		Connection conn = ConnectionManager.getConnection();
		
		//STEP 3: Crear i executar la sentència de tipus actualització
		try {
 			System.out.println("Creant una taula en la base de dades...");
      			Statement stmt = conn.createStatement();
      
    		  	String sql = "";

			stmt.executeUpdate(sql);
			System.out.println("Taula creada...");
 		}
		catch (SQLException e) {
			System.out.println("No s'ha pogut crear la taula.... ");
			e.printStackTrace();
			return;
		}
	}

}
