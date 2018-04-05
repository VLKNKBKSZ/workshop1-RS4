package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.*;
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
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				personBuilder.personId(resultSet.getInt(1));
				personBuilder.accountId(resultSet.getInt(2));
				personBuilder.personType(resultSet.getString(3));
				personBuilder.name(resultSet.getString(4));
				personBuilder.lastName(resultSet.getString(5));
				personBuilder.middleName(resultSet.getString(6));
				personBuilder.address(addressDao.getAddress(resultSet.getInt(1)));
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
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setString(1, personType);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				while (resultSet.next()) {

					personBuilder.personId(resultSet.getInt(1));
					personBuilder.accountId(resultSet.getInt(2));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					personBuilder.address(addressDao.getAddress(resultSet.getInt(1)));
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
	public Person getPersonByLastName(String lastName) {

		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = null;

		String query = "SELECT * FROM person WHERE last_name = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, lastName);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (!resultSet.next()) {

					logger.log(Level.WARNING, "Can't find a person");

				} else {
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.accountId(resultSet.getInt(2));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					personBuilder.address(addressDao.getAddress(resultSet.getInt(1)));
				}

			}

			person = personBuilder.build();
			return person;
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a person", e);

		}

		return null;
	}

	@Override
	public Person getPersonById(int accountId) {
		
		Person.PersonBuilder personBuilder = new Person.PersonBuilder();
		Person person = null;

		String query = "SELECT * FROM person WHERE account_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, accountId);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (!resultSet.next()) {

					logger.log(Level.WARNING, "Can't find a person");

				} else {
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.accountId(resultSet.getInt(2));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					personBuilder.address(addressDao.getAddress(resultSet.getInt(1)));
				}

			}

			person = personBuilder.build();
			return person;
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a person", e);

		}


		return null;
	}
	
	@Override
	public void createPerson(Person person) {

		String query = "INSERT INTO person (account_id, person_type, name, last_name, middle_name) VALUES(?,?,?,?,?)";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, person.getAccountId());
			preparedStatement.setString(2, person.getPersonType());
			preparedStatement.setString(3, person.getName());
			preparedStatement.setString(4, person.getLastName());
			preparedStatement.setString(5, person.getMiddleName());
			
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create a person.", e);

		}

	}

	@Override
	public void updatePerson(Person person) {

		String query = "UPDATE person " + "SET account_id = ?, person_type = ?, name = ?, last_name = ?, middle_name = ? "
				+ "WHERE person_id = ?";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {
			
			preparedStatement.setInt(1, person.getAccountId());
			preparedStatement.setString(2, person.getPersonType());
			preparedStatement.setString(3, person.getName());
			preparedStatement.setString(4, person.getLastName());
			preparedStatement.setString(5, person.getMiddleName());
			preparedStatement.setInt(6, person.getPersonId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update the person.", e);

		}

	}

	@Override
	public void deletePerson(Person person) {

		String query = "DELETE FROM person WHERE person_id = ?";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, person.getPersonId());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the person.", e);

		}

	}

}
