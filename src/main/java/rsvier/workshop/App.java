package rsvier.workshop;

import java.sql.*;
import java.util.logging.*;
import rsvier.workshop.utility.*;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class App {
	public static void main(String[] args) {

		try (Connection conn = DatabaseConnectionXML.getConnection()) {
			
		} catch(SQLException ex) {
			ex.getMessage();
		}
		
		
	}
}
