package rsvier.workshop;
import java.util.*;
import java.util.logging.Logger;


import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.LogConnection;

public class TestAppEva {
	private static Logger logger = LogConnection.getLogger();
	
	public static void main(String[] args) {
			
			//create an account object with email and password, leaving the accountId field empty for now
			Account account1 = new Account("twwebdk", "oakemf" );
			
			// create an accountDAO object
			AccountDAO accountDAO = new AccountDAOImp();
			
			//create an account in the database, using the information from the account object
			accountDAO.createAccount(account1);
			
			
			Account account2 = accountDAO.getAccount(account1.getEmail(), account1.getPassword());
			
			Address address = new Address.AddressBuilder().streetName("flup").houseNumber(32).postalCode("1236re").city("gsesrt").country("sgerg").build();
			
			PersonDAO personDAO = new PersonDAOImp();
			Person person  = new Person.PersonBuilder().accountId(account2.getAccountId()).personType("employee").name("adsf").lastName("poafe").middleName("deaa").address(address).build();
			personDAO.createPerson(person);
			
				
			AddressDAO addressDAO = new AddressDAOImp();
			addressDAO.createAddress(address, person);

			
		
			//	create Customer and Address in database
				
	//		personDAO.createPerson(customer1);
				
	//		System.out.println(personDAO.getAllPersons("customer"));
				
		
		
	}

}
