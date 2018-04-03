package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;

public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(int personId );
	public void createAddress(Address address, Person person );
	public void updateAddress(Address address,Person person );
	public void deleteAddress(int personId);
}

