package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.MainMenuView;
import rsvier.workshop.view.View;

public class MainMenuController {


    private AccountDAOImp accountDAOImp = new AccountDAOImp();
    private MainMenuView mainMenuView = new MainMenuView();

    public void doLoginMenu() {
        mainMenuView.printLoginOrCreateNewAccountMenu();
        loginMenu(View.getIntInput());
    }

    private void loginMenu(int menuNumber) {
        switch (menuNumber) {

            case 0: System.out.println("Thank you, Bye Bye!"); break;
            case 1: if(login()) {
                //todo open main menu
            } else {

            } break;
            case 2:
                //todo accountDAOImp.createAccount();
                break;
        }
    }

    private boolean login() {
        mainMenuView.requestEmailInput();
        String email = View.getStringInput();
        mainMenuView.requestPasswordInput();
        String password = View.getStringInput();

        Account account = accountDAOImp.getAccountLogin(email);
        return (account.getPassword().equals(password));
    }
}
