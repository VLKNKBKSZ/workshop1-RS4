package rsvier.workshop.view;

public class ProductView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer	|  Productenbeheer ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		System.out.println("---------------------------\n" + 
				"1- Zoek product\n" + "2- Product toevoegen\r\n" +  
				"3- Alle producten weergeven\n" + "0- Verlaat Medewerker - Productenbeheer");
	}

	public void printUpdateOrDeleteMenu() {
		System.out.println("\n1- Product aanpassen\n" + "2- Product verwijderen\n" +
				"0- Terug naar vorige menu");
	}

	public void printAskForProductName() {
		System.out.println("Geef de naam van het product:");
		
	}
	
}
