package disenyoFisico;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import conexion.ConnectionManager;

public class CreacionTablas {


	public static void main(String[] argv) throws Exception {
		
		Connection conn = ConnectionManager.getConnection();
		
		int i = 1;
		try {
 			System.out.println("Creando las tablas de la base de datos...");
      		Statement stmt = conn.createStatement();
      		
      		List<String> listaTablas = Tablas.getTablas();
    		
    		for( String tabla: listaTablas ) {
    			stmt.executeUpdate( tabla );
    			System.out.println("Tabla creada (" + i + ")");
    			i++;
    		}

 		} catch (SQLException e) {
			System.out.println("No se ha podido crear la tabla " + i );
			e.printStackTrace();
			return;
		} finally {
			if (conn != null)
				conn.close();
		}
	}

}
