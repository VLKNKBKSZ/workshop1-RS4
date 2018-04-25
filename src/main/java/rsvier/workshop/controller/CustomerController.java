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

		customerView.printAskCustomerLastName();
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

	public int selectCustomer() {
		customerView.printSelectCustomer();
		int chosenCustomerId = customerView.getIntInput();

		return chosenCustomerId;
	}

	public void updateOrDeleteCustomerSwitch(int chosenCustomerId) {

		customerView.printAskDeleteOrUpdateCustomer();
		int choice = customerView.getIntInput();

		switch (choice) {
		case 1:
			
			break;
		case 2:
			
			String yesOrNo = customerView.confirmYesOrNoSwitch()
			if(yesOrNo.equals("J")) {
				Person person = personDao.getPersonById(chosenCustomerId);
				personDao.deletePerson(person);
			} else {
				
			}
				
			
			break;
		case 0:
			break;
		default:
			break;
		}
	}

	@Override
	public void runView() {
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		customerMenu(customerView.getIntInput());

	}

	
	
}
