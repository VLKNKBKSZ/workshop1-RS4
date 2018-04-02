package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.domain.Customer;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

public class CustomerDAOImp implements CustomerDAO {

	private Logger logger = LogConnection.getLogger();
	private AddressDAO addressDao = new AddressDAOImp();

	@Override
	public List<Customer> getAllCustomers() {

		List<Customer> customerList = new ArrayList<Customer>();

		Customer.CustomerBuilder customerBuilder = new Customer.CustomerBuilder();
		Customer customer = null;

		String query = "SELECT * FROM customer";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);
				ResultSet rs = ps.executeQuery();) {

			while (rs.next()) {

				customerBuilder.customerId(rs.getInt(1));
				customerBuilder.name(rs.getString(2));
				customerBuilder.lastName(rs.getString(3));
				customerBuilder.middleName(rs.getString(4));
				customerBuilder.email(rs.getString(5));
				customerBuilder.address(addressDao.getAddress(rs.getInt(1)));
				customer = customerBuilder.build();
				customerList.add(customer);

			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a list of customers", e);

		}
		return customerList;
	}

	@Override
	public Customer getCustomer(String lastName) {

		Customer.CustomerBuilder customerBuilder = new Customer.CustomerBuilder();
		Customer customer = null;

		String query = "SELECT * FROM customer WHERE lastName = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, lastName);

			try (ResultSet rs = ps.executeQuery();) {

				if (!rs.next()) {

					logger.log(Level.WARNING, "Can't find a customer");

				} else {
					customerBuilder.customerId(rs.getInt(1));
					customerBuilder.name(rs.getString(2));
					customerBuilder.lastName(rs.getString(3));
					customerBuilder.middleName(rs.getString(4));
					customerBuilder.email(rs.getString(5));
					customerBuilder.address(addressDao.getAddress(rs.getInt(1)));

				}

			}

			customer = customerBuilder.build();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find a customer", e);

		}

		return customer;
	}

	@Override
	public void createCustomer(Customer customer) {

		String query = "INSERT INTO customer (name,lastName,middleName,email) VALUES(?,?,?,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getMiddleName());
			ps.setString(4, customer.getEmail());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create a customer", e);

		}

	}

	@Override
	public void updateCustomer(Customer customer) {

		String query = "UPDATE customer " + "SET name = ?, lastName = ?, middleName = ?, email = ? " + "WHERE id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getMiddleName());
			ps.setString(4, customer.getEmail());
			ps.setInt(5, customer.getCustomerId());

			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update the customer", e);

		}
	}

	@Override
	public void deleteCustomer(Customer customer) {

		String query = "DELETE FROM customer WHERE id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {

			ps.setInt(1, customer.getCustomerId());
			ps.executeUpdate();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the customer", e);

		}

	}

}
