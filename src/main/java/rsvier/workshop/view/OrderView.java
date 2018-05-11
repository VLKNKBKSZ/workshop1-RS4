package rsvier.workshop.view;

import rsvier.workshop.domain.Order;

public class OrderView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer | Bestellingbeheer ===========\n");
	}

	@Override
	public void printMenuMessage() {
		System.out.println("\n1- Zoek bestelling\n2- Plaats bestelling\n0- Verlaat bestellingbeheer");
	}

	
	public void printAskUserToUpdateOrDeleteOrder() {
		System.out.println("1- Bestelling aanpassen\n2- Bestelling verwijderen\n0- Terug naar vorige menu");

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

		System.out.println("Kies het Order nummer van de bestelling die u wilt bekijken: ");

	}

	public void printAskOrderId() {
		System.out.println("Vul het bestelnummer in: ");
	}

	public void printOrderHasBeenPlaced() {
		System.out.println("Uw bestelling is geplaatst.");
	}

	public void printOrdersFound(String message, int i, Order order) {
		System.out.println(message + i + ", " + " Order id: " + order.getOrderId() + " Datum van order: "
				+ order.getOrderDateTime() + " Totale prijs:" + order.getTotalPrice());
	}

	public void printUpdateExistingOrder() {
		System.out
				.println("\n1- Bekijk bestelregels van de order\n2- Voeg nieuwe bestelregels aan de order\n3-Terug naar vorig menu");
	}

	public void printUpdateExistingOrderLine() {
		System.out.println("\n1- Pas bestelregel aan\n2- Verwijder bestelregel \n3- Terug naar vorig menu");
	}

	public void printWhatOrderToUpdate() {
		System.out.println("\nWelke bestelregel wilt u aanpassen?");
	}

}
