package rsvier.workshop;

import java.util.*;
import java.math.BigDecimal;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Product;
import rsvier.workshop.domain.Product.ProductBuilder;

public class TestVolkan {

	public static void main(String[] args) {

		//ProductDAOImp pd = new ProductDAOImp();
		// Product a = new Product.ProductBuilder().name("Lamp1").price(new
		// BigDecimal("233")).stock(15).build();
		// pd.createProduct(a);
		// Product b = pd.getProductByName("Lamp1");
		// System.out.println(b.toString());
		// Product c = pd.getProductById(6);
		// System.out.println(c.toString());
		// Product c = pd.getProductById(6);
		// pd.deleteProduct(c);

		/*
		 * @Test1.1: Get all products from the database and traverse true them =
		 * succesfull List<Product> list = pd.getAllProducts(); for(int i = 0; i <
		 * list.size(); i++) { System.out.println(list.get(i).toString()); }
		 * 
		 * for(Product i : list) { System.out.println(i.toString()); }
		 * 
		 * @Test1: create 3 products = succesfull Product a = new
		 * Product.ProductBuilder().name("Hoekbank Volkan").price(new
		 * BigDecimal("745.55")).stock(3).build(); Product b = new
		 * Product.ProductBuilder().name("Eettafel Eva").price(new
		 * BigDecimal("450.34")).stock(2).build(); Product c = new
		 * Product.ProductBuilder().name("Lamp Evy").price(new
		 * BigDecimal("123.44")).stock(23).build();
		 * 
		 * @Test2: add created products to Database = succesfull
		 * abc.createProduct(a/b/c)
		 * 
		 * @Test3: get productByName / getProductById = succesfull Product bb =
		 * abc.getProductByName("Hoekbank Volkan"); System.out.println(bb.toString());
		 * 
		 * @Test4: delete product by passing in Product Object. Product a1 =
		 * abc.getProductByname("Hoekbank Volkan") // Its being deleted based on unique
		 * id. abc.deleteProduct(a1);
		 * 
		 * @Test5: update of product by passing in product = succesfull 1:
		 * getProductByName or Id Product bb = abc.getProductByName("Lamp"); 2: Create
		 * new product with Product.ProductBuilder updateProduct = new
		 * Product.ProductBuilder(bb); 3: use new variable to change product specs .
		 * updateProduct.name().price().stock(); 4: Build that product and assign it to
		 * a variable. Product updatedProduct = updateProduct.build(); 5:
		 * abc.updateProduct(updatedProduct); This is going to be implemented in the
		 * view or controller i guess.
		 */

	}
}
