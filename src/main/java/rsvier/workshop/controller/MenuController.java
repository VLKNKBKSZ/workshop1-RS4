package rsvier.workshop.controller;

import rsvier.workshop.dao.AccountDAOImp;
import rsvier.workshop.domain.Account;
import rsvier.workshop.view.MenuView;

import java.util.Scanner;

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
        System.out.print("Vul email adres in: ");
        String email = menuView.getStringInput();
        System.out.print("Vul wachtwoord in: ");
        String password = menuView.getStringInput();

        Account account = accountDAOImp.getAccountLogin(email);
        return (account.getPassword().equals(password));
    }
}
