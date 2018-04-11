/**
 * 
 */
package workshop1;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Product;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

/**
 * @author Volkan Kabaksiz
 *
 */
class ProductDAOImpTest {

	ProductDAOImp productDAO = new ProductDAOImp();
	private Logger logger = LogConnection.getLogger();

	@BeforeEach
	void setUp() {

		try {

			Connection conn = DatabaseConnectionXML.getConnection();
			Statement statement = conn.createStatement();
			String query = "INSER INTRO product (name, price , stock) VALUES ('Tafel',95.00, 25)";
			statement.addBatch(query);
			statement.executeBatch();
			logger.log(Level.INFO, "Creation of dummy product is successfull");
			conn.close();

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);

		}

	}

	@AfterEach

	void tearDown() {
		try {
			Connection conn = DatabaseConnectionXML.getConnection();
			Statement statement = conn.createStatement();
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
		Product product = new Product.ProductBuilder().name("Stoel").price(new BigDecimal("999.00")).stock(99)
				.build();
		productDAO.createProduct(product);
		Product productReturned = productDAO.getProductByName("Stoel");
		String name = productReturned.getName();

		assertEquals("Stoel", name);

	}

}
