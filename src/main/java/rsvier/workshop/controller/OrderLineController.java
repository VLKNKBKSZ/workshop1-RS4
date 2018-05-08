
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

			default:
				orderLineView.printMenuInputIsWrong();
			}
		}

	}

	// Save order, order lines and update product stocks in database
	public void saveOrderAndOrderLinesInDatabase(Order order) {

		order.setOrderDateTime(LocalDateTime.now());
		order.setTotalPrice(getTotalPriceOfOrder(order));
		// Create order
		int orderId = orderDAO.createOrder(order);

		// Create orderLines
		orderLineDAO.createOrderLine(order.getTotalOrderLines(), orderId);

		// Update Products
		updateProductInDatabase(order.getTotalOrderLines());

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

				// Create an orderline object with the product and the amount of products chosen
				OrderLine orderLine = new OrderLine.OrderLineBuilder().product(retrievedProduct)
						.numberOfProducts(requestedProduct).build();

				// Print order line details
				System.out.println(orderLine.toString() + "\n");

				// Add the created orderline to the order
				order.getTotalOrderLines().add(orderLine);

			} else {
				orderLineView.printProductStockIsNotAvailable(retrievedProduct.getStock(), requestedProduct);
			}
		} else {
			productView.printProductNotFound();
			System.out.println(" ");
			runView();
		}
	}

	// Method for asking how many copies of the product
	public int requestAmountOfProducts() {

		orderLineView.printRequestAmountOfProducts();
		return orderLineView.getIntInput();
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

		System.out.println("\nTotale prijs van de bestelling: € " + totalPrice + "\n3");
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

	public boolean checkProductStock(int requestedAmpountOfProduct, Product product) {

		if (requestedAmpountOfProduct <= product.getStock()) {
			return true;
		}
		return false;
	}

	// Update product stock
	public void updateProductInDatabase(List<OrderLine> orderLineList) {

		// Declare an ArrayList to hold product that has been updated product stock
		List<Product> productList = new ArrayList<>();

		for (OrderLine orderLine : orderLineList) {

			Product product = orderLine.getProduct();
			// deduct numberOfProducts that customer ordered from product stock
			product.setStock((product.getStock() - orderLine.getNumberOfProducts()));
			productList.add(product);
		}
		productDAO.updateProduct(productList);

	}
}
