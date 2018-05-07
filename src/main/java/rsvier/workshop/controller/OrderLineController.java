package rsvier.workshop.controller;

import java.math.BigDecimal;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class OrderLineController extends Controller {
    private OrderLineView orderLineView = new OrderLineView();
    private ProductDAO productDAO = new ProductDAOImp();
    private ProductView productView = new ProductView();
    private OrderView orderView = new OrderView();
    private OrderLine orderLine;
    private Order order;
    private OrderDAO orderDAO = new OrderDAOImp();
  
    

    @Override
    public void runView() {
        orderLineView.printHeaderMessage();
        orderLineView.printMenuMessage();
  //      orderLineMenuSwitch(orderLineView.getIntInput());
    }

    public void orderLineMenuSwitch(Order order) {
    		
    		boolean placeOrderOrCancelOrder = true;
    		
    		while(placeOrderOrCancelOrder){
    			
    			orderLineView.printMenuMessage();
            int menuChoice = orderLineView.getIntInput();
    			
    			switch(menuChoice) {
    			
    			case 1: //Add orderline(s) (i.e. product and amount of products) to order object
    					addOrderLineToOrder(order);
    					
    					break;
    		
    			case 2: //View current order
    					viewAllOrderlinesInCurrentOrder(order);
    					showTotalPriceOfCurrentOrder(order);
    					break;
    					
    			case 3: //Place order. Method to place the order in the database
    					
    					orderDAO.createOrder(order);
    					orderView.printOrderHasBeenPlaced();
    					
    					placeOrderOrCancelOrder = false;
    					break;
    					
    			case 4: //Cancel order
    					placeOrderOrCancelOrder = false;
    					break;
    			}
    		}

    }
    

    //search based on name
    public void addOrderLineToOrder(Order order) {
    		
    		//First ask the user for the product he wants to see/order
    		orderLineView.printRequestNameOfProductToView();
    		String productName = orderLineView.getStringInput();
    		
    		//Get the product from the database and store it in a product object
    		Product retrievedProduct = productDAO.getProductByName(productName);
    		
    		if (retrievedProduct != null) {
    			System.out.println("\n" + retrievedProduct.toString() + "\n");
    			
    			//Create an orderline object with the product and the amount of products chosen
    			OrderLine orderLine = new OrderLine.OrderLineBuilder().product(retrievedProduct)
    			.numberOfProducts(requestAmountOfProducts()).build();
    			
    			
    			//Add the created orderline to the order
    			//GETTING A NULL POINTER HERE NOW
    			order.getOrderLine().add(orderLine);
    			
    		}
    		
    		else {
    			productView.printProductNotFound();
    			runView();
    		}
    }
    		
    //Method for asking how many copies of the product
    public int requestAmountOfProducts() {
    		
    		orderLineView.printRequestAmountOfProducts();
    		int amountOfProducts = orderLineView.getIntInput();
    		
    		return amountOfProducts;
    }
    
    //Method for viewing all the orderlines in the current order
    public void viewAllOrderlinesInCurrentOrder(Order order) {
    		
    		if (order.getOrderLine().isEmpty()) {
    			orderLineView.printOrderIsEmpty();
    			runView();
    		}
    		
    		for (OrderLine orderLine: order.getOrderLine()) {
			System.out.println("\n" + orderLine.toString());
    		}
    }
    
    //Method for showing total price
    public void showTotalPriceOfCurrentOrder(Order order) {
    		
    		BigDecimal totalPrice = new BigDecimal(0);
    		
    		for (OrderLine orderLine: order.getOrderLine()) {
    			
    			BigDecimal numberOfProductsInBigDecimal = (BigDecimal.valueOf(orderLine.getNumberOfProducts()));
    			totalPrice = (totalPrice.add((orderLine.getProduct().getPrice())) . multiply(numberOfProductsInBigDecimal));
   			
    			System.out.println("The total price is now € " + totalPrice);
    		}
    }
    

}
