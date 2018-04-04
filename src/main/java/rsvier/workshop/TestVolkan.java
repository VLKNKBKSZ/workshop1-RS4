package rsvier.workshop;

import java.util.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.SQLException;

import rsvier.workshop.dao.OrderDAOImp;
import rsvier.workshop.dao.OrderlineDAOImp;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Order;
import rsvier.workshop.domain.OrderLine;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Product;
import rsvier.workshop.domain.Product.ProductBuilder;
import rsvier.workshop.utility.DatabaseConnectionXML;

public class TestVolkan {

	public static void main(String[] args) {
		

		//ProductDAOImp productDAO = new ProductDAOImp();
		//OrderlineDAOImp orderLineDAO = new OrderlineDAOImp();
		//OrderDAOImp orderDAO = new OrderDAOImp();
		//OrderLine orderLine = orderLineDAO.getOrderLine(13);
		//OrderLine.OrderLineBuilder olBuilder = new OrderLine.OrderLineBuilder(orderLine);
		//OrderLine newOrderLine = olBuilder.number(33).build();
		//orderLineDAO.updateOrderLine(newOrderLine);
		
		//Order order = orderDAO.getOrderById(2);		//Product product = productDAO.getProductByName("Tafel");
		//List<OrderLine> list = orderLineDAO.getAllOrderLinesFromProduct(product);
		//for(OrderLine item : list) {
		//	System.out.println(item.toString());
		//}
		//Order order = new Order.OrderBuilder().build();
		//orderDAO.createOrder(order);
		//Order order = orderDAO.getOrderById(4);
		//Product product = productDAO.getProductById(9);
		//OrderLine orderLine = new OrderLine.OrderLineBuilder().order(order).product(product).number(77).build();
		//orderLineDAO.createOrderLine(orderLine);
		//Product product = productDAO.getProductByName("Tafel");
		//Order order = orderDAO.getOrderById(1);
		//orderDAO.deleteOrder(order);
		
		//OrderLine a = new OrderLine.OrderLineBuilder().product(product).number(23).build();
		//orderLineDAO.createOrderLine(a);
		//Product a = new Product.ProductBuilder().name("Stoel").stock(77).price(new BigDecimal("777")).build();
		//productDAO.createProduct(a);
		//List<Product> list = productDAO.getAllProducts();
		//for(Product item: list) {
		//	System.out.println(item.toString());
		//}
		//Product a = productDAO.getProductById(6);
		//Product.ProductBuilder newProduct = new Product.ProductBuilder(a);
		//Product b = newProduct.name("Stoel").build();
	
		//productDAO.updateProduct(b);
		//List<OrderLine> orderLine = orderLineDAO.getAllOrderLinesFromProduct(product);
		//for(OrderLine item : orderLine) {
		//	System.out.println(item.toString());
		//}
		//OrderLine.OrderLineBuilder olBuilder = new OrderLine.OrderLineBuilder();
		//OrderLine newOrderLine = olBuilder.orderLineId(orderLine.getOrderLineId()).number(555).build();
		//orderLineDAO.updateOrderLine(newOrderLine);
	
		//orderLineDAO.deleteOrderLine(orderLine);
		//OrderLine order = orderLineDAO.getOrderLine(1);
		//System.out.println(order.toString());
		//List<OrderLine> list = orderLineDAO.getAllOrderLinesFromProduct(2);
		 //System.out.println(list.get(0).toString());
		//for (OrderLine item : list) {
		//	System.out.println(item.toString());
		//}

		// OrderlineDAOImp orderLineDAO = new OrderlineDAOImp();
		// ProductDAOImp productDAO = new ProductDAOImp();
		// Product a = productDAO.getProductByName("Lamp");
		// OrderLine b = new OrderLine.OrderLineBuilder().number(99).product(a).build();
		// orderLineDAO.createOrderLine(b);
		// System.out.println(b);
		// Product a = new Product.ProductBuilder().name("Stoel").price(new
		// BigDecimal("2231.22")).stock(12).build();
		// productDAO.createProduct(a);
		// System.out.println(b.toString());
		// Product c = pd.getProductById(6);
		// System.out.println(c.toString());
		// Product c = pd.getProductById(6);
		// pd.deleteProduct(c);

		/*
		 * @Test1.1: Get all products from the database and traverse true them =
		 * succesfull List<Product> list = pd.getAllProducts(); for(int i = 0; i <
		 * list.size(); i++) { System.out.println(list.get(i).toString()); }
		 * 
		 * for(Product i : list) { System.out.println(i.toString()); }
		 * 
		 * @Test1: create 3 products = succesfull Product a = new
		 * Product.ProductBuilder().name("Hoekbank Volkan").price(new
		 * BigDecimal("745.55")).stock(3).build(); Product b = new
		 * Product.ProductBuilder().name("Eettafel Eva").price(new
		 * BigDecimal("450.34")).stock(2).build(); Product c = new
		 * Product.ProductBuilder().name("Lamp Evy").price(new
		 * BigDecimal("123.44")).stock(23).build();
		 * 
		 * @Test2: add created products to Database = succesfull
		 * abc.createProduct(a/b/c)
		 * 
		 * @Test3: get productByName / getProductById = succesfull Product bb =
		 * abc.getProductByName("Hoekbank Volkan"); System.out.println(bb.toString());
		 * 
		 * @Test4: delete product by passing in Product Object. Product a1 =
		 * abc.getProductByname("Hoekbank Volkan") // Its being deleted based on unique
		 * id. abc.deleteProduct(a1);
		 * 
		 * @Test5: update of product by passing in product = succesfull 1:
		 * getProductByName or Id Product bb = abc.getProductByName("Lamp"); 2: Create
		 * new product with Product.ProductBuilder updateProduct = new
		 * Product.ProductBuilder(bb); 3: use new variable to change product specs .
		 * updateProduct.name().price().stock(); 4: Build that product and assign it to
		 * a variable. Product updatedProduct = updateProduct.build(); 5:
		 * abc.updateProduct(updatedProduct); This is going to be implemented in the
		 * view or controller i guess.
		 */

	}
}
