package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Database {

	private final String username = "root";
	private final String password = "";
	private final String host = "localhost:3306";
	private final String database = "stellarfest";
	private final String url = String.format("jdbc:mysql://%s/%s", host, database);
	
	private Connection connection;
	private Statement statement;
	
	// Singleton
	private static Database instance;
	
	public static Database getInstance() {
		if(instance == null) {
			return new Database();
		}
		return instance;
	}
	
	private Database() {
		try {
			connection = DriverManager.getConnection(url, username, password);
			statement = connection.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public PreparedStatement preparedStatement(String query) {
		PreparedStatement ps = null;
		try {
			ps = connection.prepareStatement(query);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ps;
	}

}
