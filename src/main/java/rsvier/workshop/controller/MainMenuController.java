package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.MainMenuView;

public class MainMenuController {


    private AccountDAOImp accountDAOImp = new AccountDAOImp();
    private MainMenuView mainMenuView = new MainMenuView();

    public void doLoginMenu() {
    	mainMenuView.printHeaderMessage();
        mainMenuView.printMenuMessage();
        loginMenu(mainMenuView.getIntInput());
    }

    private void loginMenu(int menuNumber) {
        switch (menuNumber) {

            case 0: System.out.println("Thank you, Bye Bye!"); break;
            case 1: if(loginCheckAccountValidation()) {
                //todo open main menu
            } else {

            } break;
            case 2:
                //todo accountDAOImp.createAccount();
                break;
        }
    }

    private boolean loginCheckAccountValidation() {
        mainMenuView.requestEmailInput();
        String email = mainMenuView.getStringInput();
        mainMenuView.requestPasswordInput();
        String password = mainMenuView.getStringInput();

        Account account = accountDAOImp.getAccountLogin(email);
        if (account == null) {
            return false;
        }
        return (account.getPassword().equals(password));
    }
}
