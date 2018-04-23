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
		Account account1 = new Account("hippo@gmail.com", "123456");
		int generatedAccountId = accountDao.createAccount(account1);
		Account accountDatabase = accountDao.getAccountById(generatedAccountId);
		System.out.println(accountDatabase == null);
		Person person = (Person) new Person.PersonBuilder().account(accountDatabase).personType("customer")
				.name("Evy").lastName("Wetch").middleName("Evyvy").build();
		System.out.println(person == null);
		int generatedPersonId = personDao.createPerson(person);
		Person personDatabase = personDao.getPersonById(generatedPersonId);
		
		Address address = new Address.AddressBuilder().streetName("Street").houseNumber(111).postalCode("2000ME")
		           .city("Rotterdam").country("Netherlands").addressType("delivery").person(personDatabase).build();
		
		addressDao.createAddress(address);
		

		// Person 2


		//	Account account2 = new Account("appie@gmail.com", "678910");
// @Test 1 Create account = successful
		//	accountDao.createAccount(account2);
		
// @Test  Get account  = successful
		Account accountDatabse2 = accountDao.getAccount("appie@gmail.com", "letappiein");
			//System.out.println(accountDatabse2.toString());
	
		
// @Test  Get all account  = successful
		/*
				ArrayList<Account> accountList = (ArrayList) accountDao.getAllAccounts();
		

			for(int i = 0 ; i < accountList.size() ; i++){
				System.out.println(accountList.get(i).toString());
	
			}
			
		*/
	
		
// @Test Update account  =  successful
		
	//	accountDatabse2.setPassword("letappiein");
	//	accountDao.updateAccount(accountDatabse2);
		
		
// @Test Delete account  = NOT successful coz the foreign key problem		
		
	//	accountDao.deleteAccount(accountDatabse2);
		
		
	//	Person person2 = (Person) new Person.PersonBuilder().accountId(accountDatabse2.getAccountId()).personType("employee")
	//		.name("appie").lastName("pope").build();
// @Test Create person = successful
		//	personDao.createPerson(person2);
		
	
		
// @Test Get personByLastName & ById & getAddress = successful
		//Person personDatabase2 = personDao.getPersonByLastName("pope");
		
		//System.out.println(personDatabase2.toString());
		
// @Test Get all persons = successful
		
		/*	ArrayList personList = (ArrayList) personDao.getAllPersons();
		
		for(int i = 0 ; i  < personList.size(); i++){
			System.out.println(personList.get(i).toString());
		}
		
		 */
		
// @Test Update person = successful 
		
	//	Person updatePerson = new Person.PersonBuilder(personDatabase2).middleName("middle").build();
	//	personDao.updatePerson(updatePerson);
		
		
			
// @Test Delete person without delete account = not successful
		
		//	personDao.deletePerson(personDatabase2);

			Address address2 = new Address.AddressBuilder().streetName("Street").houseNumber(111).postalCode("2000ME")
					           .city("Rotterdam").country("Netherlands").build();
		
// @Test Create address = successful
			//addressDao.createAddress(address2,personDatabase2.getPersonId());
		
// @Test Delete address without delete person = successful
		
		//	addressDao.deleteAddress(personDatabase2.getPersonId());
		

// @Test Get all address = successful	
		
		//ArrayList addressList =  (ArrayList<Address>) addressDao.getAllAddresses();
		
		
		
	/*	for (int i = 0 ; i < addressList.size(); i++){
			System.out.println(addressList.get(i).toString());
		}
		
	*/	
		
// @Test update address = successful
	
		 /*
		 Address address = personDatabase2.getAddress();
		 Address updateAddress = new Address.AddressBuilder(address).streetName("J.B.Bakamakade").additionalHouseNumber(3).build();
		addressDao.updateAddress(updateAddress);
		*/
		 
		 
		 
		//	Product product1 = new Product.ProductBuilder().name("stole").price(new BigDecimal("100")).stock(5).build();
		 
// @Test Create product = successful. Give price = 95.95 --> price in DB = 96
		
		//Product product2 = new Product.ProductBuilder().name("tafel").price(new BigDecimal("95.95")).stock(100).build();
		
		//productDao.createProduct(product2);
		
// @Test Get product by name = successful	
		
		//Product productDatabase = productDao.getProductByName("tafel");
		//System.out.println(productDatabase.toString());
		
// @Test Get all product = successful
		
		/*
		ArrayList productList = (ArrayList) productDao.getAllProducts();
		
		for(int i = 0 ; i <productList.size();i++){
			System.out.println(productList.get(i).toString());
		}
		*/
		
// @Test Update product = successful	
		
		//Product productUpdate = new Product.ProductBuilder(productDatabase).stock(55).build();
		//productDao.updateProduct(productUpdate);
		
// @Test create order = successful	
		
	//	Order order = new Order.OrderBuilder().build();
	//int orderId = orderDao.createOrder(order, personDatabase2);
	//System.out.println(orderId);
	
// @Test Get order by ID = successful
		//Order orderDatabase = orderDao.getOrderById(3);
		//System.out.println(orderDatabase.toString());
		
		
// @Test Update order = NOT successful  : Can't update person_id
		
		//Order orderUpdate = new Order.OrderBuilder(orderDatabase).person(personDatabase2).build();
		//orderDao.updateOrder(orderUpdate);
		
// @Test Get all orders = successful
	/*	
		ArrayList orderList = (ArrayList) orderDao.getAllOrders();
		for(int i = 0 ; i < orderList.size() ; i++){
			System.out.println(orderList.get(i).toString());
		}
		*/

// @Test Create orderLine = successful

		//OrderLine orderLine = new OrderLine.OrderLineBuilder().order(orderDatabase).product(productDatabase).build();
		
		//orderLineDao.createOrderLine(orderLine);
		
		
// @Test Get all orderLine from product = successful : Coz change column order in orderLine table to match column order in the code
		
		/* ArrayList orderLineList = (ArrayList) orderLineDao.getAllOrderLinesFromProduct(productDatabase);
		
		for(int i = 0; i < orderLineList.size(); i++){
			System.out.println(((OrderLine) orderLineList.get(i)).toString());
		
		}
		
		*/
		
// @Test Get all orderLine from order = successful : Coz change column order in orderLine table to match column order in the code
		
	/*	ArrayList orderLineList = (ArrayList) orderLineDao.getAllOrderLinesFromOrder(orderDatabase);
		
		for(int i = 0; i < orderLineList.size(); i++){
			System.out.println(((OrderLine) orderLineList.get(i)).toString());
		
		}
	*/
		
// @Test Get  orderLine = successful
		
		//OrderLine orderLineDatabase = orderLineDao.getOrderLine(1);
		//System.out.println((orderLineDatabase.toString()));
		
// @Test Update  orderLine  = NOT successful : String query = wrong : No " SET number_of_products = ?
		
		//OrderLine orderLineUpdate = new OrderLine.OrderLineBuilder(orderLineDatabase).numberOfProducts(2).build();
		//System.out.println((orderLineUpdate.getNumber()));
		//orderLineDao.updateOrderLine(orderLineUpdate);

	}
}
