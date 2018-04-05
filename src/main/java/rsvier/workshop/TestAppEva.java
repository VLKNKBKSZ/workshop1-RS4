package rsvier.workshop;
import java.util.*;
import java.util.logging.Logger;


import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.LogConnection;

public class TestAppEva {
	private static Logger logger = LogConnection.getLogger();
	
	public static void main(String[] args) {
			
			//create address object, person object and account object
			Address address1 = new Address.AddressBuilder().streetName("flup").houseNumber(32).postalCode("1236re").city("gsesrt").country("sgerg").build();
			Person person1  = new Person.PersonBuilder().personType("employee").name("adsf").lastName("poafe").middleName("deaa").address(address1).build();
			Account account1 = new Account("twwebdk", "oakemf" );
			
				
				AccountDAO accountDAO = new AccountDAOImp();
				accountDAO.createAccount(account1);
				Account account = accountDAO.getAccount(account1.getEmail(), account1.getPassword());
				
				PersonDAO personDAO = new PersonDAOImp();
				personDAO.createPerson(person1);
				
				person1 = personDAO.getPerson("poafe");
				
				AddressDAO addressDAO = new AddressDAOImp();
				addressDAO.createAddress(address1, person1);

				
		
			//	create Customer and Address in database
				
	//		personDAO.createPerson(customer1);
				
	//		System.out.println(personDAO.getAllPersons("customer"));
				
		
		
	}

}
