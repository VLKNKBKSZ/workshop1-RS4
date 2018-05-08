package rsvier.workshop.view;

public class OrderView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer | Bestellingbeheer ===========\n");
	}

	@Override
	public void printMenuMessage() {
		System.out.println("\n 1- Zoek bestelling\n 2- Plaats bestelling\n 0- Verlaat bestellingbeheer");	
	}

	public void printAskUserToUpdateOrDeleteProduct() {
		System.out.println("1- Bestelling aanpassen\n2- Bestelling verwijderen\n0- Terug naar vorige menu");
		
	}
	
	public void printHeaderSelectSearchOption() {
		System.out.println("\n=========== Werknemer | Bestelling zoeken ===========\n");
	}
	
	public void printAskSearchOrderByNumberOrByName() {
		System.out.println("1- Zoek met bestelnummer\n2- Zoek met klant achternaam\n0- Terug naar vorige menu");
	}
	
	public void printOrderNotFound() {
		System.out.println("De bestelling is niet gevonden. Probeert u het nogmaals.");	
	}
	
	public void printAskSelectOrder() {
		System.out.println("Kies het nummer van de bestelling die u wilt bekijken");	
	}
	
	public void printAskOrderId() {
		System.out.println("Vul het bestelnummer in: ");	
	}
	
	public void printOrderHasBeenPlaced() {
		System.out.println("Uw bestelling is geplaatst.");
	}

}


