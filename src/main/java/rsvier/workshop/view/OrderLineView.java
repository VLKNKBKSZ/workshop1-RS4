package rsvier.workshop.view;

public class OrderLineView extends View {
	@Override
	public void printHeaderMessage() {

	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Voeg product toe aan bestelling"
				+ "\n2- Bekijk huidige bestelling\n3- Bestelling plaatsen\n4- Annuleer bestelling");
	}

	public void printEditOrDeleteOrderLine() {
		System.out.println("1 - Pas aantal in de bestelregel aan\n 2- Verwijder bestelregel van bestelling\n 3- Sla de wijzigingen op.\n 4- Aanuleren de wijzigingen.\n 0- Terug naar hoofd menu.");
	}
	public void printRequestNameOfProductToView() {
		System.out.println("Voer de naam in van het product dat u wilt bestellen.");
	}

	public void printRequestAmountOfProducts() {
		System.out.println("Voer het aantal producten in dat u wilt bestellen.");
	}

	public void printOrderIsEmpty() {
		System.out.println("Uw bestellinglijst is leeg.");
	}

	public void printAskUserToChoseOrderLine() {
		System.out.println("Voer het nummer van de bestelregel in die uw wilt aanpassen");
	}
	
	public void printProductStockIsNotAvailable(int stockFromDatabase, int requestedAmountOfProducts) {
		System.out.println("Het aantal wat u heeft ingevoerd is " + requestedAmountOfProducts + ". De voorraad voor dit product is " + stockFromDatabase + ". Probeer het nogmaals.\n");
	}
	
	public void printAskNewNumberOfProductsInOrderLine() {
		System.out.println("Voer het nieuwe aantal van de producten in a.u.b : \n");
	}
	

}
