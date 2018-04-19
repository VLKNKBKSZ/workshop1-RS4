package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;

public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(int personId );
	public void createAddress(Address address);
	public void updateAddress(Address address);
	public void deleteAddressByPersonId(int personId);
	public void deleteAdressByAddressId(Address address);

}
