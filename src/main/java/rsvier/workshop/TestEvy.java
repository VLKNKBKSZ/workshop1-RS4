

package rsvier.workshop;

import java.math.BigDecimal;
import java.sql.*;
import java.util.*;
import java.util.logging.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

import rsvier.workshop.utility.*;

public class TestEvy {

	private static Logger logger = LogConnection.getLogger();

	public static void main(String[] args) {

		AccountDAO accountDao = new AccountDAOImp();
		PersonDAO personDao = new PersonDAOImp();
		AddressDAO addressDao = new AddressDAOImp();
		ProductDAO productDao = new ProductDAOImp();
		OrderDAO orderDao = new OrderDAOImp();
		OrderLineDAO orderLineDao = new OrderLineDAOImp();

		// person 1
/*
		Account account1 = new Account("evy@gmail.com", "123456");
		Address address1 = new Address.AddressBuilder().streetName("Bakemakade").houseNumber(108).postalCode("3000ME")
				.city("Rotterdam").country("Netherlands").build();

		accountDao.createAccount(account1);

		Account accountDatabse1 = accountDao.getAccount(account1.getEmail(), account1.getPassword());

		Person person1 = (Person) new Person.PersonBuilder().accountId(accountDatabse1.getAccountId()).personType("customer")
				.name("Evy").lastName("Wetch").middleName("Evyvy").build();

		personDao.createPerson(person1);
		Person person = personDao.getPersonByLastName(person1.getLastName());

		addressDao.createAddress(address1, person);
		
		*/

		// Person 2
		
		

		Account account2 = new Account("appie@gmail.com", "678910");
	//	accountDao.createAccount(account2);
		Account accountDatabse2 = accountDao.getAccount(account2.getEmail(), account2.getPassword());

		Person person2 = (Person) new Person.PersonBuilder().accountId(accountDatabse2.getAccountId()).personType("employee")
				.name("appie").lastName("pope").build();
	//	personDao.createPerson(person2);
		//Person personDatabase2 = personDao.getPersonById(person2.getAccountId());

		Address address2 = new Address.AddressBuilder().streetName("Street").houseNumber(111).postalCode("2000ME")
				.city("Rotterdam").country("Netherlands").build();
	//	addressDao.createAddress(address2,personDatabase2);
		
		
		Product product = new Product.ProductBuilder().name("stole").price(new BigDecimal("100")).stock(5).build();
		
		
	
//		productDao.createProduct(product);
		
		Product productDatabase = productDao.getProductByName("stole");
		
		
		Order order = new Order.OrderBuilder().build();
	//	orderDao.createOrder(order, personDatabase2);
		
		Order orderDatabse = orderDao.getOrderById(1);
		
		OrderLine orderLine = new OrderLine.OrderLineBuilder().order(orderDatabse).product(productDatabase).build();
		
		
	}
}

