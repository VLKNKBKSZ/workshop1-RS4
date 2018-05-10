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
		searchCreateOrderMenuSwitch(orderView.getIntInput());
	}

	
	public void searchCreateOrderMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 1: // Search order
				searchOrderSwitch();
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

	
	//Search order by order-id or customer last name
	
	public void searchOrderSwitch() {

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
				searchOrderSwitch();
				break;
		}
	}
	
	
	public void updateOrDeleteOrderSwitch(Order order) {
		
		//Check if given order id exists in database, else go back to menu
		do {
			orderView.printOrderNotFound();
			searchOrderSwitch();
			}
			while(order == null);
			
		//Print the orderlines in the order
		for (OrderLine orderLine: order.getTotalOrderLines()) {
				
			System.out.println(orderLine.toString());
		}

		orderView.printAskUserToUpdateOrDeleteOrder();
		int menuNumber = orderView.getIntInput();

		switch (menuNumber) {

				case 1:// Update order
						updateExistingOrderSwitch(order);
						break;
						
				case 2: // Delete order
						orderDao.deleteOrder(order);
						break;

				case 0:// Go back to previous Menu

				default:
					orderView.printMenuInputIsWrong();
					runView();
		}	
	}
	
	
	// Search order by OrderId
	public Order searchOrderByOrderId() {

		orderView.printAskOrderId();

		return orderDao.getOrderById(orderView.getIntInput());
	}
		
	

	// Search order by Last name
	public Order searchOrderByLastName(Person person) {

		List<Order> orderList = new ArrayList<>();

		if (person == null) {

			customerView.printCustomerNotFound();
			searchOrderSwitch();

		} else {

			orderList = orderDao.getAllOrdersFromPerson(person);

			if (orderList.size() == 0) {

				// print "Geen bestellingen gevonden"
				return null;
			}

			if (orderList.size() == 1) {

				orderView.printOrdersFound(orderList.get(0));

				return orderList.get(0);

			} else {

				for (int i = 1; i < orderList.size(); i++) {
					orderView.printOrdersFound(orderList.get(i - 1));
				}
			}
		}

		return orderList.get(selectOrder() - 1);
	}

		
	
	public int selectOrder() {

		orderView.printAskSelectOrder();

		return orderView.getIntInput();
	}

	
	
	public void updateExistingOrderSwitch(Order order) {
		
		Boolean updating = true;
		while (updating) {
			
			orderView.printUpdateExistingOrder();
			int menuNumber = orderView.getIntInput();
		
			switch(menuNumber) {
		
			case 1: //Go to orderLine
					orderLineController.viewAllOrderLinesInCurrentOrder(order);
				
					break;
				
			case 2: //Add orderLines to order
					orderLineController.addOrderLineToOrder(order);
					break;
			
			case 3: //Delete order
					break;
		
			case 4: //Save changes in the database
				
					updating = false;
					break;
					
			case 0: updating = false;
					break;
			default: 
					break;
			}
		}
				
	}
	
	
	
	/*	orderView.printWhatOrderToUpdate();
		int choice = orderView.getIntInput();
		
		editOrderLines(choice, order);
}
*/
	
	
	
	//with the number you pick the index in the arraylist you want to edit
	public void editOrderLines(int choice, Order order)	{
		
		//First print out the choosen orderline
		System.out.println(order.getTotalOrderLines().get(choice - 1));
		
	//	orderView.print
		
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