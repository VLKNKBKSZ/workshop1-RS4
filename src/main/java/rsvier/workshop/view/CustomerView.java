package rsvier.workshop.view;

public class CustomerView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Klanten ===========\n");

	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Zoek klant\n2- Klant Toevoegen\n0- Verlaat klantenbeheer");

	}

	public void printAskDeleteOrUpdateCustomer() {

		System.out.println("1- Klantgegevens aanpassen\n2- Klant verwijderen\n0- Terug naar naar vorige menu");
	}
	
	
	public void printAskCustomerLastName() {

		System.out.println("Vul achternaam in:");
	}

	public void printCustomerNotFound() {

		System.out.println("Geen klant met deze naam gevonden.");
	}

	
	public void printAskNumberOfCustomer() {

		System.out.println("Voer het nummer in van de klant die u wilt verwijderen of aanpassen:");
	}
	
	
	
	
	

}
