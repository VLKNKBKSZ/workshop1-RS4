package rsvier.workshop.view;

public class CustomerView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer  |  Klantenbeheer ===========\n");

	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Zoek klant\n2- Klant Toevoegen\n0- Verlaat klantenbeheer");

	}

	public void printAskDeleteOrUpdateCustomer() {

		System.out.println("1- Klantgegevens aanpassen\n2- Klant verwijderen\n0- Terug naar vorige menu");
	}
	
	
	public void printAskCustomerLastName() {

		System.out.print("Vul achternaam in: ");
	}

	public void printCustomerNotFound() {

		System.out.println("Geen klant met deze naam gevonden.");
	}

	
	public void printAskNumberOfCustomer() {

		System.out.print("Selecteer een klant door het nummer in te voeren: ");
	}
	
	
	
	
	

}
