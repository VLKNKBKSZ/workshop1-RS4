package rsvier.workshop.controller;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.OrderLine;
import rsvier.workshop.view.OrderLineView;

public class OrderLineController extends Controller {
    private OrderLineView orderLineView = new OrderLineView();
    private ProductDAO productDAO = new ProductDAOImp();
    private OrderLine orderLine;
    
    

    @Override
    public void runView() {
        orderLineView.printHeaderMessage();
        orderLineView.printMenuMessage();
        orderLineMenuSwitch(orderLineView.getIntInput());
    }

    public void orderLineMenuSwitch(int menuChoice) {
    	
    		switch(menuChoice) {
    		case 1: //Add product to order
    					orderLineView.printRequestNameOfProductToView();
    					productDAO.getProductByName();
    					
    				//what product do you want to add?
    				break;
    		
    		case 2: //View current order
    				break;
    		case 3: //Place order
    				break;
    		case 4: //Cancel order
    				break;
    		}

    }
    
    
    
}
