package rsvier.workshop.view;

public class OrderView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer	| Bestellingbeheer ===========");
	}

	@Override
	public void printMenuMessage() {
		System.out.println("\n1- Zoek Bestelling\n 2- Plaats Bestelling\n 0- Verlaat bestellingbeheer");	
	}

	public void printAskUserToUpdateOrDeleteProduct() {
		System.out.println("1- Order aanpassen\n2- Order verwijderen\n0- Terug naar vorige menu");
	}
	
	public void printOrderNotFound() {
		System.out.println("De bestelling is niet gevonden. Probeert u het nogmaals.");	
	}
	
	public void printAskSelectOrder() {
		System.out.println("Kies het nummer van de bestelling die u wilt bekijken");	
	}
}
