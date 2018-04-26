package rsvier.workshop.service;

import rsvier.workshop.controller.*;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class LoginValidation {
	
	private AccountView accountView = new AccountView();
	private AccountDAO accountDAOImp = new AccountDAOImp();
	private Controller employeeController = new EmployeeController();
	
	
public void loginCheckAccountValidation() {
		
		accountView.printRequestEmailInput();
		String email = accountView.getStringInput();		

		Account account = accountDAOImp.getAccountByEmail(email);
		
		accountView.printRequestPasswordInput();
		String password = accountView.getStringInput();
		
		if (account.getEmail() == null|| (!account.getPassword().equals(password))) {
			accountView.printLoginDetailsWrong();	
			
		} else {
			accountView.printLoginAccountIsSuccessful();
			employeeController.runView();
		}
	}

}
