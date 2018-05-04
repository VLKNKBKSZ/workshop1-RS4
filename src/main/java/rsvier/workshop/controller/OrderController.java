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
	public Order searchOrderByLastname(Person person) {
		List<Order> orderList = null;

		if (person == null) {
			runView();
		} else {

			

				orderList = orderDao.getAllOrdersFromPerson(person);

				if (orderList.size() == 0) {
					orderView.printOrderNotFound();
					runView();
				}

				if (orderList.size() == 1) {
					System.out.println(orderList.get(0).toString());

				} else {

					for (int i = 1; i < orderList.size(); i++) {
						System.out.println("No. " + i + " : " + orderList.get(i - 1).toString());
					}
				}

			}
		
		return orderList.get(selectOrder() - 1);
	}

	public int selectOrder() {

		orderView.printAskSelectOrder();

		return orderView.getIntInput();
	}

	// Search order by OrderId
	public void searchOrderByOrderId(Order order) {

	}

}
