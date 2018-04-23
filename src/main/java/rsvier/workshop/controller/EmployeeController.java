package rsvier.workshop.controller;

import rsvier.workshop.domain.Account;
import rsvier.workshop.view.EmployeeView;

public class EmployeeController {
	
	private EmployeeView employeeView = new EmployeeView();
	
	public void doEmployeeMenu() {
		
		employeeView.printHeaderMessage();
		employeeView.printMenuMessage();
		employeeMenu(employeeView.getIntInput());
	}
	

	private void  employeeMenu(int menuNumber) {
		
		switch (menuNumber) {

		case 0:
			employeeView.printExitApplicationMessage();
			break;
			
		case 1:
			
			break;

		case 2:
			
			break;
			
			default:
				employeeView.printMenuInputIsWrong();
				

		}
	}

	
}
