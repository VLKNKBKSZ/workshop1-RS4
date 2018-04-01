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
			
		
			
			CustomerDAO cd = new CustomerDAOImp();
			AddressDAO ad = new AddressDAOImp();
			
			
//												create Customer and Address
			
//			 1. Create customer
			
//					cd.createCustomer(customer1);
			
//  		 2. Create Address
//                 2.1  Query a customer out from database to get customer_id and store it in address table
			
//						Customer c = cd.getCustomer(customer1.getLastName());
			
// 				   2.2 Create Address
			
//						ad.createAddress(address1, c);
			
			
			
			
//												Get a customer (U will get address too)
			
//				Customer c = cd.getCustomer("Wetch");
			
//				System.out.println(c.toString());
			
			
			

			
//													Update a Customer
			
			
//           New Data that you want to update
			
//					String newMiddleName = "evywetch";  
			
// 		1. Find a customer that you want to update from a database by calling getCuatomer()
			
//					Customer customer = cd.getCustomer("Wetch");

/*		2. Create a new CustomerBuilder that will take a Customer that you just got from a database.
 * 		   Then this builder will copy all data from a customer to itself. Then use this builder to set a new data.
 * 		   While other data is still the same.
*/	
//					Customer.CustomerBuilder newBuilder = new Customer.CustomerBuilder(customer);
//					newBuilder.middleName(newMiddleName);
					
/*		3. Build the newBuilder to a new Customer object.
 *   	   Then this object will contain the same info as the customer that we got from the database except the middle name.
*/	
//					Customer updatedCustomer = newBuilder.build();
					
//		4. Update a customer by passing the updatedCustomer object to updateCustomer()
					
//					cd.updateCustomer(updatedCustomer);
			
			
			
			
				
						
// 													Update address
			
	
		//* New input
			  
					String newStreet = " J.B.Bakemakade";
					String newPostalCode = "3071ME";
			
		//* Get customer fist 
			  
					Customer customer = cd.getCustomer("Wetch");
			
		//* And use customer to find address
			  
					Address address = ad.getAddress(customer);
			
		//* Copy AddressBuiler of the found address  to the new update AddressBuilder
			  
					Address.AddressBuilder ab = new Address.AddressBuilder(address);
			
		//*Use update AddressBuilder to set new input
			  
					Address update = ab.streetName(newStreet).postalCode(newPostalCode).build();
		
		//* update Address using update address
	  
			 		ad.updateAddress(update, customer);
			 
			 
			 
			
			
			
/*										Find all Customers (You will get Address too)
 
			
				List<Customer> customerList = cd.getAllCustomers();
			
				for(int i = 0; i < customerList.size() ; i++){
				
				System.out.println(customerList.get(i).toString());			
				
			}
					
					
*/			
			
		/*	
			 
//			 							    Delete Customer&Address
		
		// Give the lastname of customer that want to delete
			String lastName = "wetch";
			
			// Find customer using lastName 
			
			Customer customer = cd.getCustomer(lastName);
			
			
			// Delete customer first
			cd.deleteCustomer(customer);
			
			// Then delete the address
			ad.deleteAddress(customer.getAddress());
			 
			 */
	}
	
	

}
