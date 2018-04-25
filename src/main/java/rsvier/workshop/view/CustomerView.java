package rsvier.workshop.view;

public class CustomerView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("=========== Klanten ===========");

	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Zoek klant\n 2- Klant Toevoegen\n 0- Verlaat klantenbeheer");

	}

	public void printAskCustomerLastName() {

		System.out.println("vul achternaam in:");
	}

	public void printSelectCustomer() {

		System.out.println("Voer het nummer in van de klant die u wilt verwijderen of aanpassen:");
	}
	public void printAskDeleteOrUpdateCustomer() {

		System.out.println("1- Klantgegevens aanpassen\n 2- Klant verwijderen\n 0- Terug naar naar vorige menu");
	}
	
	

}
