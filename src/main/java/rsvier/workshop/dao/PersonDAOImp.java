package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class PersonDAOImp implements PersonDAO {

	private Logger logger = LogConnection.getLogger();
	private AccountDAO accountDao = new AccountDAOImp();


	@Override
	public List<Person> getAllPersons() {

		List<Person> personList = new ArrayList<>();

		String query = "SELECT * FROM person";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				Person.PersonBuilder personBuilder = new Person.PersonBuilder();
				personBuilder.personId(resultSet.getInt(1));
				personBuilder.account(accountDao.getAccountById(resultSet.getInt(2)));
				personBuilder.personType(resultSet.getString(3));
				personBuilder.name(resultSet.getString(4));
				personBuilder.lastName(resultSet.getString(5));
				personBuilder.middleName(resultSet.getString(6));
				Person person = personBuilder.build();
				personList.add(person);

			}
			logger.log(Level.WARNING, "Person list successfully returned");
			return personList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public List<Person> getAllPersons(String personType) {

		List<Person> personList = new ArrayList<>();

		String query = "SELECT * FROM person WHERE person_type = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			preparedStatement.setString(1, personType);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				while (resultSet.next()) {
					Person.PersonBuilder personBuilder = new Person.PersonBuilder();
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.account(accountDao.getAccountById(resultSet.getInt(2)));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					Person person = personBuilder.build();
					personList.add(person);
				}
			}
			logger.log(Level.WARNING, "Person list successfully returned");
			return personList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		return null;
	}

	@Override
	public Person getPersonByLastName(String lastName) {

		Person person = null;

		String query = "SELECT * FROM person WHERE last_name = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, lastName);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {
					Person.PersonBuilder personBuilder = new Person.PersonBuilder();
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.account(accountDao.getAccountById(resultSet.getInt(2)));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					person = personBuilder.build();
				}

			}

			logger.log(Level.INFO, "Person succesfully returned");
			return person;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override
	public Person getPersonByAccountId(int accountId) {

		Person person = null;

		String query = "SELECT * FROM person WHERE account_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, accountId);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {
					Person.PersonBuilder personBuilder = new Person.PersonBuilder();
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.account(accountDao.getAccountById(resultSet.getInt(2)));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					person = personBuilder.build();
				}

			}
			logger.log(Level.INFO, "Person succesfully returned ");
			return person;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override
	public int createPerson(Person person) {
		int generatedId = 0;
		String query = "INSERT INTO person (account_id, person_type, name, last_name, middle_name) VALUES(?,?,?,?,?)";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setInt(1, person.getAccount().getAccountId());
			preparedStatement.setString(2, person.getPersonType());
			preparedStatement.setString(3, person.getName());
			preparedStatement.setString(4, person.getLastName());
			preparedStatement.setString(5, person.getMiddleName());

			preparedStatement.executeUpdate();
			
			System.out.println("Person succesfully created");
			logger.log(Level.INFO, "Person succesfully created");
			try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
				if (resultSet.next()) {
					generatedId = resultSet.getInt(1);
					logger.log(Level.INFO, "Key succesfully generated");
				}
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}
		
		return generatedId;

	}

	@Override
	public void updatePerson(Person person) {

		String query = "UPDATE person "
				+ "SET account_id = ?, person_type = ?, name = ?, last_name = ?, middle_name = ? "
				+ "WHERE person_id = ?";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, person.getAccount().getAccountId());
			preparedStatement.setString(2, person.getPersonType());
			preparedStatement.setString(3, person.getName());
			preparedStatement.setString(4, person.getLastName());
			preparedStatement.setString(5, person.getMiddleName());
			preparedStatement.setInt(6, person.getPersonId());

			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Person succesfully updated");
			System.out.println("Person succesfully updated");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void deletePerson(Person person) {

		String query = "DELETE FROM account WHERE account_id = ?";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, person.getAccount().getAccountId());

			preparedStatement.executeUpdate();
			logger.log(Level.INFO, "Person succesfully deleted");
			System.out.println("Person and Account succesfully deleted");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public Person getPersonById(int personId) {

		Person person = null;

		String query = "SELECT * FROM person WHERE person_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, personId);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {
					Person.PersonBuilder personBuilder = new Person.PersonBuilder();
					personBuilder.personId(resultSet.getInt(1));
					personBuilder.account(accountDao.getAccountById(resultSet.getInt(2)));
					personBuilder.personType(resultSet.getString(3));
					personBuilder.name(resultSet.getString(4));
					personBuilder.lastName(resultSet.getString(5));
					personBuilder.middleName(resultSet.getString(6));
					person = personBuilder.build();
				}

			}
			logger.log(Level.INFO, "Person succesfully returned ");
			return person;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;

	}
}