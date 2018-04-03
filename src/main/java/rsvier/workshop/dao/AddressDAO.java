package rsvier.workshop.dao;

import java.util.*;
import rsvier.workshop.domain.*;
<<<<<<< HEAD


=======
>>>>>>> 828d0ffa2df74cd84fc620bcbc5bcd424242c940

public interface AddressDAO {

	public List<Address> getAllAddresses();
	public Address getAddress(int personId );
<<<<<<< HEAD
	public void createAddress(Address address, Person person);
	public void updateAddress(Address address,Person person);
	public void deleteAddress(Person person);
=======
	public void createAddress(Address address, Person person );
	public void updateAddress(Address address,Person person );
	public void deleteAddress(int personId);
>>>>>>> 828d0ffa2df74cd84fc620bcbc5bcd424242c940
}

