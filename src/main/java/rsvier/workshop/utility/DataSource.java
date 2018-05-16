package rsvier.workshop.utility;

import java.sql.*;

import com.mongodb.*;
import com.zaxxer.hikari.*;

import rsvier.workshop.App;

public class DataSource {

	private static HikariConfig hikariConfig;
	private static HikariDataSource ds;
	
	static {
		
		if (App.hikariEnabled) {
			hikariConfig = new HikariConfig("hikari.properties");
			ds = new HikariDataSource(hikariConfig);
		}
	}

	public static Connection getConnection() {

		if (App.hikariEnabled) {

			try {

				return ds.getConnection();

			} catch (SQLException e) {
				
				e.printStackTrace();
			
			}
		}
		try {

			return DatabaseConnectionXML.getConnection();

		} catch (SQLException e) {

			e.printStackTrace();
		}
		return null;
	}
	
	
}
