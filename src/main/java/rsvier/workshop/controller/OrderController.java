package rsvier.workshop.controller;

import java.util.*;

import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderController extends Controller {

	private OrderView orderView = new OrderView();
	private OrderDAO orderDao = new OrderDAOImp();
	private CustomerController customerController = new CustomerController();
	private OrderLineController orderLineController = new OrderLineController();
	private CustomerView customerView = new CustomerView();

	@Override
	public void runView() {
		orderView.printHeaderMessage();
		orderView.printMenuMessage();
		orderMenuSwitch(orderView.getIntInput());
	}

	public void orderMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 1: // Search order
				selectOrderSearchMenuSwitch();
				break;
				
		case 2: // Create order for a customer
				doCreateOrder((customerController.searchCustomerByLastName()));
				break;
				
		case 0: // Go back to previous menu
				MainController.setController(TypeOfController.EMPLOYEE);
				break;
				
		default:	orderView.printMenuInputIsWrong();
				runView();
				break;
		}
	}

	public void updateOrDeleteOrderSwitch(Order order) {

		if(order == null) {
			orderView.printOrderNotFound();
			runView();

		}	else {

			orderView.printAskUserToUpdateOrDeleteProduct();
			int menuNumber = orderView.getIntInput();

			switch (menuNumber) {

				case 1:// Update order

				case 2: // Delete order
					orderDao.deleteOrder(order);

				case 0:// Go back to previous Menu

				default:
					orderView.printMenuInputIsWrong();
					runView();

			}
		}
	}

	public void selectOrderSearchMenuSwitch() {

		orderView.printAskSearchOrderByNumberOrByName();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {
		case 1:// Search order by order ID
				updateOrDeleteOrderSwitch(searchOrderByOrderId());
				break;
				
		case 2:// Search order by customer last name
				updateOrDeleteOrderSwitch(searchOrderByLastName(customerController.searchCustomerByLastName()));
				break;
				
		case 0: // Back to previous menu
				runView();
				break;
				
		default: // Back to this same menu
				orderView.printMenuInputIsWrong();
				selectOrderSearchMenuSwitch();
				break;
		}
	}

	// Search order by Last name
	public Order searchOrderByLastName(Person person) {

		List<Order> orderList = null;

		if (person == null) {
			
			customerView.printCustomerNotFound();
			runView();
			
		} else {

			orderList = orderDao.getAllOrdersFromPerson(person);

			if (orderList.size() == 0) {
				
				//print "Geen bestellingen gevonden"
				return null;
			}

			if (orderList.size() == 1) {
				
				System.out.println(orderList.get(0).toString());

				return orderList.get(0);

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
	public Order searchOrderByOrderId() {

		orderView.printAskOrderId();

		return orderDao.getOrderById(orderView.getIntInput());
	}

	
	
	public void doCreateOrder(Person person) {

		//check if person was found
		if (person == null) {
			runView();
		} else {

			//Build an order object with the person object
			Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
			orderBuilder.person(person);
			Order order = orderBuilder.build();
			order.getPerson().toString();
			order.toString();
			
			//Pass the order to the switch in the orderLineController
			orderLineController.orderLineMenuSwitch(order);
			

		}

	}

}