package rsvier.workshop;

import java.math.BigDecimal;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.dao.AddressDAOImp;
import rsvier.workshop.dao.OrderDAOImp;
import rsvier.workshop.dao.OrderLineDAOImp;
import rsvier.workshop.dao.PersonDAOImp;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.*;

public class TestVolkan {

	public static void main(String[] args) {

		AccountDAOImp accountDAO = new AccountDAOImp();
		PersonDAOImp personDAO = new PersonDAOImp();
		ProductDAOImp productDAO = new ProductDAOImp();
		OrderDAOImp orderDAO = new OrderDAOImp();
		OrderLineDAOImp orderLineDAO = new OrderLineDAOImp();
		AddressDAOImp addressDAO = new AddressDAOImp();

		Account account = new Account("hans@gmail.com", "rsvier");
		int generatedId = accountDAO.createAccount(account);
		Person person = new Person.PersonBuilder().accountId(generatedId).name("hans").lastName("jansen")
				.personType("employee").build();
		int generatedIdPerson = personDAO.createPerson(person);
		Address address = new Address.AddressBuilder().addressType("deliver").streetName("Jangooskaai").houseNumber(12)
				.postalCode("1602GC").city("Amsterdam").country("Nederland").personId(generatedIdPerson).build();
		addressDAO.createAddress(address);

		Product product = new Product.ProductBuilder().name("Stoel").price(new BigDecimal("245.44")).stock(55).build();
		productDAO.createProduct(product);
		OrderLine orderLine1 = new OrderLine.OrderLineBuilder().product(productDAO.getProductByName("Stoel"))
				.numberOfProducts(22).build();

		BigDecimal numOfProducts = new BigDecimal(orderLine1.getNumber());
		BigDecimal priceOfProduct = orderLine1.getProduct().getPrice();
		BigDecimal sum = numOfProducts.multiply(priceOfProduct);

		Order order = new Order.OrderBuilder().person(personDAO.getPersonByAccountId(generatedIdPerson)).totalPrice(sum)
				.build();
		int generatedIdOfOrder = orderDAO.createOrder(order, personDAO.getPersonById(generatedId));

	}

}
