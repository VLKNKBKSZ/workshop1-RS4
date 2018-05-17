package rsvier.workshop.service;

import rsvier.workshop.controller.*;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class LoginValidator {

	private AccountView accountView = new AccountView();
	public AccountDAO accountDAO = DAOFactory.getAccountDAO();
	private Controller employeeController = new EmployeeController();
	private Hashing hashing = new Hashing();

	public void loginCheckAccountValidation() {

		accountView.printRequestEmailInput();
		String email = accountView.getStringInput();
		accountView.printRequestPasswordInput();
		String password = accountView.getStringInput();
		Account account = DAOFactory.getAccountDAO().getAccountByEmail(email);

		if (account.getEmail() == null || (!hashing.checkPassword(password, account.getPassword()))) {

			accountView.printLoginDetailsWrong();
			MainController.setController(TypeOfController.MAINMENU);
		} else {
			accountView.printLoginAccountIsSuccessful();
			employeeController.runView();
		}
	}

}
