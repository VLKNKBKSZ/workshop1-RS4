package rsvier.workshop.view;

public class EmployeeView extends View {

	@Override
	public void printHeaderMessage() {
		System.out.println("\n=========== Werknemer | Hoofdmenu ===========\n");
		
	}

	@Override
	public void printMenuMessage() {
		System.out.println("1- Klanten beheren\n2- Accounts beheren\n3- Producten beheren\n4- Bestellingen beheren\n0- Uitloggen");
		
	}
	
	

}
