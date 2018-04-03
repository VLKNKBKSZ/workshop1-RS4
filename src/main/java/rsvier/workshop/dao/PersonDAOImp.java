package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

<<<<<<< HEAD
import rsvier.workshop.domain.Person;
import rsvier.workshop.utility.*;

public class PersonDAOImp implements PersonDAO {

	private Logger logger = LogConnection.getLogger();
	private AddressDAO addressDao = new AddressDAOImp();

	@Override
	public List<Person> getAllPersons() {

		List<Person> personList = new ArrayList<Person>();

		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = null;

		String query = "SELECT * FROM person";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {

				personBuilder.personId(rs.getInt(1));
				personBuilder.personType(rs.getString(2));
				personBuilder.name(rs.getString(3));
				personBuilder.lastName(rs.getString(4));
				personBuilder.middleName(rs.getString(5));
				personBuilder.email(rs.getString(6));
				personBuilder.password(rs.getString(7));
				personBuilder.address(addressDao.getAddress(rs.getInt(1)));
				person = personBuilder.build();
				personList.add(person);

			}
			
			return personList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a list of persons", e);

		}
		return null;
	}

	@Override
	public List<Person> getAllPersons(String personType) {

		List<Person> personList = new ArrayList<Person>();

		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = null;

		String query = "SELECT * FROM person WHERE person_type = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {
			ps.setString(1, personType);

			try (ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {

					personBuilder.personId(rs.getInt(1));
					personBuilder.personType(rs.getString(2));
					personBuilder.name(rs.getString(3));
					personBuilder.lastName(rs.getString(4));
					personBuilder.middleName(rs.getString(5));
					personBuilder.email(rs.getString(6));
					personBuilder.password(rs.getString(7));
					personBuilder.address(addressDao.getAddress(rs.getInt(1)));
					person = personBuilder.build();
					personList.add(person);

				}
			}
			
			return personList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a list of persons", e);

		}
		return null;
	}

	@Override
	public Person getPerson(String lastName) {

		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = null;

		String query = "SELECT * FROM person WHERE lastName = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, lastName);

			try (ResultSet rs = ps.executeQuery();) {

				if (!rs.next()) {

					logger.log(Level.WARNING, "Can't find a person");

				} else {
					personBuilder.personId(rs.getInt(1));
					personBuilder.personType(rs.getString(2));
					personBuilder.name(rs.getString(3));
					personBuilder.lastName(rs.getString(4));
					personBuilder.middleName(rs.getString(5));
					personBuilder.email(rs.getString(6));
					personBuilder.password(rs.getString(7));
					person = personBuilder.build();
					personBuilder.address(addressDao.getAddress(rs.getInt(1)));
					

				}

			}

			person = personBuilder.build();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a person", e);

		}

		return person;
=======
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
