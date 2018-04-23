package rsvier.workshop.view;

import java.util.Scanner;

public abstract class View {

	private Scanner userInput = new Scanner(System.in);

	public abstract void printHeaderMessage();

	public abstract void printMenuMessage();
	
	public void printExitApplicationMessage() {
		System.out.println("Bedankt. Tot ziens !");
	}

	public Integer getIntInput() {
		try {
			return Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException ex) {
			System.out.print("Wrong input, please enter a number: ");
			return getIntInput();
		}
	}

	public String getStringInput() {
		String s = userInput.nextLine();

		if (s == null) {
			System.out.print("Please enter a String: ");
			return getStringInput();
		} else {
			return s;
		}
	}

	public void askUserYesOrNo() {
		System.out.println("Please enter \"Y\" for Yes or \"N\" for No");
	}

	public void confirmExitProgram() {
		System.out.println("You are about to exit the program.");
	}
	
	public void printMenuInputIsWrong() {
		System.out.println("Het ingevoerde menu keuze is onjuist, probeer het nogmaals");
	}
}
