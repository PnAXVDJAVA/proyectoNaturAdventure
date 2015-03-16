package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import conexion.ConnectionManager;

public class CustomerDao {
	
	//creamos el log para poder registrar todos los errores inesperados
		private final static Logger Log = Logger.getLogger(ActivityDao.class.getName()); 
		
		public Set<Customer> getCustomers() {
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
			
			
			Set<Customer> customers = new HashSet<Customer>();
			PreparedStatement stmt = null;
			ResultSet rs = null;
			
			try {
				
				stmt = connection.prepareStatement( "select * from Customer" );
				rs = stmt.executeQuery();
				
				while( rs.next() ) {
					Customer customer = new Customer();
					customer.setNIF( rs.getString( "nif" ) );
					customer.setName( rs.getString( "name" ) );
					customer.setEmail( rs.getString( "email" ) );
					customer.setTelephone( rs.getInt( "telephone" ) );
					customers.add( customer );
				}
				
			}
			
			catch (SQLException e) {
					Log.severe("Error ejecutando preparedStatement");
					e.printStackTrace();
					return null;
			}
			
			finally {
					if (rs != null) {
						try {
							rs.close();
						} catch (SQLException e) {
							Log.warning("Error cerrando ResultSet");
							e.printStackTrace();
						}
					}
					if (stmt != null) {
						try {
							stmt.close();
						} catch (SQLException e) {
							Log.warning("Error cerrando PreparedStatement");
							e.printStackTrace();
						}
					} try {
						connection.close();
					} catch (SQLException e) {
						Log.warning("Error cerrando la conexion JDBC");
						e.printStackTrace();
					}
				}
			
			return customers;
		}

}
