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


public class ActivityDao {
	
	//creamos el log para poder registrar todos los errores inesperados
	private final static Logger Log = Logger.getLogger(ActivityDao.class.getName()); 
	
	private Activity storeActivities(ResultSet rs) throws SQLException {
		Activity activity = new Activity();
		activity.setCodActivity(rs.getInt("codActivity"));
		activity.setName(rs.getString("name"));
		activity.setDescription(rs.getString("description"));
		activity.setPricePerPerson(rs.getDouble("pricePerPerson"));
		activity.setDuration(rs.getInt("duration"));
		activity.setMaxPartakers(rs.getInt("maxPartakers"));
		activity.setMinPartakers(rs.getInt("minPartakers"));
		activity.setLevel(Level.getOpcion(rs.getString("level")));
		return activity;
	}
	//Buscamos todas las actividades en la base de datos
	public Set<Activity> getActivities() {
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		
		Set<Activity> activities = new HashSet<Activity>();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = connection.prepareStatement("select codActivity, name, description, pricePerPerson,"
					+ " duration, maxPartakers, minPartakers, level from Activity");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Activity activity = storeActivities(rs);
				activities.add(activity);
			}
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return activities;
	}
	//Devuelve una actividad indicando el codActivity
	public Activity getActivity(int codActivity) {
		
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Activity activity = null;
		
		try {
			String sentenciaBuscaActivity = "select * from Activity " 
											+ "where codActivity = ?";
			stmt = connection.prepareStatement(sentenciaBuscaActivity);
			stmt.setInt(1, codActivity);
			rs = stmt.executeQuery();
			rs.next(); ////////////////////////////////////////////////////////////////////////////////////
			activity = storeActivities(rs);
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return null;
		} finally {
			c.closeConnections(stmt, rs);
		}
		return activity;
	}
	
	
	public void addActivity(Activity activity) {

		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					   "insert into Activity(codActivity, name, description, pricePerPerson,"
					   + " duration, maxPartakers, minPartakers, level) "
					   + " values(?, ?, ?, ?, ?, ?, ?, cast(? as ActivityLevel))");
			stmt.setInt(1, activity.getCodActivity());
			stmt.setString(2, activity.getName());
			stmt.setString(3, activity.getDescription());
			stmt.setDouble(4, activity.getPricePerPerson());
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
			c.closeConnections(stmt);
		}
	}
	
	public void updateActivity(Activity activity) {

		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					   "update Activity set name = ?, description = ?, pricePerPerson = ?,"
						+ " duration = ?, maxPartakers = ?, minPartakers = ?, level = cast(? as ActivityLevel)"
						+ " where codActivity = ?");
			stmt.setString(1, activity.getName());
			stmt.setString(2, activity.getDescription());
			stmt.setDouble(3, activity.getPricePerPerson());
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
			c.closeConnections(stmt);
		}
	}
	
	public void deleteActivity(Activity activity) {

		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
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
			c.closeConnections(stmt);
		}
	}
	
	public void addSpecializedInstructors( Activity activity ) { //////////////////////////////////////////////////////7
		
		ConnectionDatabase c = new ConnectionDatabase(Log);
		Connection connection = c.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = connection.prepareStatement(
					   "insert into Specialized(codActivity, instructorNif) "
					   + " values(?, ?)");
			
			List<Instructor> specializedInstructors = activity.getSpecializedInstructors();
			
			for( Instructor instructor: specializedInstructors ) {
				stmt.setInt(1, activity.getCodActivity());
				stmt.setString(2, instructor.getNif());
				stmt.execute();
			}
			
			
		} catch (SQLException e) {
			Log.severe("Error ejecutando preparedStatement");
			e.printStackTrace();
			return;
		} finally {
			c.closeConnections(stmt);
		}
		
	}
}