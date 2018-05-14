package rsvier.workshop.controller;

import java.math.BigDecimal;
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

		switch (menuNumber) {

		case 1: // search product
			searchProductByName();
			runView();
			break;

		case 2: // add product
			doCreateProduct();
			runView();
			break;

		case 3: // show product list
			showProductList();
			runView();
			break;

		case 0: // back to previous menu
			MainController.setController(TypeOfController.EMPLOYEE);
			break;

		default:// Goes back to this same menu
			productView.printMenuInputIsWrong();
			runView();
			break;

		}

	}

	public void updateOrDeleteProductSwitch(Product product) {

		productView.printUpdateOrDeleteMenu();
		int choice = productView.getIntInput();

		switch (choice) {

		case 1: // update product
			updateProductSwitch(product);
			break;

		case 2: // delete product
			doDeleteProduct(product);
			break;

		case 0: // back to previous menu
			runView();
			break;

		default: // error message
			productView.printMenuInputIsWrong();
			break;
		}

	}

	// Method asks user for product name and gets product from the database:

	public void searchProductByName() {

		productView.printAskForProductName();
		String productName = productView.getStringInput();

		Product returnedProduct = productDAO.getProductByName(productName);

		if (returnedProduct != null) {

			System.out.println("\n" + returnedProduct.toString());
			updateOrDeleteProductSwitch(returnedProduct);

		} else {
			productView.printProductNotFound();
		}

	}

	public void doCreateProduct() {

		Product.ProductBuilder productBuilder = new Product.ProductBuilder();
		productView.printAskForProductName();
		String nameOfNewProduct = productView.getStringInput();

		if (checkIfProductAlreadyExists(nameOfNewProduct)) {

			productView.printProductNameAlreadyExists();
			return;
		}

		productBuilder.name(nameOfNewProduct);
		productView.printAskForProductPrice();
		productBuilder.price(productView.getBigDecimalInput());
		productView.printAskForProductStock();
		productBuilder.stock(productView.getIntInput());
		Product product = productBuilder.build();

		productDAO.createProduct(product);
		productView.printProductIsSuccessfullyCreated();
	}

	public void doDeleteProduct(Product product) {

		if (productView.confirmYesOrNo().equalsIgnoreCase("J")) {

			productDAO.deleteProduct(product);

		} else {
			runView();
		}
	}

	public boolean checkIfProductAlreadyExists(String nameOfProduct) {

		if (productDAO.getProductByName(nameOfProduct) != null) {

			return true;
		}
		return false;

	}

	public void showProductList() {
		
		System.out.println("\nLijst van alle Nevvo-Producten:");
		System.out.println("-------------------------------");
		List<Product> products = productDAO.getAllProducts();

		for (Product product : products) {
			System.out.println(product.toString());
		}
		System.out.println("-------------------------------\n");
	}

	
	public void updateProductSwitch(Product product) {

		boolean updatingProduct = true;

		while (updatingProduct) {

			productView.printUpdateProduct();
			int choice = productView.getIntInput();

			switch (choice) {

			case 1: // Show current product
				System.out.println(product.toString());
				break;

			case 2: // Update product name
				product.setName(updateProductName());
				break;

			case 3: // Update product price
				product.setPrice(updateProductPrice());
				break;

			case 4: // Update product stock
				product.setStock(updateProductStock());
				break;

			case 5: // Save updates in database
				updateProduct(product);
				break;

			case 0: // Go back to previous menu
				updatingProduct = false;
				break;

			default:
				productView.printMenuInputIsWrong();
			}
		}
	}

	// Methods for updating each field of product

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
	
	// Method for updating product

	public void updateProduct(Product product) {
		productDAO.updateProduct(product);
	}
}
