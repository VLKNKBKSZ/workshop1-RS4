package rsvier.workshop.view;

import java.util.*;

import rsvier.workshop.domain.Product;

public class ProductView {

	public void displayAllProducts(List<Product> allProducts) {
		for(Product product: allProducts) {
			System.out.println(product.toString());
		}
		
	}
}
