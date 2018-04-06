package rsvier.workshop.dao;

import java.sql.*;
import java.util.*;
import java.util.logging.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.utility.*;

public class AccountDAOImp implements AccountDAO {

	private Logger logger = LogConnection.getLogger();

	@Override
	public List<Account> getAllAccounts() {

		List<Account> accountList = new ArrayList<>();
		Account account = new Account();

		String query = "SELECT * FROM account";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {

			while (resultSet.next()) {
				account.setAccountId(resultSet.getInt(1));
				account.setEmail(resultSet.getString(2));
				account.setPassword(resultSet.getString(3));
				accountList.add(account);

			}

			return accountList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred", e);
		}

		logger.log(Level.WARNING, "Error occured while trying to find an account list ");
		return null;
	}

	@Override
	public Account getAccount(String email, String password) {

		Account account = new Account();

		String query = "SELECT * FROM account WHERE email = ? AND password = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);

			try (ResultSet resultSet = preparedStatement.executeQuery();) {

				if (resultSet.next()) {

					account.setAccountId(resultSet.getInt(1));
					account.setEmail(resultSet.getString(2));
					account.setPassword(resultSet.getString(3));

				}

			}
			return account;

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

		logger.log(Level.WARNING, "Can't find an account ");
		return null;
	}

	@Override
	public int createAccount(Account account) {

		int generatedId = 0;

		String query = "INSERT INTO account (email,password) VALUES(?,?)";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query,
						PreparedStatement.RETURN_GENERATED_KEYS);) {

			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());

			preparedStatement.executeUpdate();

			try (ResultSet rs = preparedStatement.getGeneratedKeys()) {
				if (rs.next()) {
					generatedId = rs.getInt(1);
					logger.log(Level.INFO, "succesfully created account");
				}
			}

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

		return generatedId;

	}

	public void updateAccount(Account account) {

		String query = "UPDATE account SET email = ?, password = ? WHERE account_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3, account.getAccountId());

			preparedStatement.executeUpdate();
			System.out.println("Account updated succesfully");
			
			logger.log(Level.WARNING, "Account updated succesfully");

		} catch (SQLException e) {

			logger.log(Level.WARNING, "SQL exception occurred ", e);

		}

	}

	@Override
	public void deleteAccount(Account account) {

		String query = "DELETE FROM account WHERE account_id = ?";

		try (Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {

			preparedStatement.setInt(1, account.getAccountId());

			preparedStatement.executeUpdate();
			System.out.println("Account deleted succesfully");

		} catch (SQLException e) {
			logger.log(Level.WARNING, "SQL exception occurred ", e);
		}

	}

}
