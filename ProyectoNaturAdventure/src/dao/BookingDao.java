package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import conexion.ConnectionManager;

public class BookingDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(ActivityDao.class.getName());
		
	public Set<Booking> getBookings() {
		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		}
		catch (ClassNotFoundException e) {
			Log.severe("El driver JDBC no se ha encontrado");
			e.printStackTrace();
			return null;
		}
		catch (SQLException e) {
			Log.severe("Error creando la conexion JDBC");
			e.printStackTrace();
			return null;
		}
		
		Set<Booking> bookings = new HashSet<Booking>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("Select ");
			rs = stmt.executeQuery();
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return bookings;
		
		
		
		
		
		
		
	}
		
}
