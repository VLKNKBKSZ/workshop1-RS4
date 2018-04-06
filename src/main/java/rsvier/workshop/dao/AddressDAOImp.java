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

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {

				// Loop through all rows in address table in the database
				// Set Address properties using Addressbuilder

				Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
				addressBuilder.addressId(resultSet.getInt(1));
				addressBuilder.streetName(resultSet.getString(2));
				addressBuilder.houseNumber(resultSet.getInt(3));
				addressBuilder.additionalHouseNumber(resultSet.getInt(4));
				addressBuilder.postalCode(resultSet.getString(5));
				addressBuilder.city(resultSet.getString(6));
				addressBuilder.country(resultSet.getString(7));

				// Create an address object with the all the set properties
				Address address = addressBuilder.build();

				// Add Address to ArrayList
				addressList.add(address);

			}
			return addressList;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override

	public Address getAddress(int personId) {

		Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
		Address address = null;

		String query = "SELECT * FROM address WHERE person_id = ?";

		try (Connection connection = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(query);) {

			preparedStatement.setInt(1, personId);

			try (ResultSet rs = preparedStatement.executeQuery();) {

				if (rs.next()) {

					addressBuilder.addressId(rs.getInt(1));
					addressBuilder.streetName(rs.getString(2));
					addressBuilder.houseNumber(rs.getInt(3));
					addressBuilder.additionalHouseNumber(rs.getInt(4));
					addressBuilder.postalCode(rs.getString(5));
					addressBuilder.city(rs.getString(6));
					addressBuilder.country(rs.getString(7));
					address = addressBuilder.build();
				}
			}

			logger.log(Level.INFO, "Addres succesfully returned");
			return address;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

		return null;
	}

	@Override

	public void createAddress(Address address, int personId) {

		String query = "INSERT INTO address (street_name,house_number,additional_house_number,postal_code,city,country,person_id) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, address.getStreetName());
			preparedStatement.setInt(2, address.getHouseNumber());
			preparedStatement.setInt(3, address.getAdditionalHouseNumber());
			preparedStatement.setString(4, address.getPostalCode());
			preparedStatement.setString(5, address.getCity());
			preparedStatement.setString(6, address.getCountry());
			preparedStatement.setInt(7, personId);
			preparedStatement.executeUpdate();
			System.out.println("Adress succesfully created");
			logger.log(Level.INFO, "Address succesfully created");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void updateAddress(Address address) {

		String query = "UPDATE address "
				+ "SET street_name = ?,house_number = ?,additional_house_number = ?,postal_code = ?,city = ?,country = ? "
				+ "WHERE address_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, address.getStreetName());
			preparedStatement.setInt(2, address.getHouseNumber());
			preparedStatement.setInt(3, address.getAdditionalHouseNumber());
			preparedStatement.setString(4, address.getPostalCode());
			preparedStatement.setString(5, address.getCity());
			preparedStatement.setString(6, address.getCountry());
			preparedStatement.setInt(7, address.getAddressId());

			preparedStatement.executeUpdate();
			System.out.println("Address succesfully updated");
			logger.log(Level.INFO,"Address succesfully updated");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override

	public void deleteAddressByPersonId(int personId) {

		String query = "DELETE FROM address WHERE person_id=?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, personId);

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@Override
	public void deleteAdressByAddressId(Address address) {
		String query = "DELETE FROM address WHERE id =?;";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query)){
			preparedStatement.setInt(1, address.getAddressId());
			preparedStatement.executeUpdate();
			System.out.println("Address deleted succesfully");
			logger.log(Level.INFO, "Address deleted succesfully");
		} catch (SQLException e) {
			logger.log(Level.INFO, "SQL exception occured", e);
		}
		
	}

}
