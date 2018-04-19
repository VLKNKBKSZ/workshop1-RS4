package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.MenuView;

public class MenuController {


    private AccountDAOImp accountDAOImp = new AccountDAOImp();
    private MenuView menuView = new MenuView();

    public void loginMenu(int menuNumber) {
        switch (menuNumber) {

            case 0: System.out.println("Thank you, Bye Bye!"); break;
            case 1: if(login()) {
                //open main menu
            }; break;
            case 2:
                accountDAOImp.createAccount();
                break;
        }
    }

    private boolean login() {
        menuView.requestEmailInput();
        String email = menuView.getStringInput();
        menuView.requestPasswordInput();
        String password = menuView.getStringInput();

        Account account = accountDAOImp.getAccountLogin(email);
        return (account.getPassword().equals(password));
    }
}
