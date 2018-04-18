
package rsvier.workshop.dao;

import java.util.List;

import rsvier.workshop.domain.*;

public interface AccountDAO {

	public List<Account> getAllAccounts();

	public Account getAccount(String email, String password);

	public int createAccount(Account account);

	public void updateAccount(Account account);

	public void deleteAccount(Account account);

	public boolean login(String email, String password);

}
