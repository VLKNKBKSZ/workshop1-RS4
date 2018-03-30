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

			// Loop through all rows in address table in the database
			while (rs.next()) {

				// Set Address properties using Addressbuilder
				Address.AddressBuilder ab = new Address.AddressBuilder();
				ab.addressId(rs.getInt(1));
				ab.streetName(rs.getString(2));
				ab.houseNumber(rs.getInt(3));
				ab.additionalHouseNumber(rs.getInt(4));
				ab.postalCode(rs.getString(5));
				ab.city(rs.getString(6));
				ab.country(rs.getString(7));

				// Create an address object with the all the set properties
				Address address = ab.build();

				// Add Adress to ArrayList
				addressList.add(address);

			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't get a list of addresses");
			e.printStackTrace();

		}

		return addressList;
	}

	@Override
	public Address getAddress(Customer customer) {

		Address.AddressBuilder ab = new Address.AddressBuilder();
		Address address = null;

		String query = "SELECT * FROM address WHERE customer_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setInt(1, customer.getCustomerId());

			try (ResultSet rs = ps.executeQuery();) {

				while (rs.next()) {

					ab.addressId(rs.getInt(1));
					ab.streetName(rs.getString(2));
					ab.houseNumber(rs.getInt(3));
					ab.additionalHouseNumber(rs.getInt(4));
					ab.postalCode(rs.getString(5));
					ab.city(rs.getString(6));
					ab.country(rs.getString(7));
				}
			}

			address = ab.build();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't find an address");
			e.printStackTrace();
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
			logger.log(Level.WARNING, "Can't create address");
			e.printStackTrace();
		}

	}

	@Override
	public void updateAddress(Address address, Customer customer) {

		String query = "UPDATE address "
				+ "SET streetName = ?,houseNumber = ?,additionalHouseNumber = ?,postalCode = ?,city = ?,country = ?,customer_id = ? "
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
			logger.log(Level.WARNING, "Can't update address");
			e.printStackTrace();
		}

	}

	@Override
	public void deleteAddress(Customer customer) {

		String query = "DELETE FROM address WHERE customer_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setInt(1, customer.getCustomerId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the address");
			e.printStackTrace();
		}

	}

}
