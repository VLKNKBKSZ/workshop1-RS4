package rsvier.workshop.controller;


import rsvier.workshop.service.LoginValidation;

import rsvier.workshop.view.MainMenuView;

public class MainMenuController extends Controller{

	private MainMenuView mainMenuView = new MainMenuView();
	private AccountController accountController = new AccountController();
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
			
		case 1://Login into account
			loginValidation.loginCheckAccountValidation();
			runView();
			break;

		case 2: //Create a new account
			accountController.doCreateAccount();
			runView();
			break;
			
		default:
			mainMenuView.printMenuInputIsWrong();
			runView();

		}
	}

	
	
	
	
}
