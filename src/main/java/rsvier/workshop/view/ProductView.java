package rsvier.workshop.view;

public class ProductView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer	|  Productenbeheer ===========\n");
		
	}
	
	public void printProductInfoHeader() {
		System.out.println("\n======== Productgegevens ======");
	}

	@Override
	public void printMenuMessage() {
		System.out.println("---------------------------\n" + 
				"1- Zoek product\n2- Product toevoegen\r\n" +
				"3- Alle producten weergeven\n0- Verlaat Medewerker - Productenbeheer");
	}

	public void printUpdateOrDeleteMenu() {
		System.out.println("\n1- Product aanpassen\n2- Product verwijderen\n" +
				"0- Terug naar vorige menu");
	}

	public void printProductIsSuccessfullyCreated() {
		System.out.println("Product is succesvol aangemaakt en opgeslagen");
	}
	
	public void printUpdateProduct() {
		System.out.println("1- Toon huidig product\n2- Productnaam aanpassen\n3- Productprijs aanpassen\n" +
				"4- Productvoorraad aanpassen\n5- Veranderingen opslaan\n0- Terug naar vorige menu");
	}

	public void printAskForProductName() {
		System.out.print("Geef de naam van het product: ");
		
	}

	public void printAskForProductPrice() {
		System.out.print("Geef de prijs van het product: € ");
	}

	public void printProductNameAlreadyExists() {
		System.out.println("\nDe opgegeven naam voor het nieuwe product bestaat al. Probeer het opnieuw.");
	}
	
	public void printAskForProductStock() {
		System.out.print("Geef de huidige voorraad van het product: ");
	}
	
	public void printProductNotFound() {
		System.out.println("Product niet gevonden.");
	}
	
}
