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
	
	public void addDegree(Degree degree) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"INSERT INTO Instructor_Degrees(instructorNif, codDegree, description, "
					+ "name) values(?, ?, ?, ?)");
			stmt.setString(1, degree.getInstructorNif());
			stmt.setInt(2, degree.getCodDegree());
			stmt.setString(3, degree.getDescription());
			stmt.setString(4, degree.getName());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void updateDegree(Degree degree) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Instructor_Degrees SET instructorNif = ?, description = ?, "
					+ "name = ? where codDegree = ?");
			stmt.setString(1, degree.getInstructorNif());
			stmt.setString(2, degree.getDescription());
			stmt.setString(3, degree.getName());
			stmt.setInt(4, degree.getCodDegree());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void deleteDegree(String instructorNif) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM Instructor_Degrees"
					+ " WHERE instructorNif = ?;");
			stmt.setString(1, instructorNif);
			stmt.execute();
		}  catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
			c.closeConnections(stmt);
		}
	}
}
