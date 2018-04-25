package rsvier.workshop.controller;

import rsvier.workshop.dao.*;
import rsvier.workshop.view.CustomerView;

public class CustomerController extends Controller {
	
	private CustomerView customerView = new CustomerView();
	private PersonDAO personDao = new PersonDAOImp();

	public void customerMenu(int menuNumber) {

		switch (menuNumber) {

		case 0:
			customerView.printExitApplicationMessage();
			break;

		case 1:

			break;

		case 2:

			break;

		default:
			customerView.printMenuInputIsWrong();

		}
	}

	public void searchCustomer() {

	}

	@Override
	public void runView() {
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		customerMenu(customerView.getIntInput());

	}
}
