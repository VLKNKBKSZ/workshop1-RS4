package rsvier.workshop.controller;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.Order;
import rsvier.workshop.domain.OrderLine;
import rsvier.workshop.domain.OrderLine.OrderLineBuilder;
import rsvier.workshop.domain.Product;
import rsvier.workshop.view.OrderLineView;
import rsvier.workshop.view.ProductView;

public class OrderLineController extends Controller {
    private OrderLineView orderLineView = new OrderLineView();
    private ProductDAO productDAO = new ProductDAOImp();
    private ProductView productView = new ProductView();
    private OrderLine orderLine;
    private Order order;
  
    

    @Override
    public void runView() {
        orderLineView.printHeaderMessage();
        orderLineView.printMenuMessage();
  //      orderLineMenuSwitch(orderLineView.getIntInput());
    }

    public void orderLineMenuSwitch(Order order) {
    		
    		orderLineView.printHeaderMessage();
    		int menuChoice = orderLineView.getIntInput();
    		
    		
    		while(menuChoice != 3 && menuChoice != 4){
    			
    			switch(menuChoice) {
    			
    			case 1: //Add product to order
    					
    					addOrderLineToOrder(order);
    					
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
    
    //search based on name
    public void addOrderLineToOrder(Order order) {
    		
    		orderLineView.printRequestNameOfProductToView();
    		String productName = orderLineView.getStringInput();
    		Product retrievedProduct = productDAO.getProductByName(productName);
    		
    		if (retrievedProduct != null) {
    			System.out.println("\n" + retrievedProduct.toString());
    			
    			OrderLine.OrderLineBuilder orderLine = new OrderLine.OrderLineBuilder();
    			orderLine.numberOfProducts(requestAmountOfProducts());
    			
    			order.getOrderLine().add(orderLine.build());
    			
    		}
    		
    		else {
    			productView.printProductNotFound();
    			runView();
    		}
    }
    		
    //method for asking how many of the product
    public int requestAmountOfProducts() {
    		
    		orderLineView.printRequestAmountOfProducts();
    		int amountOfProducts = orderLineView.getIntInput();
    		
    		return amountOfProducts;
    }
    
    
}
