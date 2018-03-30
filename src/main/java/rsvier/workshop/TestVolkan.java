package rsvier.workshop;

import java.util.*;
import java.math.BigDecimal;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Product;


public class TestVolkan {

	public static void main(String[] args) {

		ProductDAOImp abc = new ProductDAOImp();
		/*List<Product> test = abc.getAllProducts();
		System.out.println(test.get(0).getProductId() + " " + test.get(0).getName() + " " + test.get(0).getPrice() + " "
				+ test.get(0).getStock() + " " + test.get(0).getTimeStamp());

		System.out.println(test.get(1).getProductId() + " " + test.get(1).getName() + " " + test.get(1).getPrice() + " "
				+ test.get(1).getStock() + " " + test.get(1).getTimeStamp());
		System.out.println(test.get(2).getProductId() + " " + test.get(2).getName() + " " + test.get(2).getPrice() + " "
				+ test.get(2).getStock() + " " + test.get(2).getTimeStamp());

		Product a = abc.getProduct(2);
		System.out.println(a.getProductId() + " " + a.getName() + " " + a.getPrice() + " " + a.getStock() + " "
				+ a.getTimeStamp());
				*/
		double d = 34.99;
		BigDecimal b = new BigDecimal(d);
		Product p1 = new Product.ProductBuilder().name("Eettafelod").price(b).stock(111).build();
		//Product p2 = new Product.ProductBuilder().name("Eettafelod").price(b).stock(888).build();
		//abc.createProduct(p1);
		abc.updateProduct(p1, 2);
		
		
		
	}
}
