package rsvier.workshop.view;

import rsvier.workshop.domain.Order;

public class OrderView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer | Bestellingbeheer ===========\n");
	}
	

	@Override
	public void printMenuMessage() {
		System.out.println("\n1- Zoek een bestelling\n2- Maak een bestelling\n0- Verlaat bestellingbeheer");
	}

	
	public void printAskUserToUpdateOrDeleteOrder() {
		System.out.println("1- Bestelling aanpassen\n2- Bestelling verwijderen"
				+ "\n0- Terug naar vorige menu");
	}

	
	public void printOrderSuccessFullyDeleted() {
		System.out.println("De bestelling is succesvol verwijderd.");
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
		System.out.println("Kies het nummer van de bestelling die u wilt bekijken: ");
	}

	
	public void printAskOrderId() {
		System.out.println("Vul het bestelnummer in: ");
	}
	

	public void printOrderHasBeenPlaced() {
		System.out.println("Uw bestelling is geplaatst.");
	}

	public void printOrderHasNotBeenPlaced() {
		System.out.println("Uw bestelling is niet geplaatst.");
	}
	
	public void printOrdersFound(String message, int i, Order order) {
		System.out.println(message + i + ", " + " Bestelling id-nummer: " + order.getOrderId() + " Datum van bestelling: "
				+ order.getOrderDateTime() + " Totale prijs:" + order.getTotalPrice());
	}
	

	public void printUpdateExistingOrder() {
		System.out.println("\n1- Bekijk bestelregels van de bestelling"
						+ "\n2- Voeg nieuwe bestelregels toe aan de bestelling\n0- Terug naar vorige menu"
				);
	}
	

	public void printWhatOrderLineToUpdate() {
		System.out.println("\nWelke bestelregel wilt u aanpassen?");
	}

	public void printYouDoNotHaveOrders() {
		System.out.println("\nU heeft geen bestellingen staan op dit moment\n");	
	}


	public void printYouCanNowAddProducts() {
		System.out.println("U kunt nu producten toevoegen aan uw bestelling.\n");
		
	}
	
	
}
