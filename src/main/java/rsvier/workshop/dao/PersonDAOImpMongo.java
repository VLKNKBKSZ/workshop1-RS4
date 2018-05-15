package rsvier.workshop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import rsvier.workshop.domain.Person;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;



public class PersonDAOImpMongo implements PersonDAO{
	
	private Logger logger = LogConnection.getLogger();

	@Override
	public List<Person> getCustomerByLastName(String lastname) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonByAccountId(int accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Person getPersonById(int personId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createPerson(Person person) {
		int generatedId = 0;
		String query = "//Mongo Query";

	/*	try (Connection connection = ??MONGOCONNECTIONFILE??.getConnection());
				 {

			//STATEMENTS FOR MONGO DATABASE??

			logger.log(Level.INFO, "Person succesfully created");
			}
			}
		} catch (???Exception e) {
			logger.log(Level.WARNING, "??? exception occured", e);

		}
	 
	 */
		return generatedId;
		
	}

	@Override
	public void updatePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePerson(Person person) {
		// TODO Auto-generated method stub
		
	}

}
