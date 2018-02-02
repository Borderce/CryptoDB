package cryptocurrency.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnectionService {
	
	private String url = "jdbc:sqlserver://${dbServer};databaseName=${dbName};user=${user};password={${pass}}";
	private Connection connection = null;
	private String databaseName;
	private String serverName;
	
	public DatabaseConnectionService(String dbName, String serverName){
		this.databaseName = dbName;
		this.serverName = serverName;
		
	}
	
	public boolean connect(String user, String pass) {
		
		this.url = url.replace("${dbServer}", this.serverName);
		this.url = url.replace("${dbName}", this.databaseName);
		this.url = url.replace("${user}", user);
		
		if(pass.equals("none")){
			this.url = this.url.replace(";password={${pass}}", "");
		}
		else{
			this.url = url.replace("${pass}", pass);
		}
		
		System.out.println(url + " URL");
		try {
			this.connection = DriverManager.getConnection(url);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	public Connection getConnection() {
		return this.connection;
	}
	
	public void closeConnection() {
		try {
			this.connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	

}
