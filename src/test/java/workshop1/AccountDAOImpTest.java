package workshop1;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AccountDAOImpTest {

	AccountDAO accountDao = new AccountDAOImp();
	Logger logger = LogConnection.getLogger();

	@BeforeEach
	public void setup() {

		logger.info("Setting up database....");
		try (Connection conn = DataSource.getConnection(); Statement stmt = conn.createStatement();) {

			String query = "INSERT INTO account (account_type, email,password) VALUES (1,'evy@gmail.com','1234')";
			stmt.addBatch(query);
			String query2 = "INSERT INTO account (account_type, email,password) VALUES (1, 'hippo@gmail.com','5678')";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL Exception occured", e);
			fail("Error instantiating database table." + e.getMessage());
		}

	}

	@AfterEach
	public void cleanUp() {
		try (Connection conn = DataSource.getConnection(); Statement stmt = conn.createStatement();) {

			String query = "DELETE FROM account";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE account AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL Exception occured", e);

		}
	}

	@Test
	public void testCreateAccount() {
		Account account = new Account();
		account.setAccountType(1);
		account.setEmail("bird@gmail.com");
		account.setPassword("1111");
		int generatedId = accountDao.createAccount(account);
		assertEquals(3, generatedId);

	}

	@Test
	public void testGetAccount() {
		Account account = accountDao.getAccountByEmail("hippo@gmail.com");

		Assertions.assertAll(() -> assertNotNull(account), () -> assertEquals(2, account.getAccountId()));

	}

	@Test
	public void testGetAllAccounts() {
		List<Account> accountList = accountDao.getAllAccounts();
		Assertions.assertAll(() -> assertEquals(2, accountList.size()), () -> assertNotNull(accountList),
				() -> assertNotNull(accountList.get(0)), () -> assertNotNull(accountList.get(1))

		);
	}

	@Test
	public void testUpdateAccount() {
		Account account = accountDao.getAccountByEmail("hippo@gmail.com");

		account.setPassword("55555");

		accountDao.updateAccount(account);

		try (Connection conn = DatabaseConnectionXML.getConnection();Statement statement = conn.createStatement();) {

			String query = "SELECT * from account WHERE account_id = 2";
			
			ResultSet resultSet = statement.executeQuery(query);
			if (resultSet.next()) {
				assertEquals("55555", resultSet.getString(4));
			}
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL Exception occured", e);

		}
	}

	@Test
	public void testDeleteAccount() {

		Account account = new Account();
		account.setAccountId(2);
		accountDao.deleteAccount(account);

		try (Connection conn = DatabaseConnectionXML.getConnection();) {

			String query = "SELECT * FROM account WHERE account_id = 2";
			Statement stmt = conn.createStatement();
			ResultSet resultSet = stmt.executeQuery(query);
			if (resultSet.next()) {
				assertNull("Null", resultSet.getInt(1));
			}

		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL Exception occured", e);
		}
	}

}
