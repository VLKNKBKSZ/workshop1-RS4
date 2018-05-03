package rsvier.workshop.controller;



import java.util.List;

import rsvier.workshop.controller.MainController.TypeOfController;
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
				Product product = searchProductByName();
				updateOrDeleteProductSwitch(product);
				runView();
				break;
		case 2: //add product
				break;
		case 3:	//show product list
				break;
		case 0: //back to previous menu
				MainController.setController(TypeOfController.EMPLOYEE);
				break;
		default://Goes back to this same menu
				productView.printMenuInputIsWrong();
				runView();
				break;
				
		}
		
	}
	
	
	public void updateOrDeleteProductSwitch(Product product) {
		
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
	
	public Product searchProductByName() {
	
		productView.printAskForProductName();
        String productName = View.getStringInput();
        
        Product returnedProduct = productDAO.getProductByName(productName);
        
		
        if (returnedProduct != null) {
        	
<<<<<<< HEAD
        		System.out.println(returnedProduct.toString());
=======
        		System.out.println("\n" + returnedProduct.toString());
>>>>>>> 491b5ab9f0b2662bce1f97108c7ec27c70d362e5
        		return returnedProduct;
        		
        } else {
        		productView.printProdutNotFound();
        		runView();
        		return null;
      
        }
	
	
    }

}
