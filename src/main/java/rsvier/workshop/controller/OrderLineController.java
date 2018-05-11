
package rsvier.workshop.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;

import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderLineController extends Controller {
	private OrderLineView orderLineView = new OrderLineView();
	private ProductDAO productDAO = new ProductDAOImp();
	private ProductView productView = new ProductView();
	private OrderView orderView = new OrderView();
	private OrderLineDAO orderLineDAO = new OrderLineDAOImp();
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
				viewAllOrderLinesInCurrentOrder(order);
				showTotalPriceOfCurrentOrder(getTotalPriceOfOrder(order));
				break;

			case 3: // Place order. Method to place the order in the database
				saveOrderAndOrderLinesInDatabase(order);
				orderView.printOrderHasBeenPlaced();
				placingOrder = false;
				MainController.setController(TypeOfController.ORDER);
				break;

			case 4: // Cancel order
				cancelAllOrderLines(order);
				placingOrder = false;
				MainController.setController(TypeOfController.ORDER);
				break;

			default:
				orderLineView.printMenuInputIsWrong();
			}
		}

	}

	public void cancelAllOrderLines(Order order) {
		List<OrderLine> orderLines = order.getTotalOrderLines();

		for (OrderLine orderLine : orderLines) {
			Product product = productDAO.getProductByName(orderLine.getProduct().getName());

			product.setStock(product.getStock() + orderLine.getNumberOfProducts());
			productDAO.updateProduct(product);
		}
	}

	// Save order and order lines
	public void saveOrderAndOrderLinesInDatabase(Order order) {

		order.setOrderDateTime(LocalDateTime.now());
		order.setTotalPrice(getTotalPriceOfOrder(order));
		// Create order
		int orderId = orderDAO.createOrder(order);

		// Create orderLines
		orderLineDAO.createOrderLine(order.getTotalOrderLines(), orderId);

	}

	// Add order lines into order
	public void addOrderLineToOrder(Order order) {

		// First ask the user for the product he wants to see/order
		orderLineView.printRequestNameOfProductToView();
		String productName = orderLineView.getStringInput();

		// Get the product from the database and store it in a product object
		Product retrievedProduct = productDAO.getProductByName(productName);

		if (retrievedProduct != null) {

			// Print product details
			System.out.println("\n" + retrievedProduct.toString() + "\n");

			// Ask how many products customer wants to order
			int requestedProduct = requestAmountOfProducts();

			// Check if the product stock is available
			if (checkProductStock(requestedProduct, retrievedProduct)) {

				// Check orderLines if product is already in the order
				if (checkForDuplicateProductInOrderLines(order, requestedProduct, retrievedProduct)) {
					return;
				}

				// Create an orderLine object with the product and the amount of products chosen
				OrderLine orderLine = new OrderLine.OrderLineBuilder().product(retrievedProduct)
						.numberOfProducts(requestedProduct).build();

				// Print order line details
				System.out.println(orderLine.toString() + "\n");

				// Add the created orderLine to the order
				order.getTotalOrderLines().add(orderLine);

				// Update stock in database
				updateProductInDatabase(orderLine);

			} else {
				orderLineView.printProductStockIsNotAvailable(retrievedProduct.getStock(), requestedProduct);
			}
		} else {
			productView.printProductNotFound();
			System.out.println(" ");
			runView();
		}
	}

	public boolean checkForDuplicateProductInOrderLines(Order order, int requestProduct, Product retrievedProduct) {
		List<OrderLine> orderLines = order.getTotalOrderLines();

		for (OrderLine orderLine : orderLines) {

			if (retrievedProduct.getName().equalsIgnoreCase(orderLine.getProduct().getName())) {
				int previousNumberOfProducts = orderLine.getNumberOfProducts();

				// set numberOfProducts to the new number and reduct this from database
				orderLine.setNumberOfProducts(requestProduct);
				updateProductInDatabase(orderLine);

				// add both product numbers together in same orderline
				orderLine.setNumberOfProducts(orderLine.getNumberOfProducts() + previousNumberOfProducts);

				return true;
			}
		}
		return false;
	}

	// Method for asking how many copies of the product
	public int requestAmountOfProducts() {

		orderLineView.printRequestAmountOfProducts();
		return orderLineView.getIntInput();
	}

	// Method for viewing all the orderLines in the current order
	public void viewAllOrderLinesInCurrentOrder(Order order) {

		if (order.getTotalOrderLines().isEmpty()) {
			orderLineView.printOrderIsEmpty();
			runView();
		}

		for (int i = 0; i < order.getTotalOrderLines().size(); i++) {
			System.out.println("Bestelregel nummer" + (i+1) +order.getTotalOrderLines().get(i).toString());
			
		}

	}

	public void editOrDeleteOrderLineSwitchMenu(Order order) {

		orderLineView.printEditOrDeleteOrderLine();
		int menuNumber = orderLineView.getIntInput();

		switch (menuNumber) {

		case 1:
			viewAndSelectOrderLine(order);
			
			break;
			
		case 2:
			
			//deleteOrderLineFromOrder();
			break;

		default:
		}
	}

	public OrderLine viewAndSelectOrderLine(Order order) {
		
		viewAllOrderLinesInCurrentOrder(order);
		System.out.println(order.toString());
		
		List<OrderLine> orderLineList = order.getTotalOrderLines();
		
		if(orderLineList.size() == 1) {
			
			return orderLineList.get(0);
			
		}else {
			
			orderLineView.printAskUserToChoseOrderLine();
			int choice = orderLineView.getIntInput();
			
			while(choice < 1 | choice > orderLineList.size()) {
				
				orderLineView.printMenuInputIsWrong();
				choice = orderLineView.getIntInput();
			}
			return orderLineList.get(choice -1);
		}
		
	}

	// Method for showing total price
	public void showTotalPriceOfCurrentOrder(BigDecimal totalPrice) {

		System.out.println("\nTotale prijs van de bestelling: € " + totalPrice + "\n");
	}

	// Method of getting total price of an order
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

	// Method to check product stock

	public boolean checkProductStock(int requestedAmountOfProduct, Product product) {

		if (requestedAmountOfProduct <= product.getStock()) {
			return true;
		}

		return false;
	}

	// Update product stock
	public void updateProductInDatabase(OrderLine orderLine) {

		Product product = orderLine.getProduct();

		// deduct numberOfProducts that customer ordered from product stock
		product.setStock((product.getStock() - orderLine.getNumberOfProducts()));

		productDAO.updateProduct(product);
	}
}
