package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.domain.*;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;


public class PersonDAOImp implements PersonDAO {
	
	
	private Logger logger = LogConnection.getLogger();
	private AddressDAO ad = new AddressDAOImp();
	


	@Override
	public List<Person> getAllPersons(){
		return null;
	}
	
	
	@Override
	public List<Person> getAllPersons(String personType) {
		return null;
	}

	
	@Override
	public Person getPerson(String lastName) {
		
		return null;
	}

	@Override
	public void createPerson(Person person) {
		
		String query = "INSERT INTO person (person_type, name, last_name, middle_name) VALUES(?,?,?,?)";
		
		try(Connection connection = DatabaseConnectionXML.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			
			preparedStatement.setString(1, person.getPersonType());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getLastName());
			preparedStatement.setString(4, person.getMiddleName());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create a person." , e);
			
		}
		
	}

	@Override
	public void updatePerson(Person person) {
		
		String query = "UPDATE person " +
				"SET person_type = ?, name = ?, last_name = ?, middle_name = ? " +
				"WHERE person_id = ?";
		
		try (Connection connection = DatabaseConnectionXML.getConnection();
			 PreparedStatement preparedStatement = connection.prepareStatement(query);){
			
			
			preparedStatement.setString(1, person.getPersonType());
			preparedStatement.setString(2, person.getName());
			preparedStatement.setString(3, person.getLastName());
			preparedStatement.setString(4, person.getMiddleName());
			preparedStatement.setInt(7, person.getPersonId());
			
			preparedStatement.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update the person." ,e);
			
		}
	}

	@Override
	public void deletePerson(Person person) {
		
		String query = "DELETE FROM person WHERE person_id = ?";
		
		try(Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, person.getPersonId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the person." , e);
			
		}
		
	}

}
