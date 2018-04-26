package rsvier.workshop.controller;

import java.util.List;

import rsvier.workshop.dao.*;
import rsvier.workshop.controller.*;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.CustomerView;

public class CustomerController extends Controller {

	private CustomerView customerView = new CustomerView();
	private PersonDAO personDao = new PersonDAOImp();
	private AccountDAO accountDao = new AccountDAOImp();
	private AddressDAO addressDao = new AddressDAOImp();
	private AccountController accountController = new AccountController();
	private EmployeeController employeeController = new EmployeeController();

	
	@Override
	public void runView() {
		
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		customerMenuSwitch(customerView.getIntInput());

	}
	
	public void customerMenuSwitch(int menuNumber) {

		switch (menuNumber) {

		case 0://leave and go back to previous menu
			customerView.printExitApplicationMessage();
			employeeController.runView();
			break;

		case 1://search customer by lastname
			Person person = searchCustomer();
			updateOrDeleteCustomerSwitch(person);
			break;

		case 2:
			//add customer
			accountController.doCreateAccount();
			runView();
			break;

		default:
			customerView.printMenuInputIsWrong();

		}
	}
	
	
	public void updateOrDeleteCustomerSwitch(Person person) {

		customerView.printAskDeleteOrUpdateCustomer();
		int choice = customerView.getIntInput();

		switch (choice) {
		case 1:
			
			//update customer
			break;
		case 2:
			//delete customer
			String yesOrNo = customerView.confirmYesOrNo();
			if (yesOrNo.equals("J")) {
				
				addressDao.deleteAddressByPersonId(person.getPersonId());
				accountDao.deleteAccount(person.getAccount());
				personDao.deletePerson(person);
			} else {

			}

			break;
		case 0: //back to previous menu
			break;
		default:
			break;
		}
	}


	public Person searchCustomer() {

		customerView.printAskCustomerLastName();
		String customerLastName = customerView.getStringInput();
		List<Person> customerList = personDao.getCustomerByLastName(customerLastName);

		if (customerList.size() == 1) {
			System.out.println(customerList.get(0).toString());
		} else {

			for (int i = 1; i < customerList.size(); i++) {
				System.out.println("No. " + i + " : " + customerList.get(i - 1).toString());
			}

		}
		return customerList.get(selectCustomer() - 1);
	}
	

	public int selectCustomer() {

		customerView.printAskNumberOfCustomer();
		int chosenCustomerNumber = customerView.getIntInput();

		return chosenCustomerNumber;
		
	}


}
