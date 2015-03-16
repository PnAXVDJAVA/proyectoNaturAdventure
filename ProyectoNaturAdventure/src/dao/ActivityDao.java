package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;

import conexion.ConnectionManager;


public class ActivityDao {
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(ActivityDao.class.getName()); 
	
	//Buscamos todas las actividades en la base de datos
	public Set<Activity> getActivities() {
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
		HashSet<Activity> activities = new HashSet<Activity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("select codActivity, name, description, pricePerPerson,"
					+ " duration, maxPartakers, minPartakers, level from Activity");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Activity activity = new Activity();
				activity.setCodActivity(rs.getInt("codActivity"));
				activity.setName(rs.getString("name"));
				activity.setDescription(rs.getString("description"));
				activity.setPricePerPerson(rs.getInt("pricePerPerson"));
				activity.setDuration(rs.getInt("duration"));
				activity.setMaxPartakers(rs.getInt("maxPartakers"));
				activity.setMinPartakers(rs.getInt("minPartakers"));
				activity.setLevel(Level.getOpcion(rs.getString("level")));
				activities.add(activity);
			}
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
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
		return activities;
	}
	//Devuelve una actividad indicando el codActivity
	public Activity getActivity(int codActivity) {
		
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
			Log.severe("Error creando la connexion JDBC");
			e.printStackTrace();
			return null;
		}
		
		
		
		
		
		
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Activity activity = null;
		try {
			String sentenciaBuscaActivity = "select * from Activity " 
											+ "where codActivity = ?";
			stmt = connection.prepareStatement(sentenciaBuscaActivity);
			stmt.setInt(1, codActivity);
			rs = stmt.executeQuery();
			activity = new Activity();
			activity.setCodActivity(rs.getInt("codActivity"));
			activity.setName(rs.getString("name"));
			activity.setDescription(rs.getString("description"));
			activity.setPricePerPerson(rs.getInt("pricePerPerson"));
			activity.setDuration(rs.getInt("duration"));
			activity.setMaxPartakers(rs.getInt("maxPartakers"));
			activity.setMinPartakers(rs.getInt("minPartakers"));
			activity.setLevel(Level.getOpcion(rs.getString("level")));
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
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
		return activity;
	}
	
	
	public void addActivity(Activity activity) {

		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		}
		catch (ClassNotFoundException e) {
			Log.severe("El driver JDBC no se ha encontrado");
			e.printStackTrace();
			return;
		}
		catch (SQLException e) {
			Log.severe("Error creando la connexión JDBC");
			e.printStackTrace();
			return;
		}
		
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
				   "insert into Activity(codActivity, name, description, pricePerPerson,"
				   + " duration, maxPartakers, minPartakers, level) "
				   + " values(?, ?, ?, ?, ?, ?, ?, ?)");
			stmt.setInt(1, activity.getCodActivity());
			stmt.setString(2, activity.getName());
			stmt.setString(3, activity.getDescription());
			stmt.setInt(4, activity.getPricePerPerson());
			stmt.setInt(5, activity.getDuration());
			stmt.setInt(6, activity.getMaxPartakers());
			stmt.setInt(7, activity.getMinPartakers());
			stmt.setString(8, activity.getLevel().toString());
			stmt.execute();
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
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
	}
	
	public void updateActivity(Activity activity) {

		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		}
		catch (ClassNotFoundException e) {
			Log.severe("El driver JDBC no se ha encontrado");
			e.printStackTrace();
			return;
		}
		catch (SQLException e) {
			Log.severe("Error creando la conexion JDBC");
			e.printStackTrace();
			return;
		}
		
		PreparedStatement stmt = null;
		try {
			stmt = connection.prepareStatement(
				   "update Activity set name = ?, description = ?, pricePerPerson = ?,"
					+ " duration = ?, maxPartakers = ?, minPartakers = ?, level = ?"
					+ " where codActivity = ?");
			stmt.setString(1, activity.getName());
			stmt.setString(2, activity.getDescription());
			stmt.setInt(3, activity.getPricePerPerson());
			stmt.setInt(4, activity.getDuration());
			stmt.setInt(5, activity.getMaxPartakers());
			stmt.setInt(6, activity.getMinPartakers());
			stmt.setString(7, activity.getLevel().toString());
			stmt.setInt(8, activity.getCodActivity());
			stmt.execute();
			
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
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
	}
	
	public void deleteActivity(Activity activity) {

		Connection connection = null;
		try {
			connection = ConnectionManager.getConnection();
		}
		catch (ClassNotFoundException e) {
			Log.severe("El driver JDBC no se ha encontrado");
			e.printStackTrace();
			return;
		}
		catch (SQLException e) {
			Log.severe("Error creando la connexión JDBC");
			e.printStackTrace();
			return;
		}
		
		PreparedStatement stmt = null;
		try {
			String sentenciaBorrar = "delete from Activity where codActivity = ?";
			stmt = connection.prepareStatement(sentenciaBorrar);
			stmt.setInt(1, activity.getCodActivity());
			stmt.execute();
		}  catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
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
	}
}