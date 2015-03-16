package conexion;

//STEP 1. Importar els paquets necessàris 
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import conexion.ConnectionManager;

public class SelectPreparedStatementPostgreSQL {
 
	public static void main(String[] argv) throws Exception {
		//STEP 2: connectar amb la base de dades
		
		Connection conn = ConnectionManager.getConnection();
		ResultSet result=null;
		
		//STEP 3: Crear i executar la sentència de tipus consulta
		try {
 			System.out.println("Executant la consulta...");
			
			String sql = "SELECT *" +
			" from pais;";
			
			PreparedStatement stmt = conn.prepareStatement(sql);
         		  	
			result= stmt.executeQuery();
			System.out.println("Dades trobades...");
 		}
		catch (SQLException e) {
			System.out.println("No ha sigut possible executar la consulta.... ");
			e.printStackTrace();
			return;
		}
		//Mostrar el ResultSet
		if (result != null) // Si result == null no hi ha dades que mostrar
			while ( result.next() ) // amb aquest while processem totes les tuples que hi ha en result
			{   /* Per a cada columna hem de buscar la seua dada:*/
				System.out.println("Pais: " + result.getString(1)); //en aquest cas accedim per el nom de la columna
				System.out.println("Capital: " + result.getString(2));
				System.out.println("----------------------------------");
			}
	}

}

