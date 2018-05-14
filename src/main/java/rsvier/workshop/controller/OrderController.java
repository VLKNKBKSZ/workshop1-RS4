package rsvier.workshop.controller;

import java.time.LocalDateTime;
import java.util.*;

import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderController extends Controller {

	private OrderView orderView = new OrderView();
	private OrderDAO orderDAO = new OrderDAOImp();
	private CustomerController customerController = new CustomerController();
	private OrderLineController orderLineController = new OrderLineController();
	private CustomerView customerView = new CustomerView();
	private OrderLineDAO orderLineDAO = new OrderLineDAOImp();

	@Override
	public void runView() {
		orderView.printHeaderMessage();
		orderView.printMenuMessage();
		searchCreateOrderSwitch(orderView.getIntInput());
	}

	public void searchCreateOrderSwitch(int menuNumber) {

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

		default:
			orderView.printMenuInputIsWrong();
			runView();
			break;
		}
	}

	// Search order by order-id or customer last name
	public void searchOrderSwitch() {

		orderView.printAskSearchOrderByNumberOrByName();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {
		case 1:// Search order by order ID
			searchOrderByOrderId();
			break;

		case 2:// Search order by customer last name
			updateOrDeleteOrderSwitch(searchOrderByLastName(customerController.searchCustomerByLastName()));
			break;

		case 0: // Back to previous menu
			runView();
			break;

		default: // Back to this same menu
			orderView.printMenuInputIsWrong();
			searchOrderSwitch();
			break;
		}
	}

	public void updateOrDeleteOrderSwitch(Order order) {

		orderView.printAskUserToUpdateOrDeleteOrder();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {

		case 1:// Update order
			updateExistingOrder(order);
			break;

		case 2: // Delete order
			doDeleteOrder(order);
			break;

		case 0:// Go back to EmployeeController

			MainController.setController(TypeOfController.ORDER);

		default:
			orderView.printMenuInputIsWrong();
			runView();

		}

	}

	public void updateExistingOrder(Order order) {

		boolean updating = true;

		while (updating) {

			orderView.printUpdateExistingOrder();
			int menuNumber = orderView.getIntInput();

			switch (menuNumber) {

			case 1: // Go to orderLine
				orderLineController.editOrDeleteOrderLineSwitchMenu(order);
				updating = false;
				break;

			case 2: // Add orderLines to order
				orderLineController.addOrderLineToOrder(order);
				orderLineDAO.createOrderLine(order.getTotalOrderLines().get(order.getTotalOrderLines().size ()-1), order.getOrderId());
				order.setOrderDateTime(LocalDateTime.now());
		        order.setTotalPrice(orderLineController.getTotalPriceOfOrder(order));
		        orderDAO.updateOrder(order);
				updating = false;
				break;
			case 0:
				MainController.setController(TypeOfController.ORDER);
				updating = false;
				break;
			default:
				break;
			}
		}

		// After we are done here we want to back to the main employeecontroller
		// runview?

		MainController.setController(TypeOfController.EMPLOYEE);

	}

	public void selectOrderSearchMenuSwitch() {

		orderView.printAskSearchOrderByNumberOrByName();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {
		case 1:// Search order by order ID
			searchOrderByOrderId();
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

		// One person can have more than one order, so first get all orders
		// and store them in a order list

		List<Order> orderList = new ArrayList<>();

		if (person == null) {

			runView();

		} else {

			orderList = orderDAO.getAllOrdersFromPerson(person);

			if (orderList.size() == 0) {

				orderView.printYouDontHaveOrders();
				runView();
				return null;
			}

			if (orderList.size() == 1) {

				orderView.printOrdersFound("Order nummer:", 1, orderList.get(0));

				return orderList.get(0);

			} else {

				// if the person has more than 1 order, a list is printed
				for (int i = 0; i < orderList.size(); i++) {

					orderView.printOrdersFound("Order nummer:", i + 1, orderList.get(i));
				}
			}

		}

		// The user is then asked to select an order
		int choice = selectOrder();
		while (choice < 1 | choice > orderList.size()) {
			orderView.printMenuInputIsWrong();
			choice = selectOrder();
		}

		return (orderList.get(choice - 1));
	}

	public int selectOrder() {

		orderView.printAskSelectOrder();

		return orderView.getIntInput();
	}

	// Search order by OrderId
	public void searchOrderByOrderId() {

		orderView.printAskOrderId();
		Order order = orderDAO.getOrderById(orderView.getIntInput());

		if (order == null) {
			orderView.printOrderNotFound();
			runView();
		} else {
			orderView.printOrdersFound("Order nummer", 1, order);
			updateOrDeleteOrderSwitch(order);
		}
	}

	public void doDeleteOrder(Order order) {

		if (orderView.confirmYesOrNo().equalsIgnoreCase("J")) {
			orderDAO.deleteOrder(order);
			orderView.printOrderSuccessFullyDeleted();
			runView();
		} else {
			runView();
		}

	}

	public void doCreateOrder(Person person) {

		// check if person was found
		if (person == null) {

			runView();

		} else {

			// Build an order object with the person object
			Order.OrderBuilder orderBuilder = new Order.OrderBuilder();
			orderBuilder.person(person);
			Order order = orderBuilder.build();
			order.getPerson().toString();
			order.toString();

			// Pass the order to the switch in the orderLineController
			orderLineController.orderLineMenuSwitch(order);

		}

	}
}