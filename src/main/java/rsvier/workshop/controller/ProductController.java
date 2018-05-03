package rsvier.workshop.controller;

import rsvier.workshop.view.ProductView;
import rsvier.workshop.view.View;

public class ProductController extends Controller {
	
	private ProductView productView = new ProductView();
	
	@Override
	public void runView() {
		productView.printHeaderMessage();
		productView.printMenuMessage();
		searchOrAddProductMenuSwitch(View.getIntInput());
	}
	
	public void searchOrAddProductMenuSwitch(int menuNumber) {
		
		switch(menuNumber)	{
			
		case 1:	//search product
				break;
		case 2: //add product
				break;
		case 3:	//show product list
				break;
		case 0: //exit
				break;
		default:
				break;
				
		}
		
	}
	
	
	public void updateOrDeleteProductSwitch(int menuNumber) {
		
		productView.printUpdateOrDeleteMenu();	
		int choice = View.getIntInput();
		
		switch(choice) {
			
		case 1:	//update product
				break;
		case 2: //delete product
				break;
		case 0: //back to previous menu
				break;
		default: //error message
				break;
		}
			
	}

}
