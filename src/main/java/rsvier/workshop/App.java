package rsvier.workshop;

import rsvier.workshop.controller.MainMenuController;
import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;

public class App {

	public static boolean hikariEnabled;

	public static void main(String[] args) {

		View view = new MainMenuView();

		// Option to choose connection pool

		hikariEnabled = view.printAskUserToEnableHikariOrNot();

		// Option to choose database

		view.printAskUserToUseSQLOrMongo();

		MainMenuController mainMenuController = new MainMenuController();
		mainMenuController.runView();
		//
	}
}
