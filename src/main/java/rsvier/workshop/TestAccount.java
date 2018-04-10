package rsvier.workshop;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class TestAccount {

	public static void main(String[] args) {
		
		
		AccountDAO accountDao = new AccountDAOImp();
		
		Account account = new Account("evy@gmail.com","678910");
		
		account.setAccountId(25);
		accountDao.updateAccount(account);
		
		

	}

}
