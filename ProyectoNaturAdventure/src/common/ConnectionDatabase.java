package common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Logger;

import conexion.ConnectionManager;

public class ConnectionDatabase {
	private Logger log;
	Connection connection = null;
	
	public ConnectionDatabase(Logger log) {
		this.log = log;
	}
	
	public Connection getConnection() {
		try {
			connection = ConnectionManager.getConnection();
		}
		catch (ClassNotFoundException e) {
			log.severe("El driver JDBC no se ha encontrado");
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
			log.severe("Error creando la conexion JDBC");
			e.printStackTrace();
			return null;
		}
		return connection;
	}
	
	public void closeConnections(PreparedStatement stmt, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				log.warning("Error cerrando ResultSet");
				e.printStackTrace();
			}
		}
		closeConnections(stmt);
	}
	
	public void closeConnections(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				log.warning("Error cerrando PreparedStatement");
				e.printStackTrace();
			}
		} try {
			connection.close();
		} catch (SQLException e) {
			log.warning("Error cerrando la conexion JDBC");
			e.printStackTrace();
		}
	}
}
