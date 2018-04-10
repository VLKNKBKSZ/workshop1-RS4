package rsvier.workshop;

import java.math.BigDecimal;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class EvaTestsMore {

	public static void main(String[] args) {
		
		Account account1 = new Account("eva@eva", "wacht");
		AccountDAO accountDAO = new AccountDAOImp();
		
		
		//createAccount() geeft een accountId als int terug,dus deze gebruiken als argument voor createPerson:
		
		Person person1 = new Person.PersonBuilder().accountId(accountDAO.createAccount(account1)).name("Eveline").lastName("Noenij").personType("superperson").build();
		PersonDAO personDAO = new PersonDAOImp();
			System.out.println(person1.toString());
		
		//createPerson geeft een personId als int terug, dus deze gebruiken als argument voor createAddress
		
		Address address1 = new Address.AddressBuilder().personId(personDAO.createPerson(person1)).streetName("Straatje").houseNumber(12).postalCode("1231rt").city("supercity").country("CrazyLand").build();
		AddressDAO addressDAO = new AddressDAOImp();
		addressDAO.createAddress(address1, personDAO.createPerson(person1)); 
			System.out.println(address1.toString());
		
		Product product = new Product.ProductBuilder().name("Stoelie").price(new BigDecimal("95.00")).stock(20).build();
		ProductDAO productDAO = new ProductDAOImp();
		productDAO.createProduct(product);
		
		Order order = new Order.OrderBuilder().person(person1).build();
		OrderDAO orderDAO = new OrderDAOImp();
		orderDAO.createOrder(order, person1); //geeft order_table_id terug, dus int orderId
		
		OrderLine orderLine = new OrderLine.OrderLineBuilder().order(order).product(product).numberOfProducts(3).build();
		OrderLineDAO orderLineDAO = new OrderLineDAOImp();
		orderLineDAO.createOrderLine(orderLine);
		
		
	}

}
