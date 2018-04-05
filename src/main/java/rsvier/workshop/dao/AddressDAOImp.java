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

		List<Address> addressList = new ArrayList<Address>();

		String query = "SELECT * FROM address";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {

				// Loop through all rows in address table in the database
				// Set Address properties using Addressbuilder

				Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
				addressBuilder.addressId(rs.getInt(1));
				addressBuilder.streetName(rs.getString(2));
				addressBuilder.houseNumber(rs.getInt(3));
				addressBuilder.additionalHouseNumber(rs.getInt(4));
				addressBuilder.postalCode(rs.getString(5));
				addressBuilder.city(rs.getString(6));
				addressBuilder.country(rs.getString(7));

				// Create an address object with the all the set properties
				Address address = addressBuilder.build();

				// Add Adress to ArrayList
				addressList.add(address);

			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find an address list", e);

		}

		return addressList;
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

				if (!rs.next()) {

					logger.log(Level.WARNING, "Can't find an address");
					
				}else {
					
					addressBuilder.addressId(rs.getInt(1)); 
					addressBuilder.streetName(rs.getString(2));
					addressBuilder.houseNumber(rs.getInt(3));
					addressBuilder.additionalHouseNumber(rs.getInt(4));
					addressBuilder.postalCode(rs.getString(5));
					addressBuilder.city(rs.getString(6));
					addressBuilder.country(rs.getString(7));
				}
			}

			address = addressBuilder.build();


		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find an address", e);

		}

		return address;
	}

	@Override

	public void createAddress(Address address, Person person ) {


		String query = "INSERT INTO address (street_name,house_number,additional_house_number,postal_code,city,country,person_id) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, address.getStreetName());
			ps.setInt(2, address.getHouseNumber());
			ps.setInt(3, address.getAdditionalHouseNumber());
			ps.setString(4, address.getPostalCode());
			ps.setString(5, address.getCity());
			ps.setString(6, address.getCountry());
			ps.setInt(7, person.getPersonId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create address", e);

		}

	}

	@Override
	public void updateAddress(Address address, Person person) {

		String query = "UPDATE address "
				+ "SET street_name = ?,house_number = ?,additional_house_number = ?,postal_code = ?,city = ?,country = ? , person_id = ? "
				+ "WHERE address_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, address.getStreetName());
			ps.setInt(2, address.getHouseNumber());
			ps.setInt(3, address.getAdditionalHouseNumber());
			ps.setString(4, address.getPostalCode());
			ps.setString(5, address.getCity());
			ps.setString(6, address.getCountry());
			ps.setInt(7, person.getPersonId());
			ps.setInt(8, address.getAddressId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update address", e);

		}

	}

	@Override

	public void deleteAddress(int personId) {


		String query = "DELETE FROM address WHERE person_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {


			ps.setInt(1, personId);

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the address", e);
			
		}

	}

}
