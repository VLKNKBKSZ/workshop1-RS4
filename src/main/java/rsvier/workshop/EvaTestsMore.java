package rsvier.workshop;

<<<<<<< HEAD
import java.math.BigDecimal;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class EvaTestsMore {

	public static void main(String[] args) {
		
		Account account1 = new Account("eva@eva", "wacht");
		AccountDAO accountDAO = new AccountDAOImp();
		int accountIdeetje = accountDAO.createAccount(account1);
		
		
		Person person1 = new Person.PersonBuilder().accountId(accountIdeetje).name("Eveline").lastName("Noenij").personType("superperson").build();
		PersonDAO personDAO = new PersonDAOImp();
		int personIdeetje = personDAO.createPerson(person1);
		
		Person person2 = new Person.PersonBuilder().accountId(accountIdeetje).personId(personIdeetje).build();
		
		Address address1 = new Address.AddressBuilder().personId(personIdeetje).streetName("Straatje").houseNumber(12).postalCode("1231rt").city("supercity").country("CrazyLand").build();
		AddressDAO addressDAO = new AddressDAOImp();
		addressDAO.createAddress(address1); 
			
		
		Product product = new Product.ProductBuilder().name("Stoelie").price(new BigDecimal("95.00")).stock(20).build();
		ProductDAO productDAO = new ProductDAOImp();
		productDAO.createProduct(product);
		//int productIdeetje = (productDAO.getProductByName(product.getName())).getProductId();
		product = productDAO.getProductByName(product.getName());
		
		Order order = new Order.OrderBuilder().person(person2).build();
		OrderDAO orderDAO = new OrderDAOImp();
		int orderIdeetje = orderDAO.createOrder(order, person2); //geeft order_table_id terug, dus int orderId
		
		//order2 is order maar dan met int orderId
		Order order2 = new Order.OrderBuilder().person(person2).orderId(orderIdeetje).build();
		
		OrderLine orderLine = new OrderLine.OrderLineBuilder().order(order2).product(product).numberOfProducts(3).build();
		OrderLineDAO orderLineDAO = new OrderLineDAOImp();
		orderLineDAO.createOrderLine(orderLine);
		
		
=======
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class EvaTestsMore {

	public static void main(String[] args) {
		
		Account account1 = new Account("eva@eva", "wacht");
		
		AccountDAO accountDAO1 = new AccountDAOImp();
		accountDAO1.createAccount(account1);
		
	

>>>>>>> refs/remotes/origin/master
	}

}
