package rsvier.workshop.view;

public class PersonView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer  |  Klantenbeheer ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		
	}
	
	public void printUpdateUserDetailsMenu() {
		System.out.println("Welke gegevens wilt u veranderen?\n"
				+ "\n1- Naam\n2- Achternaam\n3- Tussenvoegsel\n4- Adresgegevens\n0- Terug naar vorige menu");
	}
	
	
	public void printAskUserForNewName() {
		System.out.print("Vul uw (nieuwe) naam in: ");
	}
	
	public void printAskUserForNewLastName() {
		System.out.print("Vul uw (nieuwe) achternaam in: ");
	}
	
	public void printAskUserForMiddleName() {
		System.out.print("Vul uw (nieuwe) tussenvoegsel in: ");
	}
	
	public void printNameHasBeenUpdated() {
		System.out.println("\nUw naam is aangepast.\n");
	}
	
	public void printLastNameHasBeenUpdated() {
		System.out.println("\nUw achternaam is aangepast.\n");
	}
	
	public void printMiddleNameHasBeenUpdated() {
		System.out.println("\nUw tussenvoegsel is aangepast.\n");
	}

}
