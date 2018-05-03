package rsvier.workshop.controller;



import java.math.BigDecimal;
import java.util.ArrayList;
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
		searchOrAddProductMenuSwitch(productView.getIntInput());
	}
	
	public void searchOrAddProductMenuSwitch(int menuNumber) {
		
		switch(menuNumber)	{
			
		case 1:	//search product
				Product product = searchProductByName();
				updateOrDeleteProductSwitch(product);
				runView();
				break;
		case 2: //add product
				doCreateProduct();
				runView();
				break;
		case 3:	//show product list
				showProductList();
				runView();
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
		int choice = productView.getIntInput();
		
		switch(choice) {
			
		case 1:	//update product
				updateProductSwitch(product);
				break;
		case 2: //delete product
				doDeleteProduct(product);
				break;
		case 0: //back to previous menu
				runView();
				break;
		default: //error message
				productView.printMenuInputIsWrong();
				break;
		}
			
	}
	
	
	//Method asks user for product name and returns product from the database:
	
	public Product searchProductByName() {
	
		productView.printAskForProductName();
        String productName = productView.getStringInput();
        
        Product returnedProduct = productDAO.getProductByName(productName);
        
        if (returnedProduct != null) {
        	
        		System.out.println("\n" + returnedProduct.toString());
        		return returnedProduct;
        		
        } else {
        		productView.printProductNotFound();
        		runView();
        		return null;
      
        }

    }

    public void doCreateProduct () {
		Product.ProductBuilder productBuilder = new Product.ProductBuilder();
		productView.printAskForProductName();
		productBuilder.name(productView.getStringInput());
		productView.printAskForProductPrice();
		productBuilder.price(productView.getBigDecimalInput());
		productView.printAskForProductStock();
		productBuilder.stock(productView.getIntInput());
		Product product = productBuilder.build();
		productDAO.createProduct(product);
	}

	public void doDeleteProduct(Product product) {

		if (productView.confirmYesOrNo().equalsIgnoreCase("J")) {
			productDAO.deleteProduct(product);
		} else {
			runView();
		}
	}

	public void showProductList() {
		List<Product> products = productDAO.getAllProducts();

		for (Product product : products) {
			System.out.println(product.toString());
		}
	}

	public void updateProductSwitch(Product product) {

		boolean updatingProduct = true;

		while(updatingProduct) {

			productView.printUpdateProduct();
			int choice = productView.getIntInput();

			switch (choice) {

				case 1: System.out.println(product.toString());
						break;

				case 2: product.setName(updateProductName());
						break;

				case 3: product.setPrice(updateProductPrice());
						break;

				case 4:	product.setStock(updateProductStock());
						break;

				case 5: productDAO.updateProduct(product);
						break;

				case 0: updatingProduct = false;
						break;

				default: productView.printMenuInputIsWrong();
			}
		}
	}

	public String updateProductName() {
		productView.printAskForProductName();
		return productView.getStringInput();
	}

	public BigDecimal updateProductPrice() {
		productView.printAskForProductPrice();
		return productView.getBigDecimalInput();
	}

	public int updateProductStock() {
		productView.printAskForProductStock();
		return productView.getIntInput();
	}
}
