package rsvier.workshop.service;

import rsvier.workshop.controller.*;
import rsvier.workshop.controller.MainController.TypeOfController;
import rsvier.workshop.dao.*;
import rsvier.workshop.domain.*;
import rsvier.workshop.view.*;

public class LoginValidator {

	private AccountView accountView = new AccountView();
	private Controller employeeController = new EmployeeController();
	private Hashing hashing = new Hashing();

	public void loginCheckAccountValidation() {

		accountView.printRequestEmailInput();
		String email = accountView.getStringInput();
		accountView.printRequestPasswordInput();
		String password = accountView.getStringInput();
		Account account = DAOFactory.getAccountDAO().getAccountByEmail(email);

		/*
		 * first accounts value is checked. This has been added because of mongoDB. In
		 * mongo if a field is not existing that field has no null value like we would
		 * have in mysql so a check if account.getEmail() == null was resulting in a
		 * nullpointer when we where using mongodb instead of sql.
		 */

		if (account == null || account.getEmail() == null
				|| (!hashing.checkPassword(password, account.getPassword()))) {

			accountView.printLoginDetailsWrong();
			MainController.setController(TypeOfController.MAINMENU);
		} else {
			accountView.printLoginAccountIsSuccessful();
			employeeController.runView();
		}
	}

}
