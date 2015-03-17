package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;

import common.ConnectionDatabase;

public class DegreeDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(DegreeDao.class.getName()); 

	public List<Degree> getInstructorDegrees( String nif ) {

		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<Degree> degreeList;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Instructor_Degrees " 
												+ "WHERE nif = ?;");
			stmt.setString(1, nif);
			rs = stmt.executeQuery();
			rs.next();
			degreeList = new LinkedList<>();
			while( rs.next() ) {
				Degree degree = new Degree();
				degree.setInstructorNif( rs.getString( "instructorNif" ) );
				degree.setCodDegree( rs.getInt( "codDegree" ) );
				degree.setDescription( rs.getString( "description" ) );
				degree.setName( rs.getString( "name" ) );
				
				degreeList.add( degree );
			}
			
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return degreeList;
	}

}
