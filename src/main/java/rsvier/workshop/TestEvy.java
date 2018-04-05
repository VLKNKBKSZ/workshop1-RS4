package rsvier.workshop;

import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

import rsvier.workshop.utility.*;


public class TestEvy {
	
	private static Logger logger = LogConnection.getLogger();

	public static void main(String[] args) {
		
		
			Account account1 = new Account("evy@gmail.com", "123456");
			Address address1 = new Address.AddressBuilder().streetName("Bakemakade").houseNumber(108).postalCode("3000ME").city("Rotterdam").country("Netherlands").build();
			

			Person person1  =  (Person) new Person.PersonBuilder().name("Evy").lastName("Wetch").middleName("Evyvy").address(address1).build();

			
		
			AccountDAO accountDao = new AccountDAOImp();
			PersonDAO personDao = new PersonDAOImp();
			AddressDAO addressDao = new AddressDAOImp();
			
			
	//		accountDao.createAccount(account1);
			
		
			Account account = accountDao.getAccount(account1.getEmail(), account1.getPassword());
			
			System.out.println(account.getAccountId());
			
		//	personDao.createPerson(person1, account1);
			
	}
}
