package workshop1;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.logging.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.dao.AddressDAOImp;
import rsvier.workshop.dao.OrderDAOImp;
import rsvier.workshop.dao.OrderLineDAOImp;
import rsvier.workshop.dao.PersonDAOImp;
import rsvier.workshop.dao.ProductDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.domain.Address;
import rsvier.workshop.domain.Order;
import rsvier.workshop.domain.OrderLine;
import rsvier.workshop.domain.Person;
import rsvier.workshop.domain.Product;
import rsvier.workshop.utility.DatabaseConnectionXML;
import rsvier.workshop.utility.LogConnection;

/**
 * @author Volkan Kabaksiz
 *
 */
class OrderLineDAOImpTest {

	private Logger logger = LogConnection.getLogger();

	AccountDAOImp accountDAO = new AccountDAOImp();
	PersonDAOImp personDAO = new PersonDAOImp();
	ProductDAOImp productDAO = new ProductDAOImp();
	OrderDAOImp orderDAO = new OrderDAOImp();
	OrderLineDAOImp orderLineDAO = new OrderLineDAOImp();
	AddressDAOImp addressDAO = new AddressDAOImp();

	@BeforeEach
	void setUp() {
		try (Connection conn = DatabaseConnectionXML.getConnection(); Statement statement = conn.createStatement();) {
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL Exception occured", e);
		}
	}

	@AfterEach
	void tearDown() {
		try (Connection conn = DatabaseConnectionXML.getConnection(); Statement statement = conn.createStatement();) {
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occured", e);
		}

	}

	@Test
	void canCreateOrderLine() {
		
		
	}

}
