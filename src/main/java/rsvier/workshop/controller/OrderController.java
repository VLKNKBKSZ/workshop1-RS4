package rsvier.workshop.controller;

import java.util.*;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderController extends Controller {

	private OrderView orderView = new OrderView();
	private OrderDAO orderDao = new OrderDAOImp();

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
	
	// Search order by Lastname
	public void searchOrderByLastname(Person person) {
		
		if( person == null ) {
			runView();
		}else {
			List<Order> orderList = orderDao.getAllOrdersFromPerson(person);
			for(Order order : orderList) {
				System.out.println(order.toString());
			}
		}
		
		
		
		
		
		
		
	}
	
	// Search order by OrderId
	public void searchOrderByOrderId(Order order) {
		
	}

}
