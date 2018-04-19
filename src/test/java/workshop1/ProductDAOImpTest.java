package workshop1;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import java.math.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.junit.jupiter.api.*;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Product;
import rsvier.workshop.utility.*;

/**
 * @author Volkan Kabaksiz
 *
 */
class ProductDAOImpTest {

	ProductDAOImp productDAO = new ProductDAOImp();
	private Logger logger = LogConnection.getLogger();

	@BeforeEach
	void setUp() {

		try (Connection conn = DatabaseConnectionXML.getConnection(); Statement statement = conn.createStatement();) {

			String query = "INSERT INTO product (name, price , stock) VALUES ('Tafel',95.00, 25)";
			String query_2 = "INSERT INTO product (name, price , stock) VALUES ('Lamp',77.00, 77)";
			String query_3 = "INSERT INTO product (name, price , stock) VALUES ('Hoekbank',77.00, 77)";
			statement.addBatch(query);
			statement.addBatch(query_2);
			statement.addBatch(query_3);
			statement.executeBatch();
			logger.log(Level.INFO, "Creation of dummy products is successfull");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@AfterEach

	void tearDown() {
		try (Connection conn = DatabaseConnectionXML.getConnection(); Statement statement = conn.createStatement();) {

			String query = "DELETE FROM product;";
			String query_2 = "ALTER TABLE product AUTO_INCREMENT = 1";
			statement.addBatch(query);
			statement.addBatch(query_2);
			statement.executeBatch();
			logger.log(Level.INFO, "Deletion of product table data is succesfull");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Test
	void canCreateProduct() {
		Product product = new Product.ProductBuilder().name("Stoel").price(new BigDecimal("999.00")).stock(99).build();
		productDAO.createProduct(product);
		Product productReturned = productDAO.getProductByName("Stoel");
		String name = productReturned.getName();
		assertEquals("Stoel", name);
	}

	@Test
	void canGetProductById() {
		Product product = productDAO.getProductById(1);
		assertEquals("Tafel", product.getName());
	}

	@Test
	void canGetProductByName() {
		Product product = productDAO.getProductByName("Lamp");
		assertEquals("Lamp", product.getName());
	}

	@Test
	void canDeleteProduct() {
		Product product = new Product.ProductBuilder().name("Stoel").price(new BigDecimal("999.00")).stock(99).build();
		productDAO.createProduct(product);
		productDAO.deleteProduct(productDAO.getProductByName("Stoel"));
		assertNull("This should return null", productDAO.getProductByName("Stoel"));
	}

	@Test
	void canUpdateProduct() {
		Product product = productDAO.getProductByName("Hoekbank");
		Product.ProductBuilder newProduct = new Product.ProductBuilder(product);
		Product updatedProduct = newProduct.name("Loungebank").build();
		productDAO.updateProduct(updatedProduct);
		assertEquals("Loungebank", productDAO.getProductByName("Loungebank").getName());
	}

	@Test
	void canGetAllProducts() {
		List<Product> listOfProducts = productDAO.getAllProducts();
		assertAll("listOfProducts", () -> assertEquals(3, listOfProducts.size()),
				() -> assertEquals("Tafel", listOfProducts.get(0).getName()),
				() -> assertNotNull(listOfProducts.get(1).getName())

		);
	}

}