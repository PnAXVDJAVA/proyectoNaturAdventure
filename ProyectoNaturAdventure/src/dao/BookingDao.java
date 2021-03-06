package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import common.ConnectionDatabase;

public class BookingDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(BookingDao.class.getName()); 
	
	//Buscamos todas las reservas en la base de datos
	public Set<Booking> getBookings() {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		Set<Booking> bookings = new HashSet<Booking>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Booking;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				bookings.add(storeBooking(rs));
			}
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return bookings;
	}
	
	//Devuelve una reserva indicando el codBooking
	public Booking getBooking(int codBooking) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Booking booking = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Booking " 
												+ "WHERE codBooking = ?;");
			stmt.setInt(1, codBooking);
			rs = stmt.executeQuery();
			rs.next();
			booking = storeBooking(rs);
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return booking;
	}
	
	private Booking storeBooking(ResultSet rs) throws SQLException {
		Booking booking = new Booking();
		booking.setCodBooking(rs.getInt("codBooking"));
		booking.setProposalPerformingDate(rs.getDate("proposalPerformingDate"));
		booking.setNumPartakers(rs.getInt("numPartakers"));
		booking.setBookingDate(rs.getDate("bookingDate"));
		booking.setCustomerNif(rs.getString("customerNif"));
		booking.setStartHour(StartHour.getOpcion(rs.getString("startHour")));
		booking.setStatus(BookingStatus.getOpcion(rs.getString("status")));
		booking.setCodActivity(rs.getInt("codActivity"));
		return booking;
	}
	
	public void addBooking(Booking booking) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
				   "INSERT INTO Booking(codBooking, proposalPerformingDate," +
				   "numPartakers, bookingDate, customerNif, startHour, status, codActivity)" + 
				   " VALUES(?, ?, ?, ?, ?, cast ( ? as StartHour) , cast( ? as BookingStatus), ?);");
			stmt.setInt(1, booking.getCodBooking());
			stmt.setDate(2, booking.getProposalPerformingDate());
			stmt.setInt(3, booking.getNumPartakers());
			stmt.setDate(4, booking.getBookingDate());
			stmt.setString(5, booking.getCustomerNif());
			stmt.setString(6, booking.getStartHour().toString());
			stmt.setString(7, booking.getStatus().toString());
			stmt.setInt(8, booking.getCodActivity());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void updateBooking(Booking booking) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Booking SET proposalPerformingDate = ?," +
							   "numPartakers = ?, bookingDate = ?, customerNif = ?," +
							   "startHour = cast( ? as StartHour) , status = cast( ? as BookingStatus), codActivity = ?" +
							   " WHERE codBooking = ?;");
			stmt.setDate(1, booking.getProposalPerformingDate());
			stmt.setInt(2, booking.getNumPartakers());
			stmt.setDate(3, booking.getBookingDate());
			stmt.setString(4, booking.getCustomerNif());
			stmt.setString(5, booking.getStartHour().toString());
			stmt.setString(6, booking.getStatus().toString());
			stmt.setInt(7, booking.getCodActivity());
			stmt.setInt(8, booking.getCodBooking());
			
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void deleteBooking(Booking booking) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		int codBooking = booking.getCodBooking();
		deleteAssingInstructors(codBooking, connection);
		try {
			stmt = connection.prepareStatement("DELETE FROM Booking WHERE codBooking = ?;");
			stmt.setInt(1, codBooking);
			stmt.execute();
		}  catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	private void deleteAssingInstructors(int codBooking, Connection connection) {
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM booking_assigns WHERE codBooking = ?;");
			stmt.setInt(1, codBooking);
			stmt.execute();
		}  catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		}
	}
	
	public void assignInstructors( Booking booking ) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			
			List<Instructor> assignedInstructorsList = booking.getAssignedInstructors();
			
			for( Instructor instructor: assignedInstructorsList ) {
				
				stmt = connection.prepareStatement(
						   "INSERT INTO Booking_Assigns( codBooking, instructorNif )" + 
						   " VALUES(?, ?);");
				stmt.setInt(1, booking.getCodBooking());
				stmt.setString(2, instructor.getNif());
				stmt.execute();
			}
			
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
}
