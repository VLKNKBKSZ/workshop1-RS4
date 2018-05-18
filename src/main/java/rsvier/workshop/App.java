package rsvier.workshop;

import rsvier.workshop.controller.MainMenuController;
import rsvier.workshop.dao.DAOFactory;


public class App {
	
	
	public static void main(String[] args) {
		
		DAOFactory daoFactory = new DAOFactory();
		
		MainMenuController mainMenuController = new MainMenuController();
		mainMenuController.runView();
	}
}
