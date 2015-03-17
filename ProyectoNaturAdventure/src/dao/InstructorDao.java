package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import common.ConnectionDatabase;

public class InstructorDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(InstructorDao.class.getName()); 
	
	//Buscamos todos los monitores en la base de datos
	public Set<Instructor> getInstructors() {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		Set<Instructor> instructors = new HashSet<Instructor>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Instructor;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				instructors.add(storeInstructor(rs));
			}
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return instructors;
	}
	
	//Devuelve un monitor indicando el nif
	public Instructor getInstructor(int nif) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Instructor instructor = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Instructor " 
												+ "WHERE nif = ?;");
			stmt.setInt(1, nif);
			rs = stmt.executeQuery();
			instructor = storeInstructor(rs);
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return instructor;
	}
	
	private Instructor storeInstructor(ResultSet rs) throws SQLException {
		Instructor instructor = new Instructor();
		
		/*
		instructor.setNIF(rs.getString("nif"));
		instructor.setName(rs.getString("name"));
		instructor.setFirstSurname(rs.getString("firstSurname"));
		instructor.setSecondSurname(rs.getString("secondSurname"));
		instructor.setEmail(rs.getString("email"));
		instructor.setTelephone(rs.getInt("telephone"));
		*/
		
		return instructor;
	}
	
	public void addInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		/*PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"INSERT INTO Instructor(nif, name, firstSurname," +
					"secondSurname, email, telephone)" + 
					" VALUES(?, ?, ?, ?, ?, ?);");
			stmt.setString(1, instructor.getNIF());
			stmt.setString(2, instructor.getName());
			stmt.setString(3, instructor.getFirstSurname());
			stmt.setString(4, instructor.getSecondSurname());
			stmt.setString(5, instructor.getEmail());
			stmt.setInt(6, instructor.getTelephone());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}*/
	}
	
	public void updateInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		/*PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Instructor SET nif = ?, name = ?, firstSurname = ?," +
					"secondSurname = ?, email = ?, telephone = ?" +
					" WHERE nif = ?;");
			stmt.setString(1, instructor.getName());
			stmt.setString(2, instructor.getFirstSurname());
			stmt.setString(3, instructor.getSecondSurname());
			stmt.setString(4, instructor.getEmail());
			stmt.setInt(5, instructor.getTelephone());
			stmt.setString(6, instructor.getNIF());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}*/
	}
	
	public void deleteInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM Instructor WHERE nif = ?;");
			stmt.setString(1, instructor.getNIF());
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
