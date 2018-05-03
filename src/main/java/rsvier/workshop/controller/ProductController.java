package rsvier.workshop.controller;



import java.util.List;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;


public class ProductController extends Controller {
	
	private ProductView productView = new ProductView();
	private ProductDAO productDAO = new ProductDAOImp();
	
	@Override
	public void runView() {
		productView.printHeaderMessage();
		productView.printMenuMessage();
		searchOrAddProductMenuSwitch(View.getIntInput());
	}
	
	public void searchOrAddProductMenuSwitch(int menuNumber) {
		
		switch(menuNumber)	{
			
		case 1:	//search product
				productView.printAskForProductName();
				searchProductByName(productView.getStringInput());
				break;
		case 2: //add product
				break;
		case 3:	//show product list
				break;
		case 0: //back to previous menu
				runView();
				break;
		default://Goes back to this same menu
				productView.printMenuInputIsWrong();
				runView();
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
				runView();
				break;
		default: //error message
				
				break;
		}
			
	}
	
	public void searchProductByName(String name) {
	
		productView.printAskForProductName();
		String productName = productView.getStringInput();
		Product retrievedProduct = productDAO.getProductByName(productName);
		retrievedProduct.toString();
		productView.printUpdateOrDeleteMenu();
		updateOrDeleteProductSwitch(productView.getIntInput());
		
	}

}
