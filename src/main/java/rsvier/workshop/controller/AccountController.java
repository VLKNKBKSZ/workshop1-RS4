package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAO;
import rsvier.workshop.domain.Account;
import rsvier.workshop.domain.Validator;
import rsvier.workshop.view.AccountView;

public class AccountController {

	private AccountDAO accountDAO;
	private AccountView accountView;
	private Account account;
	private Validator validator;

	public AccountController() {

	}

	public AccountController(AccountDAO accountDAO, AccountView accountView) {
		this.accountDAO = accountDAO;
		this.accountView = accountView;
	}

	public String requestAndValidateEmail() {
		String email;
		
		do {
			accountView.requestEmailInput();
			email = accountView.getStringInput();
		
		} while (!validator.validateEmail(email));

		return email;
	}
	
	public String requestAndValidatePassword() {
		String password;
		
		do {
			accountView.requestPasswordInput();;
			password = accountView.getStringInput();
		
		} while (!validator.validatePassword(password));

		return password;
	}

	public void doCreateAccount() {

		String email = requestAndValidateEmail();
		String password = requestAndValidatePassword();
		account = new Account(email, password);
		accountDAO.createAccount(account);
	}

	public void doAccountMenu() {
		accountView.printHeaderMessage();
		accountView.printMenuMessage();
		accountMenu(accountView.getIntInput());
	}

	private void accountMenu(int menuNumber) {
		switch (menuNumber) {

		case 0:
			accountView.printExitApplicationMessage();
			break;
		case 1:

		}
	}

}