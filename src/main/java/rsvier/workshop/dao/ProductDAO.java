package rsvier.workshop.dao;

import rsvier.workshop.domain.*;
import java.util.*;

public interface ProductDAO {

	public List<Product> getAllProducts();
	public Product getProduct(int productId);
	public void createProduct(Product product);
	public void updateProduct(Product product, int productId);
	public void deleteProduct(int productId);
	
}
