package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAO;
import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.service.Validator;
import rsvier.workshop.view.AccountView;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.view.View;

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
	
	
	@Override
	public void runView() {
		
		accountView.printHeaderMessage();
		accountView.printMenuMessage();
		accountMenuSwitch(View.getIntInput());
		
	}

	public void accountMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {

			case 1:	//Make new account
					
					break;
			case 2:	//Back to main menu
					
					break;
			case 0:
					accountView.printExitApplicationMessage();
					break;		
		
			default:

		}
	}
	
	
	public String requestAndValidateEmail() {
		
		String email;
		
		do {
			accountView.printRequestEmailInput();
			email = View.getStringInput();
		
		} while (!validator.validateEmail(email));

		return email;
	}
	
	
	public String requestAndValidatePassword() {
		
		String password;
		
		do {
			accountView.printRequestPasswordInput();
			password = View.getStringInput();
		
		} while (!validator.validatePassword(password));

		return password;
	}

	
	public Account doCreateAccount() {
		
		String email = requestAndValidateEmail();
		String password = requestAndValidatePassword();
		account = new Account(email, password);
		account.setAccountId(accountDAO.createAccount(account));
		return account;
	}
}
