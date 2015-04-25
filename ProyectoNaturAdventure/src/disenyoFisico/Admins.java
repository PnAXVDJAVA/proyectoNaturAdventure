package disenyoFisico;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.jasypt.util.password.BasicPasswordEncryptor;

public class Admins {
	
	private static final String ficheroAdmins = "admins.txt";
	private static final int adminRole = 2;
	
	public static Map<String, String> leeFicheroAdmins( String fichero ) {
		BufferedReader br;
		Map<String, String> map = null;
		try {
			map = new HashMap<>();
			br = new BufferedReader( new FileReader( fichero ) );
			String line = br.readLine();
			
			while( line != null ) {
				String [] comps = line.split(":");
				map.put( comps[0] , comps[1] );
				line = br.readLine();
			}
			
			br.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return map;
	}
	
	public static List<String> getAdminsInserts() {
		List<String> sqls = new LinkedList<>();
		Map<String,String> map = leeFicheroAdmins( ficheroAdmins );
		Iterator<String> iter = map.keySet().iterator();
		while( iter.hasNext() ) {
			String username = iter.next();
			String password = map.get( username );
			BasicPasswordEncryptor pwdEncryptor = new BasicPasswordEncryptor();
			String pwdEncriptada = pwdEncryptor.encryptPassword( password );
			sqls.add( "INSERT INTO User_details (userID, password, role) VALUES( '" + username + "', '" + pwdEncriptada + "', " + adminRole + ")" );
		}
		return sqls;
	}

}
