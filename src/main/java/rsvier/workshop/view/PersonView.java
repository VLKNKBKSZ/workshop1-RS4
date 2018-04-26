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
	

}
