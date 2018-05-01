
package rsvier.workshop.controller;

import java.util.List;

import rsvier.workshop.dao.*;
import rsvier.workshop.controller.*;
import rsvier.workshop.domain.Account;
import rsvier.workshop.domain.Person;
import rsvier.workshop.view.*;
import rsvier.workshop.view.View;
import rsvier.workshop.controller.MainController.TypeOfController;

public class CustomerController extends Controller {

	private CustomerView customerView = new CustomerView();
	private PersonDAO personDao = new PersonDAOImp();
	private AccountDAO accountDao = new AccountDAOImp();
	private AddressDAO addressDao = new AddressDAOImp();
	private AccountController accountController = new AccountController();
	private PersonController personController = new PersonController();

	
	@Override
	public void runView() {
		
		customerView.printHeaderMessage();
		customerView.printMenuMessage();
		searchOrAddCustomerMenuSwitch(View.getIntInput());

	}
	
	public void searchOrAddCustomerMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {
			case 0:	//Go back to employee-menu

				MainController.setController(TypeOfController.EMPLOYEE);
				break;

			case 1:	//search customer by last name
				Person person = searchCustomerByLastName();
					
				if (person != null) {
						
					updateOrDeleteCustomerSwitch(person);
						
				} else {
						
				runView();
					
				}
					
				break;

			case 2:	//add customer
				AccountView.printMakeCustomerAccount();
				Account account = accountController.doCreateAccount();
				person = personController.doCreatePerson(account);

				runView();
					
				break;

			default:
				customerView.printMenuInputIsWrong();

		}
	}
	
	
	public void updateOrDeleteCustomerSwitch(Person person) {
		
		PersonController personController = new PersonController();

		customerView.printAskDeleteOrUpdateCustomer();
		int choice = View.getIntInput();

		switch (choice) {
			case 1:	//update person 
				personController.personUpdateMenuSwitch(person);
					
				break;
					
			case 2:	//delete person
				String yesOrNo = customerView.confirmYesOrNo();
					
				if (yesOrNo.equals("J")) {
				
					addressDao.deleteAddressByPersonId(person.getPersonId());
					accountDao.deleteAccount(person.getAccount());
					personDao.deletePerson(person);
				
				} else {

					runView();
						
				}

				break;
					
			case 0: //back to previous menu

				runView();

				break;
					
			default:
				break;
		}
	}


	public Person searchCustomerByLastName() {

		customerView.printAskCustomerLastName();
		String customerLastName = View.getStringInput();
		List<Person> customerList = personDao.getCustomerByLastName(customerLastName);


		if (customerList.size() == 0){
			customerView.printCustomerNotFound();
			return null;
		}

		if (customerList.size() == 1) {
			System.out.println(customerList.get(0).toString());
			return customerList.get(0);
			
		} else {

			for (int i = 1; i < customerList.size(); i++) {
				System.out.println("No. " + i + " : " + customerList.get(i - 1).toString());
			}

		}
		//Let user select specific person in case there are more than one
		return customerList.get(selectCustomer() - 1);
	}
	

	public int selectCustomer() {
		
		customerView.printAskNumberOfCustomer();
		return View.getIntInput();
		
	}


}

