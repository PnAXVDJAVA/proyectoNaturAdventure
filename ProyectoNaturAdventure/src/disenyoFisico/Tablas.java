package disenyoFisico;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class Tablas {
	
	private static String [] listaFicherosSQL = { "activityTable.sql", "userDetailsTable.sql", "instructorTable.sql", "specializedTable.sql", 
												  "customerTable.sql", "bookingTable.sql", "bookingAssignsTable.sql", 
												  "degreeTable.sql", "instructorDegreesTable.sql" };
	
	public static String leeFicheroTabla( String fichero ) {
		BufferedReader br;
		String table = "";
		try {
			br = new BufferedReader( new FileReader( fichero ) );
			StringBuilder sb = new StringBuilder();
			String line = br.readLine();
			
			while( line != null ) {
				sb.append( line );
				sb.append( System.lineSeparator() );
				line = br.readLine();
			}
			table = sb.toString();
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return table;	
	}
	
	public static List<String> getTablas() {
		List<String> listaTablas = new LinkedList<>();
		
		for( String fichero: listaFicherosSQL ) {
			listaTablas.add( leeFicheroTabla( fichero ) );
		}
		
		return listaTablas;
	}

}
