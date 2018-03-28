package rsvier.workshop.dao;

import java.util.*;

import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Customer;

public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(int addressId);
	public void createAddress(Address address, Customer customer);
	public void updateAddress(Address address , Customer customer);
	public void deleteAddress(Address address );
}
