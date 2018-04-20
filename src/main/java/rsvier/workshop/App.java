package rsvier.workshop;

import rsvier.workshop.controller.MainMenuController;

public class App {
	public static void main(String[] args) {
	
		MainMenuController mainMenuController = new MainMenuController();
		mainMenuController.doLoginMenu();
	}
}
