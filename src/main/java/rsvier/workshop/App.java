package rsvier.workshop;

import java.sql.*;
import java.util.logging.*;
import rsvier.workshop.utility.*;

public class App {
	public static void main(String[] args) {

		try (Connection conn = DatabaseConnectionXML.getConnection()) {
			
			Statement statement = conn.createStatement();
			
			ResultSet resultSet = statement.executeQuery("select name from customer");
			
			while (resultSet.next()) System.out.println(resultSet.getString(1));
			
			PreparedStatement preparedStatement = conn.prepareStatement
					("insert into customer(id, name, lastname, middleName, email)" + 
							"values (?, ?, ?, ?, ?)");
			
			preparedStatement.setInt(1, 4);
			preparedStatement.setString(2, "Bella");
			preparedStatement.setString(3, "Klokka");
			preparedStatement.setString(4, "von");
			preparedStatement.setString(5, "bellavonklokka@gmail.com");
			
			preparedStatement.executeUpdate();
			Logger logger = LogConnection.getLogger();
			
			
		} catch (SQLException ex) {
			ex.getMessage();
		}

		Logger logger = LogConnection.getLogger();
		
		
	}
}
