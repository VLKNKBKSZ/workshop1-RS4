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
		
		List<Account> accountList = new ArrayList();
		Account account = new Account();
		
		String query = "SELECT * FROM account";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);
				ResultSet resultSet = preparedStatement.executeQuery();) {
			
			while(resultSet.next()){
				account.setAccountId(resultSet.getInt(1));
				account.setEmail(resultSet.getString(2));
				account.setPassword(resultSet.getString(3));
				accountList.add(account);
				
			}
			
			return accountList;
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find an account list", e);
		}
		return null;
	}

	@Override
	public Account getAccount(String email, String password) {
		
		Account account = new Account();
		
		String query = "SELECT * FROM account WHERE email = ? AND password = ?";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			
			preparedStatement.setString(1, email);
			preparedStatement.setString(2, password);
			
			try (ResultSet resultSet = preparedStatement.executeQuery();) {
				
			if(!resultSet.next()){
				
				logger.log(Level.WARNING, "Can't find an account ");
			} else {
				
					
				account.setAccountId(resultSet.getInt(1));
				account.setEmail(resultSet.getString(2));
				account.setPassword(resultSet.getString(3));
				
				
			}
			
			}
			return account;
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to find an account ", e);
		}
		return null;
	}

	@Override
	public void createAccount(Account account) {
		
		String query = "INSERT INTO account (email,password) VALUES(?,?)";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to create an account ", e);
		}
		
	}

	@Override
	public void updateAccount(Account account) {
		
		String query = "UPDATE account SET email = ?, password = ? WHERE account_id = ?";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			
			preparedStatement.setString(1, account.getEmail());
			preparedStatement.setString(2, account.getPassword());
			preparedStatement.setInt(3, account.getAccountId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to update an account ", e);
		}
		
	}

	@Override
	public void deleteAccount(Account account) {
		
String query = "DELETE FROM account WHERE account_id = ?";
		
		try(Connection conn = DatabaseConnectionXML.getConnection();
				PreparedStatement preparedStatement = conn.prepareStatement(query);) {
			
			preparedStatement.setInt(1, account.getAccountId());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Error occured while trying to delete an account ", e);
		}
		
		
	}

}
