package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.AccountView;
import rsvier.workshop.view.MainMenuView;

public class MainMenuController {

	private AccountDAOImp accountDAOImp = new AccountDAOImp();
	private MainMenuView mainMenuView = new MainMenuView();
	private AccountView accountView = new AccountView();
	private AccountController accountController = new AccountController();

	public void doLoginMenu() {
		mainMenuView.printHeaderMessage();
		mainMenuView.printMenuMessage();
		loginMenu(mainMenuView.getIntInput());
	}

	private void loginMenu(int menuNumber) {
		switch (menuNumber) {

		case 0:
			mainMenuView.printExitApplicationMessage();
			break;
		case 1:loginCheckAccountValidation();
			break;

		case 2:
			accountController.doCreateAccount();
			break;

		}
	}

	public void loginCheckAccountValidation() {
		
		accountView.requestEmailInput();
		String email = mainMenuView.getStringInput();
		accountView.requestPasswordInput();
		String password = mainMenuView.getStringInput();

		Account account = accountDAOImp.getAccountLogin(email);
		if (account.getEmail() == null) {
			
			accountView.printRequestedAccountIsNull();
			doLoginMenu();
		} else {
			
			if(account.getPassword().equals(password)) {
				accountView.printLoginAccountIsSuccesfull();
				// call method of main menu that is not here yet
			} else {
				accountView.printPasswordOfAccountNotMatching();
			}
		}
	}
}
