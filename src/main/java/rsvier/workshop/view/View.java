package rsvier.workshop.view;

import java.util.Scanner;

public abstract class View {

	private Scanner userInput = new Scanner(System.in);

	public abstract void printHeaderMessage();

	public abstract void printMenuMessage();
	
	
	public void printAskUserYesOrNo() {
		System.out.println("Vul in \"J\" indien Ja en \"N\" indien Nee.");
	}
	public void printAskconfirmDelete() {
		System.out.println("Weet u het zeker?");
	}
	public  String confirmYesOrNoSwitch() {
		printAskconfirmDelete();
		printAskUserYesOrNo();
		String yesOrNo = getStringInput();
		return yesOrNo;
	}


	public void printConfirmExitProgram() {
		System.out.println("U verlaat het programma.");
	}
	
	public void printMenuInputIsWrong() {
		System.out.println("De ingevoerde menu keuze is onjuist, probeer het nogmaals");
	}
	
	public void printExitApplicationMessage() {
		System.out.println("Bedankt. Tot ziens !");
	}

	public Integer getIntInput() {
		try {
			return Integer.parseInt(userInput.nextLine());
		} catch (NumberFormatException ex) {
			System.out.print("Verkeerde input. Vul aub een nummer in: ");
			return getIntInput();
		}
	}

	public String getStringInput() {
		String s = userInput.nextLine();

		if (s == null || (s.trim().isEmpty())) {
			System.out.print("U kunt dit gedeelte niet leeg laten. Vul aub iets in: ");
			return getStringInput();
		} else {
			return s;
		}
	}

	
}
