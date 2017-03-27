package net.tutorial.utilities;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataService {
	
	public static final int INSERT_RECORD = 1;
	public static final int UPDATE_RECORD = 2;
	
	DBService dbService = null;
	private Connection dbConnection = null;
	private PreparedStatement ps = null;
	
	public DataService() {
		dbService = DBService.getInstance();
		createTable();
	}
	
	public ArrayList<Map<String, Object>> allRecords() {

		dbConnection = dbService.getConnection();

		ArrayList<Map<String, Object>> records = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = null;
		String sSQL = "SELECT _id, name, email, mobile " + "FROM `contacts`";

		ResultSet rs = null;

		try {
			ps = this.dbConnection.prepareStatement(sSQL);
			rs = ps.executeQuery(sSQL);

			while (rs.next()) {
				record = new HashMap<String, Object>();
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("mobile", rs.getString("mobile"));
				records.add(record);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbService.cleanUp();
		}

		return records;
	}
	
	private void createTable() {
		dbConnection = dbService.getConnection();

		String createTableSQL = "CREATE TABLE IF NOT EXISTS `contacts` (" + "`_id` int(11) NOT NULL AUTO_INCREMENT,"
				+ "`name` varchar(45) DEFAULT NULL," + "`email` varchar(45) DEFAULT NULL,"
				+ "`mobile` varchar(45) DEFAULT NULL," + "PRIMARY KEY (`_id`)"
				+ ") ENGINE=InnoDB DEFAULT CHARSET=utf8;";

		try {
			ps = dbConnection.prepareStatement(createTableSQL);
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbService.cleanUp();
		}
	}
	
	public void deleteRecord(int id) {
		dbConnection = dbService.getConnection();

		try {
			String sSQL = "DELETE FROM `contacts` WHERE _id=?";
			ps = dbConnection.prepareStatement(sSQL);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbService.cleanUp();
		}
	}
	
	public Map<String, Object> findRecord(int id) { 
		dbConnection = dbService.getConnection();
		
		Map<String, Object> record = new HashMap<String, Object>();
		ResultSet rs = null;
		
		try {
			String sSQL = "SELECT * FROM `contacts` WHERE _id=?";
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			if (rs.next()) {
				record.put("_id", rs.getInt("_id"));
				record.put("name", rs.getString("name"));
				record.put("email", rs.getString("email"));
				record.put("mobile", rs.getString("mobile"));
			}

			rs.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbService.cleanUp();
		}
		return record;
	}
	
	public void updateRecord(int transaction, Map<String, Object> record) {
		dbConnection = dbService.getConnection();

		String sSQL = null;

		if (transaction == UPDATE_RECORD) {
			sSQL = "UPDATE `contacts` " + "SET name = ? , email = ? , mobile = ? " + "WHERE _id = ?";
		} else {
			sSQL = "INSERT INTO `contacts`" + "(`name`, `email`, `mobile`) VALUES" + "(?,?,?)";
		}

		try {
			ps = this.dbConnection.prepareStatement(sSQL);
			ps.setString(1, (String) record.get("name"));
			ps.setString(2, (String) record.get("email"));
			ps.setString(3, (String) record.get("mobile"));
			if (transaction == UPDATE_RECORD) {
				ps.setInt(4, (int) record.get("_id"));
			}
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			dbService.cleanUp();
		}
	}

		
	
}
