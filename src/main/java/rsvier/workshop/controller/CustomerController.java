package rsvier.workshop.controller;

import java.util.List;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.Person;
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

		customerView.askCustomerLastName();
		String customerLastName = customerView.getStringInput();
		List<Person> customerList = personDao.getCustomerByLastName(customerLastName);

		if (customerList.size() == 1) {
			System.out.println(customerList.get(0).toString());
		} else {

			for (Person customer : customerList) {
				System.out.println(customer.toString());
			}

		}
	}

	public void selectCustomer() {
		customerView.printSelectCustomer();
		int chosenCustomer = customerView.getIntInput();
	}

	@Override
	public void runView() {
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		customerMenu(customerView.getIntInput());

	}
}
