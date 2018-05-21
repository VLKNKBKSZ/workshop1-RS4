package workshop1;

import java.util.logging.*;

import org.junit.jupiter.api.*;

import java.sql.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.utility.*;

public class AddressDAOImpTest {

	AddressDAO addressDAO = new AddressDAOImp();
	private Logger logger = LogConnection.getLogger();
	
	@BeforeEach
	void setUp() {
		try(//first create account
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();) {
			
			String query1 = "INSERT INTO account (email, password) VALUES ('evg@gmail.com', 'weightword') ";
			String query2 = "INSERT INTO account (email, password) VALUES ('mymail@mail.com', 'wochtward') ";
			statement.addBatch(query1);
			statement.addBatch(query2);
			statement.executeBatch();
			logger.log(Level.INFO, "Accounts created in database");
			
			//second create person
			String query3 = "INSERT INTO person (account_id, person_type, name, last_name, middle_name)"
			+ "VALUES (1, 'employee', 'Gerard', 'Hoppema', 'de')";
			String query4 = "INSERT INTO person (account_id, person_type, name, last_name, middle_name)"
			+ "VALUES (2, 'customer', 'Sjantal', 'Kookie', 'het')";
			statement.addBatch(query3);
			statement.addBatch(query4);
			statement.executeBatch();
			logger.log(Level.INFO, "Persons created in database");
			
			//third create address
			String query5 = "INSERT INTO address (person_id, street_name, house_number, additional_house_number,"
					+ "postal_code, city, country) VALUES (1, 'Loofhof', 5, 'b', '1234kl', 'Nijmegen', 'Nederland')";
			String query6 = "INSERT INTO address (person_id, street_name, house_number, additional_house_number,"
					+ "postal_code, city, country) VALUES (2, 'Straatje', 15, 'c', '1114kl', 'Hoogstad', 'Belgie')";
			statement.addBatch(query5);
			statement.addBatch(query6);
			statement.executeBatch();
			logger.log(Level.INFO, "Address created in Database");
			}
		
		catch (SQLException e) {
			logger.log(Level.WARNING, "Database setup failed", e);
		}
		
	}
	
	@AfterEach
	void tearDown() {
		try(
			Connection connection = DataSource.getConnection();
			Statement statement = connection.createStatement();) {
			
			String query1 = "DELETE FROM address";
			String query2 = "ALTER TABLE address AUTO_INCREMENT = 1";
			statement.addBatch(query1);
			statement.addBatch(query2);
			statement.executeBatch();
			logger.log(Level.INFO, "Records in address table successfully deleted");
			
			String query3 = "DELETE FROM person";
			String query4 = "ALTER TABLE person AUTO_INCREMENT = 1";
			statement.addBatch(query3);
			statement.addBatch(query4);
			statement.executeBatch();
			logger.log(Level.INFO, "Records in person table successfully deleted");
			
			String query5 = "DELETE FROM account";
			String query6 = "ALTER TABLE account AUTO_INCREMENT = 1";
			statement.addBatch(query5);
			statement.addBatch(query6);
			statement.executeBatch();
			logger.log(Level.INFO, "Records in account table successfully deleted");
			
		}
		catch(SQLException e) {
			logger.log(Level.WARNING, "Database teardown failed", e);
		}
	}
	
	@Test
	void canCreateAddress() {
		// address object
		// address dao create - so create in database
		//  show that address created in database is equal to asserted outcome
		
	}
}
