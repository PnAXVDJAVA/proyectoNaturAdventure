package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
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
	public Instructor getInstructor(String nif) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Instructor instructor = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Instructor " 
												+ "WHERE nif = ?;");
			stmt.setString(1, nif);
			rs = stmt.executeQuery();
			rs.next();
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

		String nif = rs.getString( "nif" );
		instructor.setNif( nif );
		instructor.setName( rs.getString( "name" ) );
		instructor.setFirstSurname( rs.getString( "firstSurname" ) );
		instructor.setSecondSurname( rs.getString( "secondSurname" ) );
		instructor.setAddress( rs.getString( "address" ) );
		instructor.setTelephone( rs.getInt( "telephone" ) );
		instructor.setDateOfBirth( rs.getDate(  "dateOfBirth" ) );
		instructor.setEmail( rs.getString(  "email" ) );
		instructor.setBankAccount( rs.getString( "bankAccount" ) );
		instructor.setUserID( rs.getString( "userID" ));
		instructor.setPassword( rs.getString( "password" ) );		
		instructor.setDegrees( new DegreeDao().getInstructorDegrees( nif ) );
		
		return instructor;
	}
	
	
	
	public void addInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"INSERT INTO Instructor(nif, name, firstSurname," +
					"secondSurname, address, telephone, dateOfBirth," +
					"email, bankAccount, userID, password)" + 
					" VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);");
			stmt.setString(1, instructor.getNif());
			stmt.setString(2, instructor.getName());
			stmt.setString(3, instructor.getFirstSurname());
			stmt.setString(4, instructor.getSecondSurname());
			stmt.setString(5, instructor.getAddress());
			stmt.setInt(6, instructor.getTelephone());
			stmt.setDate(7, instructor.getDateOfBirth());
			stmt.setString(8, instructor.getEmail());
			stmt.setString(9, instructor.getBankAccount());
			stmt.setString(10, instructor.getUserID());
			stmt.setString(11, instructor.getPassword());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void updateInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Instructor SET name = ?, firstSurname = ?," +
					"secondSurname = ?, address = ?, telephone = ?, dateOfBirth = ?," +
					"email = ?, bankAccount = ?, userID = ?, password = ?" +
					" where nif = ?;");
			
			stmt.setString(1, instructor.getName());
			stmt.setString(2, instructor.getFirstSurname());
			stmt.setString(3, instructor.getSecondSurname());
			stmt.setString(4, instructor.getAddress());
			stmt.setInt(5, instructor.getTelephone());
			stmt.setDate(6, instructor.getDateOfBirth());
			stmt.setString(7, instructor.getEmail());
			stmt.setString(8, instructor.getBankAccount());
			stmt.setString(9, instructor.getUserID());
			stmt.setString(10, instructor.getPassword());
			stmt.setString(11, instructor.getNif());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void deleteInstructor(Instructor instructor) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM Instructor WHERE nif = ?;");
			stmt.setString(1, instructor.getNif());
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
