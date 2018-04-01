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
	public Address getAddress(int customerId) {

		Address.AddressBuilder addressBuilder = new Address.AddressBuilder();
		Address address = null;

		String query = "SELECT * FROM address WHERE customer_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setInt(1, customerId);

			try (ResultSet rs = ps.executeQuery();) {

				if (!rs.next()) {

					logger.log(Level.WARNING, "Can't find an address");
				} else {

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
	public void createAddress(Address address, Customer customer) {

		String query = "INSERT INTO address (streetName,houseNumber,additionalHouseNumber,postalCode,city,country,customer_id) "
				+ "VALUES (?,?,?,?,?,?,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, address.getStreetName());
			ps.setInt(2, address.getHouseNumber());
			ps.setInt(3, address.getAdditionalHouseNumber());
			ps.setString(4, address.getPostalCode());
			ps.setString(5, address.getCity());
			ps.setString(6, address.getCountry());
			ps.setInt(7, customer.getCustomerId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create address", e);

		}

	}

	@Override
	public void updateAddress(Address address, Customer customer) {

		String query = "UPDATE address "
				+ "SET streetName = ?,houseNumber = ?,additionalHouseNumber = ?,postalCode = ?,city = ?,country = ? , customer_id = ? "
				+ "WHERE id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, address.getStreetName());
			ps.setInt(2, address.getHouseNumber());
			ps.setInt(3, address.getAdditionalHouseNumber());
			ps.setString(4, address.getPostalCode());
			ps.setString(5, address.getCity());
			ps.setString(6, address.getCountry());
			ps.setInt(7, customer.getCustomerId());
			ps.setInt(8, address.getAddressId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update address", e);

		}

	}

	@Override
	public void deleteAddress(int customerId) {

		String query = "DELETE FROM address WHERE customer_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setInt(1, customerId);

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the address", e);
			
		}

	}

}
