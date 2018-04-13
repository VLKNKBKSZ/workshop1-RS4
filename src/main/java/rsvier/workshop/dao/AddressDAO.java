package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;


public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(int personId );
<<<<<<< HEAD
	public void createAddress(Address address);
=======
	public void createAddress(Address address, int personId );
>>>>>>> refs/remotes/origin/master
	public void updateAddress(Address address);
	public void deleteAddressByPersonId(int personId);
	public void deleteAdressByAddressId(Address address);

}

