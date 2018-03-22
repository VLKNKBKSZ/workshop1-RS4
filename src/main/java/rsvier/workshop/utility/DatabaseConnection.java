package rsvier.workshop.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	// This class should only be used to make connection with the database and
	// interact with it.

	/*
	 * Declaring all the variables inside the class. Later on we will provide this
	 * information true a xml file So the collaboration on this projects is open for
	 * modification
	 */
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/evvo?verifyServerCertificate=false&useSSL=true";
	private static final String USER = "root";
	private static final String PASSWORD = "admin";

	/*
	 * The function is static so you can call it without creating a object. to call
	 * the function use MySQLJDBCUtility.getConnection(); and enjoy the connection
	 * with the database.
	 */

	public static Connection getConnection() throws SQLException {

		Connection conn = null;

		try {
			System.out.println("Connecting to the database...");
			conn = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
			System.out.println("Connected to the database.");

		} catch (SQLException ex) {
			System.out.println(ex.getMessage());

		}
		/*
		 * Check if the connection was successful. If the connection is successful, conn
		 * is not null, so we should close the connection to save more resources.
		 */

		return conn;
	}
}
