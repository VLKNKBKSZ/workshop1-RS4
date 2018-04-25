package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAO;
import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.service.Validator;
import rsvier.workshop.view.AccountView;

public class AccountController extends Controller{

	private AccountDAO accountDAO = new AccountDAOImp();
	private AccountView accountView = new AccountView();
	private Account account;
	private Validator validator = new Validator();

	public AccountController() {

	}

	public AccountController(AccountDAO accountDAO, AccountView accountView) {
		this.accountDAO = accountDAO;
		this.accountView = accountView;
	}

	public String requestAndValidateEmail() {
		String email;
		
		do {
			accountView.printRequestEmailInput();
			email = accountView.getStringInput();
		
		} while (!validator.validateEmail(email));

		return email;
	}
	
	public String requestAndValidatePassword() {
		String password;
		
		do {
			accountView.printRequestPasswordInput();;
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

	

	private void accountMenu(int menuNumber) {
		switch (menuNumber) {

		case 0:
			accountView.printExitApplicationMessage();
			break;
		case 1:

		}
	}

	@Override
	public void runView() {
		accountView.printHeaderMessage();
		accountView.printMenuMessage();
		accountMenu(accountView.getIntInput());
		
	}

}
