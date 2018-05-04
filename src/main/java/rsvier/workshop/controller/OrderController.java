package rsvier.workshop.controller;

import rsvier.workshop.view.OrderView;

public class OrderController extends Controller {

	OrderView orderView = new OrderView();

	@Override
	public void runView() {
		orderView.printHeaderMessage();
		orderView.printMenuMessage();
		orderMenuSwitch(orderView.getIntInput());
	}

	public void orderMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 1: // Search Bestelling
			break;
		case 2: // Place Bestelling
			break;
		case 0: // Go back to previous

		default:
			orderView.printMenuInputIsWrong();
			runView();
		}
	}

	public void updateOrDeleteOrderSwitch(Order order) {

		orderView.printAskUserToUpdateOrDeleteProduct();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {

		case 1:// Update order

		case 2: // Delete order

		case 0:// Go back to previos Menu

		default:
			orderView.printMenuInputIsWrong();
			runView();

		}
	}
	
	// Search product by Lastname
	public void searchProductByLastname() {
		
		
		
	}
	
	// Search product by OrderId
	public void searchProductByOrderId() {
		
	}

}
