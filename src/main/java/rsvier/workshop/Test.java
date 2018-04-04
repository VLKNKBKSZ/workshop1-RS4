package rsvier.workshop;

import java.sql.*;

import java.util.logging.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;


public class Test {
	
	private static Logger logger = LogConnection.getLogger();

	public static void main(String[] args) {
		
		try (Connection conn = DatabaseConnectionXML.getConnection();){
			
		
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't connect to the databse", e.getMessage());

		}

	}

}
