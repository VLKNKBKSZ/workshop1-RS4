package rsvier.workshop.dao;

import java.util.List;

import rsvier.workshop.domain.*;

public interface AccountDAO {

	public List<Account> getAllAccounts();
	public Account getAccount(String email, String password);
	public void createAccount(Account account);
	public boolean updateAccount(Account account);
	public void deleteAccount(Account account);
}
