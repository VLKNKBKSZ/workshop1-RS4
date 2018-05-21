
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
	
	private ProductView productView = new ProductView();
	private OrderView orderView = new OrderView();
	private ProductController productController = new ProductController();

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

			case 1: // Add orderline(s) to order object
				addOrderLineToOrder(order);
				break;

			case 2: // View current order
				viewAllOrderLinesInCurrentOrder(order);
				showTotalPriceOfCurrentOrder(getTotalPriceOfOrder(order));
				break;

			case 3: // Place order in the database
				placeOrderInDatabase(order);
				placingOrder = false;
				MainController.setController(TypeOfController.EMPLOYEE);
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

	public void placeOrderInDatabase(Order order) {
		if(order == null) {
			orderLineView.printOrderIsEmpty();
			return;
		}

		//Ask confirmation before saving in database
		if (orderLineView.confirmYesOrNo().equalsIgnoreCase("J")) {
			saveOrderAndOrderLinesInDatabase(order);
			orderView.printOrderHasBeenPlaced();
		} else {
			orderView.printOrderHasNotBeenPlaced();
		}
	}

	
	
	public void cancelAllOrderLines(Order order) {
		List<OrderLine> orderLines = order.getTotalOrderLines();

		for (OrderLine orderLine : orderLines) {
			Product product = DAOFactory.getProductDAO().getProductByName(orderLine.getProduct().getName());

			product.setStock(product.getStock() + orderLine.getNumberOfProducts());
			DAOFactory.getProductDAO().updateProduct(product);
		}
	}

	
	// Save order and orderLines for a already existing order
	public void saveOrderAndOrderLinesInDatabaseForAlreadyExistingOrder(Product product, Order order) {
		
		//System.out.println(order.toString());
		
		
		if (order.getTotalOrderLines().size() == 0) {

			DAOFactory.getOrderDAO().deleteOrder(order);
			orderLineView.printOrderHasBeenDeletedBecauseOfNoMoreOrderLines();
			
		} else {
			
			order.setOrderDateTime(LocalDateTime.now());
			order.setTotalPrice(getTotalPriceOfOrder(order));
			
			
			
			// Update order
			DAOFactory.getOrderDAO().updateOrder(order);
			

			// Update orderLines
			for (OrderLine orderLineItems : order.getTotalOrderLines()) {
				DAOFactory.getOrderLineDAO().updateOrderLine(orderLineItems);
				orderLineItems.toString();
			}
			System.out.println(order.toString());
		}
		DAOFactory.getProductDAO().updateProduct(product);
	}

	
	// Save order and order lines for a new order
	public void saveOrderAndOrderLinesInDatabase(Order order) {

		order.setOrderDateTime(LocalDateTime.now());
		order.setTotalPrice(getTotalPriceOfOrder(order));
		// Create order
		int orderId = DAOFactory.getOrderDAO().createOrder(order);

		// Create orderLines
		DAOFactory.getOrderLineDAO().createOrderLine(order.getTotalOrderLines(), orderId);

	}
	

	// Add order lines into order
	public void addOrderLineToOrder(Order order) {

		productController.showProductList();
		
		// First ask the user for the product he wants to see/order
		orderLineView.printRequestNameOfProductToView();
		String productName = orderLineView.getStringInput();

		// Get the product from the database and store it in a product object
		Product retrievedProduct = DAOFactory.getProductDAO().getProductByName(productName);

		if (retrievedProduct != null) {

			// Print product details
			productView.printProductInfoHeader();
			System.out.println("\n" + retrievedProduct.toString() + "\n");

			// Ask how many products customer wants to order
			int requestedProduct = requestAmountOfProducts();

			// Check if the product stock is available
			if (checkProductStock(requestedProduct, retrievedProduct)) {

				// Check orderLines if product is already in another order line in the order 
				if (checkForDuplicateProductInOrderLines(order, requestedProduct, retrievedProduct)) {
					return;
				}

			// Create an orderLine object with the product and the amount of products chosen
			OrderLine orderLine = new OrderLine.OrderLineBuilder().product(retrievedProduct)
					.numberOfProducts(requestedProduct).build();

			// Print order line details
			System.out.println("Het volgende product is toegevoegd aan uw bestelling:");
			System.out.println(orderLine.toString());
			orderLineView.printYouCanAddMoreOrPlaceOrder();

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
			System.out.println("Productnummer " + (i + 1) + " - "+ order.getTotalOrderLines().get(i).toString());

		}

	}

	
	public void editOrDeleteOrderLineSwitchMenu(Order order) {

		Product updatedProduct;
		List<OrderLine> orderLineList = order.getTotalOrderLines();
		
		//Have user select an order line and store the selected order line in a variable
		int selectedOrderLineInt = viewAndSelectOrderLineInt(order);

		boolean updating = true;

		while (updating) {

			// ask to choose edit or delete order lines
			orderLineView.printEditOrDeleteOrderLine();
			int menuNumber = orderLineView.getIntInput();

			switch (menuNumber) {

			case 1: // update order line
				updatedProduct = updateNumberOfProductsInOrderLine(orderLineList.get(selectedOrderLineInt));
				saveOrderAndOrderLinesInDatabaseForAlreadyExistingOrder(updatedProduct, order);
				break;

			case 2: // delete order line
				choseOrderLineAndDelete(order, selectedOrderLineInt, orderLineList);
				updating = false;
				break;
				
			case 0:
				updating = false;
				break;
				
			default:
				orderLineView.printMenuInputIsWrong();
				break;
			}
		}
	}

	public void choseOrderLineAndDelete(Order order, int selectedOrderLineInt, List<OrderLine> orderLineList) {
		OrderLine orderLine = order.getTotalOrderLines().get(selectedOrderLineInt);
		DAOFactory.getOrderLineDAO().deleteOrderLine(orderLineList.get(selectedOrderLineInt));
		updateNegativeProductStockInDatabaseWhenOrderLineDeleted(orderLine);
		order.getTotalOrderLines().remove(orderLineList.get(selectedOrderLineInt));
		saveOrderAndOrderLinesInDatabaseForAlreadyExistingOrder(orderLine.getProduct(), order);
	}
	

	// Testing method to delete the orderLine from the order object
	public int viewAndSelectOrderLineInt(Order order) {
		
		List<OrderLine> orderLineList = order.getTotalOrderLines();
		
		viewAllOrderLinesInCurrentOrder(order);

		if (orderLineList.size() == 1) {

			return 0;

		} else {

			orderLineView.printAskUserToChoseOrderLine();
			int choice = orderLineView.getIntInput();

			while (choice < 1 | choice > orderLineList.size()) {

				orderLineView.printMenuInputIsWrong();
				choice = orderLineView.getIntInput();
			}
			return choice - 1;
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

	
	public void updateNegativeProductStockInDatabaseWhenOrderLineDeleted(OrderLine orderLine) {

		Product product = orderLine.getProduct();

		// deduct numberOfProducts that customer ordered from product stock
		product.setStock((product.getStock() + orderLine.getNumberOfProducts()));

		DAOFactory.getProductDAO().updateProduct(product);
	}

	
	// Update product stock
	public void updateProductInDatabase(OrderLine orderLine) {

		Product product = orderLine.getProduct();

		// deduct numberOfProducts that customer ordered from product stock
		product.setStock((product.getStock() - orderLine.getNumberOfProducts()));

		DAOFactory.getProductDAO().updateProduct(product);
	}

	
	public Product updateNumberOfProductsInOrderLine(OrderLine orderLine) {

		int oldNumberOfProducts = orderLine.getNumberOfProducts();

		orderLineView.printAskNewNumberOfProductsInOrderLine();
		int newNumberOfProducts = orderLineView.getIntInput();

		Product retrievedProduct = DAOFactory.getProductDAO().getProductById(orderLine.getProduct().getProductId());

		// check if product stock is available
		while (!checkProductStock((newNumberOfProducts - oldNumberOfProducts), retrievedProduct)) {

			// show current stock of the product so the user enters the right amount

			System.out.println(retrievedProduct.toString());
			orderLineView.printNewNumberOfProductsForOrderLineIsHigherThanStock();
			newNumberOfProducts = orderLineView.getIntInput();
		}

		// set new stock to product
		retrievedProduct.setStock(retrievedProduct.getStock() - (newNumberOfProducts - oldNumberOfProducts));
		// set new NumberOfProducts to order line
		orderLine.setNumberOfProducts(newNumberOfProducts);

		return retrievedProduct;

	}

}
