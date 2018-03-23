package rsvier.workshop;

import java.sql.*;
import rsvier.workshop.utility.DatabaseConnectionXML;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		try (Connection conn = DatabaseConnectionXML.getConnection()) {
		} catch (SQLException ex) {
			ex.getMessage();
		}
	}
}
