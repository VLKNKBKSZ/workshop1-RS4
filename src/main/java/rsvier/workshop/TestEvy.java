package rsvier.workshop;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.domain.Customer.CustomerBuilder;
import rsvier.workshop.utility.*;


public class TestEvy {
	
	private static Logger logger = LogConnection.getLogger();

	public static void main(String[] args) {
		
		
			
			Address address1 = new Address.AddressBuilder().streetName("Bakemakade").houseNumber(108).postalCode("3000ME").city("Rotterdam").country("Netherlands").build();
			
			Customer customer1  =  (Customer) new Customer.CustomerBuilder().name("Evy").lastName("Wetch").middleName("Evyvy").email("evy@gmail.com").address(address1).build();
			
			Address address2 = new Address.AddressBuilder().streetName("Street").houseNumber(111).postalCode("2000GT").city("Utrect").country("Netherlands").build();
			
			Customer customer2  =  (Customer) new Customer.CustomerBuilder().name("Arno").lastName("Massen").middleName("Cornellis").email("arno@gmail.com").address(address2).build();
			
			
			CustomerDAO cd = new CustomerDAOImp();
			AddressDAO adi = new AddressDAOImp();
			
			
													// Create customers
			
				cd.createCustomer(customer1);
				//cd.createCustomer(customer2);
			
													// get customers 
				
			//	Customer c1 = cd.getCustomer(customer1.getLastName());
				//Customer c2 = cd.getCustomer(customer2.getLastName());
			
			
			
												// create Addresses
				
			 adi.createAddress(address1, customer1); 
			//adi.createAddress(address2, c2); 
			
			
												// find all Addresses
			
		/*	List<Address> addressList = adi.getAllAddresses();
			
			for (int i = 0 ; i < addressList.size() ; i++){
				System.out.println(addressList.get(i).toString());
			}
			
			*/
			
			
												// get address
			
			Address address = adi.getAddress(customer1);
			System.out.println(address.toString());
												
			
			
			
			
											// Update address
			
	/*		
		//* New input
			  
			String newStreet = " J.B.Bakemakade";
			String newPostalCode = "3071ME";
			
		//* Get customer fist 
			  
			Customer customer = cd.getCustomer("Wetch");
			
		//* And use customer to find address
			  
			Address address = adi.getAddress(customer);
			
		//* Copy AddressBuiler of the found address  to the new update AddressBuilder
			  
			Address.AddressBuilder ab = new Address.AddressBuilder(address);
			
		//*Use update AddressBuilder to set new input
			  
			Address update = ab.streetName(newStreet).postalCode(newPostalCode).build();
		
		//* update Address using update address
	  
			 adi.updateAddress(update, customer);
			 
			 
			 */
			 
			 							// Delete address
		
		// Give the lastname of customer that want to delete
			String lastName = "wetch";
			
			// Find customer using lastName
			
			Customer customer = cd.getCustomer(lastName);
			
			
			// Delete customer first
			cd.deleteCustomer(customer);
			
			// Then delete the address
			adi.deleteAddress(customer);
			 
			 
	}
	
	

}
