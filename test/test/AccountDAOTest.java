package test;

import static org.junit.Assert.*;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.*;
import java.util.logging.*;
import org.junit.Test;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AccountDAOTest {

	AccountDAO accountDao = new AccountDAOImp();
	Logger logger = LogConnection.getLogger();

	@BeforeEach
	public void setup() {

		logger.info("Seeting up database....");
		try {
			Connection conn = DatabaseConnectionXML.getConnection();
			Statement stmt = conn.createStatement();
			String query = "INSERT INTO account (email,password) VALUES ('evy@gmail.com','1234')";
			stmt.addBatch(query);
			String query2 = "INSERT INTO account (email,password) VALUES ('hippo@gmail.com','5678')";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			fail("Error instantiating database table." + e.getMessage());
		}

	}

	@AfterEach
	public void cleanUp() {
		try {
			Connection conn = DatabaseConnectionXML.getConnection();
			Statement stmt = conn.createStatement();
			String query = "DALETE FROM account";
			stmt.addBatch(query);
			String query2 = "ALTER TABLE account AUTO_INCREMENT = 1";
			stmt.addBatch(query2);
			stmt.executeBatch();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Test
	public void testCreateAccount() {
		Account account = new Account();
		account.setEmail("bird@gmail.com");
		account.setPassword("1111");
		int generatedId = accountDao.createAccount(account);
		assertEquals(3, generatedId);

	}

	@Test
	public void testGetAccount() {
		Account account = accountDao.getAccount("hippo@gmail.com", "5678");

		Assertions.assertAll(() -> assertNotNull(account), () -> assertEquals(2, account.getAccountId()));

	}

	@Test
	public void testGetAllAccounts() {
		List<Account> accountList = new ArrayList<>();
		Assertions.assertAll(() -> assertEquals(2, accountList.size()), () -> assertNotNull(accountList),
				() -> assertNotNull(accountList.get(0)), () -> assertNotNull(accountList.get(1))

		);
	}

	@Test
	public void testUpdateAccount() {
		Account account = new Account();
		account.setAccountId(2);
		account.setPassword("55555");
		accountDao.updateAccount(account);

		try {
			Connection conn = DatabaseConnectionXML.getConnection();
			String query = "SELECT * from account WHERE account_id = 2";
			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			assertEquals("55555", rs.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Test
	public void testDeleteAccount() {

		Account account = new Account();
		account.setAccountId(2);
		accountDao.deleteAccount(account);

		try {
			Connection conn = DatabaseConnectionXML.getConnection();
			String query = "DElETE FROM account WHERE account_id = 2";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			rs.next();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}
