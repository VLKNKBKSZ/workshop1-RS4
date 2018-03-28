package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Customer;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

public class AddressDAOImp implements AddressDAO {

	private PreparedStatement ps = null;
	private ResultSet rs = null;
	private String query = "";
	private Logger logger = LogConnection.getLogger();

	@Override
	public List<Address> getAllAddresses() {

		// Declare an ArrayList to hold all Addresses

		List<Address> addressList = new ArrayList<Address>();

		try (Connection conn = DatabaseConnectionXML.getConnection();) {

			query = "SELECT * FROM address";
			ps = conn.prepareStatement(query);
			rs = ps.executeQuery();

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
			logger.log(Level.WARNING, "Can't connect to the databse", e.getMessage());

		}

		return addressList;
	}

	@Override
	public Address getAddress(int addressId) {

		Address.AddressBuilder ab = new Address.AddressBuilder();
		Address address = null;

		try (Connection conn = DatabaseConnectionXML.getConnection();) {

			query = "SELECT * FROM address WHERE id = ?";
			ps = conn.prepareStatement(query);
			ps.setInt(1, addressId);
			rs = ps.executeQuery();

			while (rs.next()) {

				ab.addressId(rs.getInt(1));
				ab.streetName(rs.getString(2));
				ab.houseNumber(rs.getInt(3));
				ab.additionalHouseNumber(rs.getInt(4));
				ab.postalCode(rs.getString(5));
				ab.city(rs.getString(6));
				ab.country(rs.getString(7));

			}

			address = ab.build();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't connect to the databse", e.getMessage());
		}

		return address;
	}

	@Override
	public void createAddress(Address address , Customer customer) {

		try  {
			Connection conn = DatabaseConnectionXML.getConnection();
			query = "INSERT INTO address (streetName,houseNumber,additionalHouseNumber,postalCode,city,country,customer_id)"
					+ "VALUES (?,?,?,?,?,?,?)";

			ps = conn.prepareStatement(query);

			ps.setString(1, address.getStreetName());
			ps.setInt(2, address.getHouseNumber());
			ps.setInt(3, address.getAdditionalHouseNumber());
			ps.setString(4, address.getPostalCode());
			ps.setString(5, address.getCity());
			ps.setString(6, address.getCountry());
			ps.setInt(7, customer.getCustomerId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create address", e.getStackTrace());
		}

	}

	@Override
	public void updateAddress(Address address, Customer customer) {

		try (Connection conn = DatabaseConnectionXML.getConnection();) {

			query = "UPDATE address"
					+ "SET streetName = ?,houseNumber = ?,additionalHouseNumber = ?,postalCode = ?,city = ?,country = ?,customer_id = ?"
					+ "WHERE id = ?";

			ps = conn.prepareStatement(query);
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
			logger.log(Level.WARNING, "Can't connect to the databse", e.getMessage());
		}

	}

	@Override
	public void deleteAddress(Address address) {

		try (Connection conn = DatabaseConnectionXML.getConnection();) {

			query = "DELETE FROM address WHERE id = ?";

			ps = conn.prepareStatement(query);
			ps.setInt(1, address.getAddressId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't connect to the databse", e.getMessage());
		}

	}

}
