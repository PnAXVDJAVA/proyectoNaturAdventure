package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import common.ConnectionDatabase;

public class CustomerDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(CustomerDao.class.getName()); 
	
	//Buscamos todos los clientes en la base de datos
	public Set<Customer> getCustomers() {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		Set<Customer> customers = new HashSet<Customer>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Customer;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				customers.add(storeCustomer(rs));
			}
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return customers;
	}
	
	//Devuelve un cliente indicando el nif
	public Customer getCustomer(String nif) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Customer customer = null;
		try {
			stmt = connection.prepareStatement("SELECT * FROM Customer " 
												+ "WHERE nif = ?;");
			stmt.setString(1, nif);
			rs = stmt.executeQuery();
			rs.next();
			customer = storeCustomer(rs);
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return customer;
	}
	
	private Customer storeCustomer(ResultSet rs) throws SQLException {
		Customer customer = new Customer();
		customer.setNIF(rs.getString("nif"));
		customer.setName(rs.getString("name"));
		customer.setFirstSurname(rs.getString("firstSurname"));
		customer.setSecondSurname(rs.getString("secondSurname"));
		customer.setEmail(rs.getString("email"));
		customer.setTelephone(rs.getInt("telephone"));
		return customer;
	}
	
	public void addCustomer(Customer customer) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"INSERT INTO Customer(nif, name, firstSurname," +
					"secondSurname, email, telephone)" + 
					" VALUES(?, ?, ?, ?, ?, ?);");
			stmt.setString(1, customer.getNIF());
			stmt.setString(2, customer.getName());
			stmt.setString(3, customer.getFirstSurname());
			stmt.setString(4, customer.getSecondSurname());
			stmt.setString(5, customer.getEmail());
			stmt.setInt(6, customer.getTelephone());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void updateCustomer(Customer customer) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
					"UPDATE Customer SET name = ?, firstSurname = ?," +
					"secondSurname = ?, email = ?, telephone = ?" +
					" WHERE nif = ?;");
			stmt.setString(1, customer.getName());
			stmt.setString(2, customer.getFirstSurname());
			stmt.setString(3, customer.getSecondSurname());
			stmt.setString(4, customer.getEmail());
			stmt.setInt(5, customer.getTelephone());
			stmt.setString(6, customer.getNIF());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
		} finally {
			c.closeConnections(stmt);
		}
	}
	
	public void deleteCustomer(Customer customer) {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement("DELETE FROM Customer WHERE nif = ?;");
			stmt.setString(1, customer.getNIF());
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
