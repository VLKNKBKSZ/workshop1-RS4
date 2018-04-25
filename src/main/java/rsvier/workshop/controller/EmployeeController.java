package rsvier.workshop.controller;

import rsvier.workshop.domain.Account;
import rsvier.workshop.view.EmployeeView;

public class EmployeeController extends Controller{
	
	private EmployeeView employeeView = new EmployeeView();
	
	
	@Override
	public void runView() {
		
		employeeView.printHeaderMessage();
		employeeView.printMenuMessage();
		employeeMenuSwitch(employeeView.getIntInput());
		
	}

	
	public void  employeeMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {

		case 0:
			employeeView.printExitApplicationMessage();
			break;
			
		case 1:
			//klanten beheren
			break;

		case 2:
			//producten beheren
			break;
			
		case 3:
			//bestellingen beheren
			break;
			
		default:
			employeeView.printMenuInputIsWrong();
				
		}
	}

	
}
