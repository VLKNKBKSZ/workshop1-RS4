package rsvier.workshop;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;

public class EvaTestsMore {

	public static void main(String[] args) {
		
		Account account1 = new Account("eva@eva", "wacht");
		
		AccountDAO accountDAO1 = new AccountDAOImp();
		accountDAO1.createAccount(account1);
		
		AccountDAO account2 = account1.getAccount("eva@eva", "wacht");

	}

}
