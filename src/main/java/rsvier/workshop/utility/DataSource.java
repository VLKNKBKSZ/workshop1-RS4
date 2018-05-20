package rsvier.workshop.utility;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.zaxxer.hikari.*;
import rsvier.workshop.App;

public class DataSource {

	private static HikariConfig hikariConfig;
	private static HikariDataSource ds;

	private static Logger logger = LogConnection.getLogger();

	/*
	 * Static initializer, runs as soon as the class is loaded. before any static
	 * method is called and even before any static variable can be used
	 */

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
				logger.log(Level.WARNING, "SQL Exception occured, connection with hikari connection pool failed", e);

			}
		}
		try {

			return DatabaseConnectionXML.getConnection();

		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL Exception occured, connection with JDBC connection pool failed", e);
		}
		return null;
	}

}
