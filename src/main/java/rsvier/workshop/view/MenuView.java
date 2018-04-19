package rsvier.workshop.view;

import java.util.Scanner;

public class MenuView {
    private Scanner userInput = new Scanner(System.in);
    public void printLoginOrCreateNewAccountMenu() {
        System.out.println("Welkom Op Evvo Meubels\r\n" + "\r\n" + "----------------------------------\r\n" + "\r\n"
                + "1 - Login\r\n" + "2 - Maak een nieuw Account\r\n0 - Verlaat Evvo Meubels ");
    }

    public Integer getIntInput(){
        try {
            return Integer.parseInt(userInput.nextLine());
        } catch (NumberFormatException ex) {
            System.out.print("Wrong input, please enter a number: ");
            return getIntInput();
        }
    }

    public void requestEmailInput() {
        System.out.print("Vul email adres in: ");
    }

    public void requestPasswordInput() {
        System.out.print("Vul wachtwoord in: ");
    }

    public String getStringInput() {
        String s = userInput.nextLine();

        if (s == null){
            System.out.print("Please enter a String: ");
            return getStringInput();
        } else {
            return s;
        }
    }
}
