package rsvier.workshop.controller;


import rsvier.workshop.service.LoginValidator;
import rsvier.workshop.view.MainMenuView;

public class MainMenuController extends Controller{

	private MainMenuView mainMenuView = new MainMenuView();
	private AccountController accountController = new AccountController();
	private LoginValidator loginValidator = new LoginValidator();
	
	@Override
	public void runView() {
		
		mainMenuView.printHeaderMessage();
		mainMenuView.printMenuMessage();
		
		loginMainMenuSwitch(mainMenuView.getIntInput());
		
	}

	
	public void loginMainMenuSwitch(int menuNumber) {
		
		switch (menuNumber) {
			
			case 1://Login into account
					loginValidator.loginCheckAccountValidation();
					break;

			case 2: //Create a new account
					accountController.doCreateAccount();
					runView();
		
					break;

			case 0:
					mainMenuView.printExitApplicationMessage();
					break;
			default:
					mainMenuView.printMenuInputIsWrong();
					runView();
					
					break;
		}
	}

	
	
}
