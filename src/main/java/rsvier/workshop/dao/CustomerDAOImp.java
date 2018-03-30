package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.domain.Customer;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

public class CustomerDAOImp implements CustomerDAO {
	
	
	private Logger logger = LogConnection.getLogger();
	private AddressDAO ad = new AddressDAOImp();
	


	@Override
	public List<Customer> getAllCustomers() {
		
		List<Customer> customerList = new ArrayList<Customer>();
		
		Customer.CustomerBuilder cb = new Customer.CustomerBuilder();
		Customer customer = null;
		
		String query = "SELECT * FROM customer";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();) {
			
			
			while(rs.next()){
				
				
				cb.customerId(rs.getInt(1));
				cb.name(rs.getString(2));
				cb.lastName(rs.getString(3));
				cb.middleName(rs.getString(4));
				cb.email(rs.getString(5));
				customer = cb.build();
				cb.address(ad.getAddress(customer));
				customer = cb.build();
				customerList.add(customer);
				
				
			}
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	public Customer getCustomer(String lastName) {
		
		Customer.CustomerBuilder cb = new Customer.CustomerBuilder();
		Customer customer = null;
		
		
		String query = "SELECT * FROM customer WHERE lastName = ?";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);	) {
			
			ps.setString(1, lastName);
			
			
		try(ResultSet rs = ps.executeQuery();){
			
			
			while(rs.next()){
				
				cb.customerId(rs.getInt(1));
				cb.name(rs.getString(2));
				cb.lastName(rs.getString(3));
				cb.middleName(rs.getString(4));
				cb.email(rs.getString(5));
				
			}
				
		}
		
		customer = cb.build();
		cb.address(ad.getAddress(customer));
		customer = cb.build();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't find a customer");
			
			e.printStackTrace();
		}
		
		return customer;
	}

	@Override
	public void createCustomer(Customer customer) {
		
		String query = "INSERT INTO customer (name,lastName,middleName,email) VALUES(?,?,?,?)";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
			PreparedStatement ps = conn.prepareStatement(query);) {
			
			
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getMiddleName());
			ps.setString(4, customer.getEmail());
			
			ps.executeUpdate();
			
			
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't create a customer");
			e.printStackTrace();
		}
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		
		String query = "UPDATE customer " +
				"SET name = ?, lastName = ?, middleName = ?, email = ? " +
				"WHERE id = ?";
		
		try (Connection conn = DatabaseConnectionXML.getConnection();
			 PreparedStatement ps = conn.prepareStatement(query);){
			
			
			ps.setString(1, customer.getName());
			ps.setString(2, customer.getLastName());
			ps.setString(3, customer.getMiddleName());
			ps.setString(4, customer.getEmail());
			ps.setInt(5, customer.getCustomerId());
			
			ps.executeUpdate();
			
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't update the customer");
			e.printStackTrace();
		}
	}

	@Override
	public void deleteCustomer(Customer customer) {
		
		String query = "DELETE FROM customer WHERE id = ?";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement ps = conn.prepareStatement(query);) {
			
			ps.setInt(1, customer.getCustomerId());
			ps.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Can't delete the customer");
			e.printStackTrace();
		}
		
	}

}
