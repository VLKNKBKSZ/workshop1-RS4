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
		System.out.println("Welke gegevens wilt u veranderen?"
				+ "\n1- Naam\n2- Achternaam\n3- Tussenvoegsel\n4- Adresgegevens\n0- Terug naar vorige menu");
	}
	
	
	public void printAskUserForNewName() {
		System.out.print("Vul je (nieuwe) naam in: ");
	}
	
	public void printAskUserForNewLastName() {
		System.out.print("Vul je (nieuwe) achternaam in: ");
	}
	
	public void printAskUserForMiddleName() {
		System.out.print("Vul je (nieuwe) tussenvoegsel in: ");
	}

}
