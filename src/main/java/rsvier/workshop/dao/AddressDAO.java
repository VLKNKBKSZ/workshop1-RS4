package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;

public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(Person person );
	public void createAddress(Address address, Person person );
	public void updateAddress(Address address,Person person );
	public void deleteAddress(Address address);
}

