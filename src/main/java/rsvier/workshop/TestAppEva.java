
package rsvier.workshop;
import java.math.BigDecimal;
import java.util.*;
import java.util.logging.Logger;


import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.LogConnection;

public class TestAppEva {
	private static Logger logger = LogConnection.getLogger();
	
	public static void main(String[] args) {
			
			/*create an account object with email and password, leaving the accountId field empty for now
			Account account1 = new Account("lwjef", "askjf" );
			
			// create an accountDAO object
			AccountDAO accountDAO = new AccountDAOImp();
			
			//create an account in the database, using the information from the account object
			accountDAO.createAccount(account1);
			
			//create a new account object that holds also the account_id
			Account account2 = accountDAO.getAccount(account1.getEmail(), account1.getPassword());
			
			//create an adres object 
			Address address1 = new Address.AddressBuilder().streetName("kdsfh").houseNumber(245).postalCode("1246re").city("bbbb").country("bbb").build();
			
			//create a person object
			Person person  = new Person.PersonBuilder().accountId(account2.getAccountId()).personType("employee").name("adsf").lastName("poafe").middleName("deaa").address(address1).build();
			
			//create a person in the database
			PersonDAO personDAO = new PersonDAOImp();
			personDAO.createPerson(person);
			

			person = personDAO.getPersonByAccountId(account2.getAccountId());

			person = personDAO.getPersonById(account2.getAccountId());
			// deze mag ook: person = personDAO.getPersonByLastName("poafe");
			
			//create an address in the database
			AddressDAO addressDAO = new AddressDAOImp();
		// need to change person to personId: 	addressDAO.createAddress(address1, person);
			
			//create a product object with the name, price and stock
			Product product = new Product.ProductBuilder().name("Kroonluchter").price(new BigDecimal("34.45")).stock(12).build();
		
			//create a product in the database with the product object
			ProductDAO productDAO = new ProductDAOImp();
			productDAO.createProduct(product);
			
			//create an order object
			Order order = new Order.OrderBuilder().build();
			
			//create an order in the database
			OrderDAO orderDAO = new OrderDAOImp();
			orderDAO.createOrder(order, person);
			
			Product product2 = productDAO.getProductByName("kroonluchter");
			
			int newId = orderDAO.createOrder(order,person);
			Order order2 = orderDAO.getOrderById(newId);
			
			OrderLine orderLine = new OrderLine.OrderLineBuilder().order(order2).product(product2).numberOfProducts(3).build();
			OrderLineDAO orderLineDAO = new OrderLineDAOImp();
			
			orderLineDAO.createOrderLine(orderLine); */
	}

}
