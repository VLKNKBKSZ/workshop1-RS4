package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

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
	}

	@Override
	public void createPerson(Person person) {
		// TODO Auto-generated method stub
		
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
