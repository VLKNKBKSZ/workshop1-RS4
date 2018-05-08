
package rsvier.workshop.controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderLineController extends Controller {
	private OrderLineView orderLineView = new OrderLineView();
	private ProductDAO productDAO = new ProductDAOImp();
	private ProductView productView = new ProductView();
	private OrderView orderView = new OrderView();
	private OrderLine orderLine;
	private OrderLineDAO orderLineDAO = new OrderLineDAOImp();
	private Order order;
	private OrderDAO orderDAO = new OrderDAOImp();

	@Override
	public void runView() {
		orderLineView.printHeaderMessage();
		// orderLineView.printMenuMessage();
		// orderLineMenuSwitch(orderLineView.getIntInput());
	}

	public void orderLineMenuSwitch(Order order) {

		// As long as placingOrder is true, the orderlines are being created and added
		// to the order
		boolean placingOrder = true;

		while (placingOrder) {

			orderLineView.printMenuMessage();
			int menuChoice = orderLineView.getIntInput();

			switch (menuChoice) {

			case 1: // Add orderline(s) (i.e. product and amount of products) to order object
				addOrderLineToOrder(order);

				break;

			case 2: // View current order
				viewAllOrderlinesInCurrentOrder(order);
				showTotalPriceOfCurrentOrder(getTotalPriceOfOrder(order));
				break;

			case 3: // Place order. Method to place the order in the database
				saveOrderAndOrderLinesInDatabase(order);
				orderView.printOrderHasBeenPlaced();
				placingOrder = false;
				MainController.setController(TypeOfController.ORDER);
				break;

			case 4: // Cancel order
				placingOrder = false;
				MainController.setController(TypeOfController.ORDER);
				break;
			}
		}

	}

	public void saveOrderAndOrderLinesInDatabase(Order order) {
		
		
		order.setOrderDate(LocalDate.now());
		order.setTotalPrice(getTotalPriceOfOrder(order));
		int orderId = orderDAO.createOrder(order);
		orderLineDAO.createOrderLine(order.getTotalOrderLines(), orderId);
		
	}
	
	//
	public void addOrderLineToOrder(Order order) {

		// First ask the user for the product he wants to see/order
		orderLineView.printRequestNameOfProductToView();
		String productName = orderLineView.getStringInput();

		// Get the product from the database and store it in a product object
		Product retrievedProduct = productDAO.getProductByName(productName);

		if (retrievedProduct != null) {
			System.out.println("\n" + retrievedProduct.toString() + "\n");

			// Create an orderline object with the product and the amount of products chosen
			OrderLine orderLine = new OrderLine.OrderLineBuilder().product(retrievedProduct)
					.numberOfProducts(requestAmountOfProducts()).build();

			System.out.println(orderLine.toString());

			// Add the created orderline to the order
			// GETTING A NULL POINTER HERE NOW
			order.getTotalOrderLines().add(orderLine);

		}

		else {
			productView.printProductNotFound();
			System.out.println(" ");
			runView();
		}
	}

	// Method for asking how many copies of the product
	public int requestAmountOfProducts() {

		orderLineView.printRequestAmountOfProducts();
		int amountOfProducts = orderLineView.getIntInput();

		return amountOfProducts;
	}

	// Method for viewing all the orderlines in the current order
	public void viewAllOrderlinesInCurrentOrder(Order order) {

		if (order.getTotalOrderLines().isEmpty()) {
			orderLineView.printOrderIsEmpty();
			runView();
		}

		for (OrderLine orderLine : order.getTotalOrderLines()) {
			System.out.println("\n" + orderLine.toString());
		}
	}

	// Method for showing total price
	public void showTotalPriceOfCurrentOrder(BigDecimal totalPrice) {

		System.out.println("\nTotale prijs van de bestelling: € " + totalPrice);
	}
	
	public BigDecimal getTotalPriceOfOrder(Order order) {
		
		BigDecimal totalPriceOfOrder = new BigDecimal(0);

		for (OrderLine orderLine : order.getTotalOrderLines()) {
			
			BigDecimal totalPriceOfOrderLine = new BigDecimal(0);
			BigDecimal numberOfProductsInBigDecimal = (BigDecimal.valueOf(orderLine.getNumberOfProducts()));
			totalPriceOfOrderLine = (totalPriceOfOrderLine.add((orderLine.getProduct().getPrice()))
					.multiply(numberOfProductsInBigDecimal));
			totalPriceOfOrder = totalPriceOfOrder.add(totalPriceOfOrderLine);
		}
		
		return totalPriceOfOrder;
	}


}

