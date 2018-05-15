package rsvier.workshop.controller;

import org.mindrot.jbcrypt.BCrypt;
import rsvier.workshop.dao.AccountDAO;
import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.service.Hashing;
import rsvier.workshop.service.Validator;
import rsvier.workshop.view.AccountView;

public class AccountController extends Controller{

	private AccountDAO accountDAO = new AccountDAOImp();
	private AccountView accountView = new AccountView();
	private Account account;
	private Validator validator = new Validator();
	private Hashing hashing = new Hashing();


	
	public AccountController() {
	}
	

	
	@Override
	public void runView() {
		
		accountView.printHeaderMessage();
		accountView.printMenuMessage();
	//	accountMenuSwitch(View.getIntInput());

	}
	
	/* Not using this method right now:

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
	
	*/
	
	
	
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
			accountView.printRequestPasswordInput();
			password = accountView.getStringInput();
		
		} while (!validator.validatePassword(password));

		return hashing.createHash(password);
	}

	
	public Account doCreateAccount() {
		
		String email = requestAndValidateEmail();
		String hashedPassword = requestAndValidatePassword();
		account = new Account(email, hashedPassword);
		account.setAccountId(accountDAO.createAccount(account));
		return account;
	}
}
