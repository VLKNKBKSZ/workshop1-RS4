package rsvier.workshop.controller;

import rsvier.workshop.dao.*;
import rsvier.workshop.domain.Account;
import rsvier.workshop.service.LoginValidation;
import rsvier.workshop.view.AccountView;
import rsvier.workshop.view.MainMenuView;

public class MainMenuController extends Controller{

	private AccountDAO accountDAOImp = new AccountDAOImp();
	private MainMenuView mainMenuView = new MainMenuView();
	private AccountView accountView = new AccountView();
	private AccountController accountController = new AccountController();
	private EmployeeController employeeController = new EmployeeController();
	private LoginValidation loginValidation = new LoginValidation();
	
	
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
			loginValidation.loginCheckAccountValidation();
			runView();
			break;

		case 2:
			accountController.doCreateAccount();
			break;
			
		default:
			mainMenuView.printMenuInputIsWrong();
			runView();

		}
	}

	
	
	
	
}
