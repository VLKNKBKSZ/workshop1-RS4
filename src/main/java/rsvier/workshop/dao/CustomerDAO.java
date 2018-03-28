package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;

public interface CustomerDAO {
	public List<Customer> getAllCustomers();
	public Customer getCustomer(int customerId);
	public void createCustomer(Customer customer);
	public void updateCustomer(Customer customer);
	public void deleteCustomer(Customer customer);
}
