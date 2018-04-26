package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.AccountView;
import rsvier.workshop.view.MainMenuView;

public class MainMenuController extends Controller{

	private AccountDAOImp accountDAOImp = new AccountDAOImp();
	private MainMenuView mainMenuView = new MainMenuView();
	private AccountView accountView = new AccountView();
	private AccountController accountController = new AccountController();
	private EmployeeController employeeController = new EmployeeController();
	
	@Override
	public void runView() {
		
		mainMenuView.printHeaderMessage();
		mainMenuView.printMenuMessage();
		loginMainMenuSwitch(mainMenuView.getIntInput());	
		
	}

	
	public void loginMainMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {

		case 0:
			mainMenuView.printExitApplicationMessage();
			break;
			
		case 1:
			loginCheckAccountValidation();
			break;

		case 2:
			accountController.doCreateAccount();
			break;
			
		default:
			mainMenuView.printMenuInputIsWrong();
			runView();

		}
	}

	
	public void loginCheckAccountValidation() {
		
		accountView.printRequestEmailInput();
		String email = mainMenuView.getStringInput();		

		Account account = accountDAOImp.getAccountLogin(email);
		
		if (account.getEmail() == null) {
			accountView.printLoginDetailsWrong();
			runView();	
			
		} else {
			accountView.printRequestPasswordInput();
			String password = mainMenuView.getStringInput();

			if (account.getPassword().equals(password)) {
				accountView.printLoginAccountIsSuccessful();
				employeeController.runView();
			} else {
				accountView.printLoginDetailsWrong();
				runView();
			}
		}
	}

	
	
}
