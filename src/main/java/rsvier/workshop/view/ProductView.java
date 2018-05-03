package rsvier.workshop.view;

public class ProductView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer	|  Productenbeheer ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		System.out.println("Werknemer|  Producten\r\n" + "---------------------------\r\n" + 
				"1- Meubel Toevoegen\r\n" + "2- Meubel Aanpassen\r\n" + "3- Meubel Verwijderen\r\n" + 
				"4- Totale Meubels\r\n" + "0- Verlaat Medewerker| Producten");
		
	}

	
}
