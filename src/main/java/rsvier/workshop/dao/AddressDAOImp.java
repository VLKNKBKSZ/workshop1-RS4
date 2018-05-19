package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AddressDAOImp implements AddressDAO {

	private Logger logger = LogConnection.getLogger();

	@Override
	public List<Address> getAllAddresses() {

		// Declare an ArrayList to hold all Addresses

		List<Address> addressList = new ArrayList<>();

		String query = "SELECT * FROM address";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				// Loop through all rows in address table in the database
				// Set Address properties using Addressbuilder

				Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
				addressBuilder.addressId(resultSet.getInt(1));
				addressBuilder.person(DAOFactory.getPersonDAO().getPersonById(resultSet.getInt(2)));
				addressBuilder.addressType(resultSet.getString(3));
				addressBuilder.streetName(resultSet.getString(4));
				addressBuilder.houseNumber(resultSet.getInt(5));
				addressBuilder.additionalHouseNumber(resultSet.getString(6));
				addressBuilder.postalCode(resultSet.getString(7));
				addressBuilder.city(resultSet.getString(8));
				addressBuilder.country(resultSet.getString(9));

				// Create an address object with the all the set properties
				Address address = addressBuilder.build();

				// Add Address to ArrayList
				addressList.add(address);

			}
			logger.log(Level.WARNING, "Address list successfully returned");
			return addressList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override
	public List<Address> getAllAddressesForPerson(int personId) {

		List<Address> addressList = new ArrayList<>();

		String query = "SELECT * FROM address WHERE person_id = ?";

		try (Connection connection = DataSource.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, personId);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				while (resultSet.next()) {
					Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
					addressBuilder.addressId(resultSet.getInt(1));
					addressBuilder.person(DAOFactory.getPersonDAO().getPersonById(resultSet.getInt(2)));
					addressBuilder.addressType(resultSet.getString(3));
					addressBuilder.streetName(resultSet.getString(4));
					addressBuilder.houseNumber(resultSet.getInt(5));
					addressBuilder.additionalHouseNumber(resultSet.getString(6));
					addressBuilder.postalCode(resultSet.getString(7));
					addressBuilder.city(resultSet.getString(8));
					addressBuilder.country(resultSet.getString(9));
					Address address = addressBuilder.build();
					addressList.add(address);

				}

				logger.log(Level.INFO, "Address list successfully returned");
				return addressList;
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override

	public void createAddress(Address address) {

		String query = "INSERT INTO address (person_id, address_type, street_name,house_number,additional_house_number,postal_code,city,country) "
				+ "VALUES (?,?,?,?,?,?,?,?)";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, address.getPerson().getPersonId());
			preparedStatement.setString(2, address.getAddressType());
			preparedStatement.setString(3, address.getStreetName());
			preparedStatement.setInt(4, address.getHouseNumber());
			preparedStatement.setString(5, address.getAdditionalHouseNumber());
			preparedStatement.setString(6, address.getPostalCode());
			preparedStatement.setString(7, address.getCity());
			preparedStatement.setString(8, address.getCountry());

			preparedStatement.executeUpdate();

			logger.log(Level.INFO, "Address succesfully created");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void updateAddress(Address address) {

		String query = "UPDATE address "
				+ "SET address_type = ?, street_name = ?,house_number = ?,additional_house_number = ?,postal_code = ?,city = ?,country = ? "
				+ "WHERE address_id = ?";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, address.getAddressType());
			preparedStatement.setString(2, address.getStreetName());
			preparedStatement.setInt(3, address.getHouseNumber());
			preparedStatement.setString(4, address.getAdditionalHouseNumber());
			preparedStatement.setString(5, address.getPostalCode());
			preparedStatement.setString(6, address.getCity());
			preparedStatement.setString(7, address.getCountry());
			preparedStatement.setInt(8, address.getAddressId());

			preparedStatement.executeUpdate();

			logger.log(Level.INFO, "Address succesfully updated");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override

	public void deleteAddressByPersonId(int personId) {

		String query = "DELETE FROM address WHERE person_id=?";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, personId);

			preparedStatement.executeUpdate();

			logger.log(Level.INFO, "Address succesfully deleted");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void deleteAdressByAddressId(Address address) {
		String query = "DELETE FROM address WHERE id =?;";

		try (Connection conn = DataSource.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)) {

			preparedStatement.setInt(1, address.getAddressId());
			preparedStatement.executeUpdate();

			logger.log(Level.INFO, "Address succesfully deleted");

		} catch (SQLException e) {
			logger.log(Level.INFO, "SQL exception occured", e);
		}

	}

}