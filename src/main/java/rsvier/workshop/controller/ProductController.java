package rsvier.workshop.controller;

import rsvier.workshop.view.ProductView;

public class ProductController extends Controller {
	
	private ProductView productView = new ProductView();
	
	@Override
	public void runView() {
		productView.printHeaderMessage();
		productView.printMenuMessage();
		
	}

}
