package rsvier.workshop.dao;

import rsvier.workshop.domain.*;
import java.util.*;

public interface ProductDAO {

	public List<Product> getAllProducts();
	public Product getProductById(int productId);
	public Product getProductByName(String name);
	public void createProduct(Product product);
	public void updateProduct(Product product);
	public void deleteProduct(Product product);
	
}
