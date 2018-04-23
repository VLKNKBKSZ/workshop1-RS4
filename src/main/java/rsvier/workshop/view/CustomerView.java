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

}
