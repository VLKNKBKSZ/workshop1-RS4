package rsvier.workshop.view;

public class PersonView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Person View ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		
	}
	
	public void printUpdateUserDetailsMenu() {
		System.out.println("\n1- Naam\n2- Achternaam\n3- Tussenvoegsel\n4- Adresgegevens");
	}
	
	
	public void printAskUserForNewName() {
		System.out.println("Wat is de nieuwe naam van de gebruiker?");
	}
	
	public void printAskUserForNewLastName() {
		System.out.println("Wat is de nieuwe achternaam van de gebruiker?");
	}
	
	public void printAskUserForMiddleName() {
		System.out.println("Wat is de nieuwe tussenvoegsel van de gebruiker?");
	}

}
