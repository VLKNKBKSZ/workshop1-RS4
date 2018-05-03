package rsvier.workshop.controller;

import rsvier.workshop.domain.*;
import rsvier.workshop.view.EmployeeView;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.view.View;

public class EmployeeController extends Controller{
	
	private EmployeeView employeeView = new EmployeeView();
	
	
	@Override
	public void runView() {
		
		employeeView.printHeaderMessage();
		employeeView.printMenuMessage();
		employeeMenuSwitch(View.getIntInput());
		
	}

	
	public void  employeeMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {

			case 1:	//Klanten beheren
					MainController.setController(TypeOfController.CUSTOMER);
					break;

			case 2: //producten beheren
					
					break;
			
			case 3:	//bestellingen beheren
				
					break;
			case 0:	//Exit Menu
					
					MainController.setController(TypeOfController.MAINMENU);
			
					break;
			
			default:
					employeeView.printMenuInputIsWrong();
					runView();
				
					break;
		}
	}
}
