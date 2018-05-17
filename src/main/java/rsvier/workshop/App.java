package rsvier.workshop;

import rsvier.workshop.controller.MainMenuController;
import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;

public class App {
	
	public static boolean hikariEnabled;
	public static void main(String[] args) {
		
		View view = new MainMenuView();
		hikariEnabled = view.printAskUserToEnableHikariOrNot();
		view.printAskUserToUseSQLOrMongo();
		
		MainMenuController mainMenuController = new MainMenuController();
		mainMenuController.runView();
	}
}
